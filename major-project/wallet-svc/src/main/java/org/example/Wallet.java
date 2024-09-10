package org.example;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

    private Long balance;
    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
    @Enumerated(value=EnumType.STRING)
    private WalletStatus status;



}
