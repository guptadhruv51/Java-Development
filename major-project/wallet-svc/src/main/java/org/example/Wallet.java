package org.example;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private Integer userId;

    private Double balance;


}
