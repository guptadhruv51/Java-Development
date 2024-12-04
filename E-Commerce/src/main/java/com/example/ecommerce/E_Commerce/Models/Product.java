package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
@ToString
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
    @ManyToOne
    @JoinColumn(name="seller_id")
    private User user;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<CartItem> products=new ArrayList<>();
}
