package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @NotBlank
    @Size(max=20)
    private String username;
    @NotBlank
    @Size(max=50)
    @Email
    private String email;
    @NotBlank
    @Size(max=120)
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},
    fetch=FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name="user_id"), //refers to the userID which is the primary key in the user table and this will be name in the join table for the userID
    inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE,CascadeType.PERSIST},
            orphanRemoval = true)
    @ToString.Exclude
    private Set<Product> products;
    @ToString.Exclude
    @OneToOne(mappedBy = "user",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Cart cart;
    @Getter
    @Setter
    @OneToMany(mappedBy ="user" ,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Address> addressList=new ArrayList<>();


}
