package com.example.ecommerce.E_Commerce.controller;

import com.example.ecommerce.E_Commerce.DTOs.OrderDTO;
import com.example.ecommerce.E_Commerce.DTOs.OrderRequestDTO;
import com.example.ecommerce.E_Commerce.Service.OrderService;
import com.example.ecommerce.E_Commerce.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private AuthUtil authUtill;
    @PostMapping("/order/users/payments/{paymentMethod}")
    public ResponseEntity<OrderDTO> orderProducts(@PathVariable String paymentMethod,
                                                  @RequestBody OrderRequestDTO orderRequestDTO)
    {
        String emailId=authUtill.loggedInEmail();
        OrderDTO orderDTO=orderService.placeOrder
                (
                        emailId,
                        orderRequestDTO.getAddressId(),
                        paymentMethod
                );
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
