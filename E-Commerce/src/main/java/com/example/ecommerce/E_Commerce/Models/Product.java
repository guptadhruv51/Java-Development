package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String productName;
    @NotBlank
    private String description;
    @NotBlank
    private Long Quantity;
    @NotBlank
    private double price;
    private double specialPrice;
    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
}
