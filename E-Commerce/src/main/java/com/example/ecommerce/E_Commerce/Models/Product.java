package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long productId;
    @NotBlank
    @Size(min = 3,message = "Product name must be longer")
    private String productName;
    private String image;
    @NotBlank
    @Size(min=6,message = "Product description must be longer")
    private String description;
    @NotBlank
    private Long Quantity;
    @NotBlank
    private double price;
    private double specialPrice;
    private double discount;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
}
