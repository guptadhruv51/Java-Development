package com.example.ecommerce.E_Commerce.DTOs;

import com.example.ecommerce.E_Commerce.Models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO
{
    private Long orderId;
    private String email;
    private List<OrderItemDTO> orderItemDTOList;
    private LocalDate orderDate;
    private PaymentDTO payment;
    private Double totalAmount;
    private String orderStatus;
    private Long addressId;
}
