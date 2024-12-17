package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.OrderDTO;
import com.example.ecommerce.E_Commerce.DTOs.OrderItemDTO;
import com.example.ecommerce.E_Commerce.Models.*;
import com.example.ecommerce.E_Commerce.exceptions.ApiException;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.E_Commerce.repository.*;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.LogManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    CartRepository cartRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderItemRepo orderItemRepo;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartService cartService;

    @Autowired
    ModelMapper mapper;
    @Override
    @Transactional
    public OrderDTO placeOrder(String emailId, Long addressId, String paymentMethod)
    {
        Cart cart=cartRepository.findCartByEmail(emailId);
        if(cart==null)
        {
            throw  new ResourceNotFoundException("Cart","email",emailId);
        }
        Address address=addressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address","addressId",addressId));
        List<CartItem> cartItemList=cart.getCartItemList();
        if(cartItemList.isEmpty())
        {
            throw new ApiException("cart is Empty");
        }

        Order order=new Order();
        order.setEmail(emailId);
        order.setDate(LocalDate.now());
        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted");
        order.setAddress(address);

        Payment payment=new Payment(paymentMethod);
        payment.setOrder(order);
        payment=paymentRepo.save(payment);
        order.setPayment(payment);
        Order savedOrder=orderRepository.save(order);



        List<OrderItem> orderItems=new ArrayList<>();
        for (CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(savedOrder);
            orderItems.add(orderItem);
        }
        orderItems=orderItemRepo.saveAll(orderItems);
        cart.getCartItemList().forEach(item -> {
            int quantity = item.getQuantity();
            Product product = item.getProduct();

            // Reduce stock quantity
            product.setQuantity(product.getQuantity() - quantity);

            // Save product back to the database
            productRepository.save(product);

            // Remove items from cart
            cartService.deleteProductFromCart(cart.getCartId(), item.getProduct().getProductId());
        });

        OrderDTO orderDTO = mapper.map(savedOrder, OrderDTO.class);
        orderItems.forEach(item -> orderDTO.getOrderItemDTOList().add(mapper.map(item, OrderItemDTO.class)));

        orderDTO.setAddressId(addressId);

        return orderDTO;

    }
}
