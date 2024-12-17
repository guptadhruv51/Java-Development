package com.example.ecommerce.E_Commerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO
{
    private Long orderItemId;
    private ProductDTO product;
    private Integer quantity;
    private Double discount;
    private Double orderProductPrice;
}
