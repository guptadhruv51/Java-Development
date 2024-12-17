package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Email
    @Column(nullable = false)
    private String email;
    private List<OrderItem> orderItemList=new ArrayList<>();
    private LocalDate date;

    @OneToOne
    @JoinColumn(name="payment_id")
    private Payment payment;
    private Double totalAmount;
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

}
