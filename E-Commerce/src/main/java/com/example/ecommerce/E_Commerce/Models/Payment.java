package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(mappedBy = "payment",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Order order;

    @NotBlank
    @Size(min=4,message="payment method must contain atleast 4 characters")
    private String paymentMethod;

    public Payment(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
