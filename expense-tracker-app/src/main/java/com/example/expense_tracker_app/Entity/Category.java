package com.example.expense_tracker_app.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    public Category()
    {

    }
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
