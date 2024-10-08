package com.example.ecommerce.E_Commerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.NIP;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO
{
    private Long productId;
    private String productName;
    private String image;
    private Long quantity;
    private Double price;
    private Double specialPrice;
    private double discount;
}
