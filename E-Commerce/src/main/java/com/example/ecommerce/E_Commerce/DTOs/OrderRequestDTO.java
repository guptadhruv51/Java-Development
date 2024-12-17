package com.example.ecommerce.E_Commerce.DTOs;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO
{
    private Long addressId;
    private String paymentMethod;

}
