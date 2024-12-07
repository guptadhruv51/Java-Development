package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.CartDTO;
import com.example.ecommerce.E_Commerce.DTOs.ProductDTO;
import com.example.ecommerce.E_Commerce.Models.Cart;
import com.example.ecommerce.E_Commerce.Models.CartItem;
import com.example.ecommerce.E_Commerce.Models.Product;
import com.example.ecommerce.E_Commerce.exceptions.ApiException;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.E_Commerce.repository.CartItemRepo;
import com.example.ecommerce.E_Commerce.repository.CartRepository;
import com.example.ecommerce.E_Commerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import com.example.ecommerce.E_Commerce.util.AuthUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CartServiceImpl implements  CartService
{
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepo cartItemRepository;
    @Override
    public CartDTO addProductTOCart(Long productId, Integer quantity)
    {
        /**
         * Steps:
         * Create cart or find existing cart
         * Retrieve the product details (including price...)
         * perform validations: check if product in cart, if it is available (check stock),
         * create cart item (after validations have passed)
         * save cart item
         * reduce product stock (or once the order has been placed)
         * return updated cart
         */
        Cart cart=createCart();
        Long cartId=cart.getCartId();
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("product","productId",productId));

        CartItem cartItem=cartItemRepository.findCartItemByProductAndCartId(cartId,productId);
        if(cartItem!=null)
        {
            throw new ApiException("Product "+product.getProductName()+" already exists in the cart");
        }
        if(product.getQuantity()==0)
        {
            throw new ApiException(product.getProductName()+" is out of stock");
        }
        if(product.getQuantity()<quantity)
        {
            throw new ApiException("Please make an order of the "+product.getProductName()+" less than or equal to the quantity: "+product.getQuantity());
        }
        CartItem newCartItem=new CartItem();

        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setDiscount(product.getDiscount());
        newCartItem.setProductPrice(product.getSpecialPrice());
        cartItemRepository.save(newCartItem);
        product.setQuantity(product.getQuantity());
        cart.setTotalPrice(cart.getTotalPrice()+(product.getSpecialPrice()*quantity));
        cartRepository.save(cart);
        CartDTO cartDTO=modelMapper.map(cart,CartDTO.class);
        List<CartItem> cartItems=cart.getCartItemList();
        Stream<ProductDTO> productStream=cartItems.stream().map(item->
        {
            ProductDTO map=modelMapper.map(item.getProduct(),ProductDTO.class);
            map.setQuantity(Long.valueOf(item.getQuantity()));
            return map;
        });
        cartDTO.setProducts(productStream.toList());
        return cartDTO;

    }

    private Cart createCart()
    {
        Cart userCart= cartRepository.findCartByEmail(authUtil.loggedInEmail());
        if(userCart!=null)
        {
            return userCart;
        }
        Cart cart=new Cart();
        cart.setTotalPrice(0.0);
        cart.setUser(authUtil.loggedInUser());
        return cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getAllCarts()
    {
        List<Cart> carts=cartRepository.findAll();
        if(carts.isEmpty())
        {
         throw new ApiException("No carts exist at the moment");
        }
        return carts.stream().map(
                cart-> {
                    CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
                    List<ProductDTO> products = (List<ProductDTO>) cart.getCartItemList().stream().
                            map(p -> modelMapper.map(p.getProduct(), ProductDTO.class));
                    cartDTO.setProducts(products);
                    return cartDTO;
                }).collect(Collectors.toList());


               // return cartDTOList;
}

    @Override
    public CartDTO getCart(String emailId, Long cartId) {

        Cart cart=cartRepository.findCartByEmailAndCartId(emailId,cartId);
        cart.getCartItemList().forEach(c->c.getProduct().setQuantity(Long.valueOf((c.getQuantity()))));
        if (cart==null)
        {
            throw new ResourceNotFoundException("Cart","cartId",cartId);
        }
        CartDTO cartDTO=modelMapper.map(cart,CartDTO.class);
        List<ProductDTO> products=cart.getCartItemList().stream()
                .map(p->modelMapper.map(p.getProduct(),ProductDTO.class))
                .toList();

        cartDTO.setProducts(products);

        return cartDTO;
    }

    @Transactional
    @Override
    public CartDTO updateProductQuantityInCart(Long productId, Integer operation)
    {
            String emailId=authUtil.loggedInEmail();

            Cart usercart=cartRepository.findCartByEmail(emailId);
            Long cartId=usercart.getCartId();

            Cart cart=cartRepository.findById(cartId)
                    .orElseThrow(()-> new ResourceNotFoundException("Cart","cartId",cartId));

            Product product=productRepository.findById(productId)
                    .orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));
            if(product.getQuantity()==0)
                throw new ApiException(product.getProductName()+" is not available");
            if (product.getQuantity() < operation) {
            throw new ApiException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getQuantity() + ".");
                }

            CartItem cartItem=cartItemRepository.findCartItemByProductAndCartId(cartId,productId);
        if (cartItem == null) {
            throw new ApiException("Product " + product.getProductName() + " not available in the cart!!!");
        }

        int newQuantity=cartItem.getQuantity()+operation;
        if(newQuantity<0)
            throw new ApiException("The resulting quantity cannot be negative");

        if(newQuantity==0)
        {
            deleteProductFromCart(cartId,productId);
        }
        else {
            cartItem.setProductPrice(product.getSpecialPrice());
            cartItem.setQuantity(newQuantity);
            cartItem.setDiscount(product.getDiscount());
            cart.setTotalPrice(cart.getTotalPrice()+(cartItem.getProductPrice()*operation));
            cartRepository.save(cart);
        }
        CartItem updatedItem = cartItemRepository.save(cartItem);
        if(updatedItem.getQuantity() == 0){
            cartItemRepository.deleteById(updatedItem.getCartItemId());
        }


        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);

        List<CartItem> cartItems = cart.getCartItemList();

        Stream<ProductDTO> productStream = cartItems.stream().map(item -> {
            ProductDTO prd = modelMapper.map(item.getProduct(), ProductDTO.class);
            prd.setQuantity(Long.valueOf(item.getQuantity()));
            return prd;
        });


        cartDTO.setProducts(productStream.toList());

        return cartDTO;



    }

    @Override
    @Transactional
    public String deleteProductFromCart(Long cartId, Long productId)
    {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        CartItem cartItem = cartItemRepository.findCartItemByProductAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ResourceNotFoundException("Product", "productId", productId);
        }

        cart.setTotalPrice(cart.getTotalPrice() -
                (cartItem.getProductPrice() * cartItem.getQuantity()));

        cartItemRepository.deleteCartItemByProductIdAndCartId(cartId, productId);

        return "Product " + cartItem.getProduct().getProductName() + " removed from the cart !!!";
    }
}
