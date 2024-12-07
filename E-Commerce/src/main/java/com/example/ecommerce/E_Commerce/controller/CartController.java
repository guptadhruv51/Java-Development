package com.example.ecommerce.E_Commerce.controller;

import com.example.ecommerce.E_Commerce.DTOs.CartDTO;
import com.example.ecommerce.E_Commerce.Models.Cart;
import com.example.ecommerce.E_Commerce.Service.CartService;
import com.example.ecommerce.E_Commerce.repository.CartRepository;
import com.example.ecommerce.E_Commerce.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class CartController
{

    @Autowired
    private CartService cartService;
    @Autowired
    private AuthUtil authutil;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/carts/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable Long productId,
                                                    @PathVariable Integer quantity)
    {
        CartDTO cartDTO=cartService.addProductTOCart(productId,quantity);
        return new ResponseEntity<CartDTO>(cartDTO, HttpStatus.CREATED);
    }

    @GetMapping("/carts")
    public ResponseEntity<List<CartDTO>> getCarts()
    {
        List<CartDTO> cartDTOList=cartService.getAllCarts();
        return new ResponseEntity<List<CartDTO>>(cartDTOList,HttpStatus.FOUND);
    }
    @GetMapping("/carts/users/cart")
    public ResponseEntity<CartDTO> getCartById()
    {
        String emailId= authutil.loggedInEmail();
        Cart cart=cartRepository.findCartByEmail(emailId);

        CartDTO cartDTO=cartService.getCart(emailId,cart.getCartId());
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }
    @PutMapping("/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProduct(@PathVariable Long productId,
                                                     @PathVariable String operation)
    {
        CartDTO cartDTO=cartService.updateProductQuantityInCart(productId,
                operation.equalsIgnoreCase("delete")?-1:1);
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }
    @DeleteMapping("/carts/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable Long cartId,
                                                        @PathVariable Long productId) {
        String status = cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

}
