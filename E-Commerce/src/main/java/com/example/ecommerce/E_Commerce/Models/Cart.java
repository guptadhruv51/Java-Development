package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="carts")
@Entity
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="cart",cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REMOVE})
    private List<CartItem> cartItemList=new ArrayList<>();
    private Double totalPrice=0.0;

}