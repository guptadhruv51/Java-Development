package com.example.ecommerce.E_Commerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotBlank
    @Size(min=5,message = "Street name is too short")
    private String street;

    @NotBlank
    @Size(min=5,message = "Building name is too short")
    private String buildingName;
    @NotBlank
    @Size(min=4,message = "City name is too short")
    private String CityName;

    @NotBlank
    @Size(min=2,message = "State name is too short")
    private String State;

    @NotBlank
    @Size(min=6,message = "pincode is too short")
    private String pincode;


    @ToString.Exclude
    @ManyToMany(mappedBy = "addressList")
    private List<User> users=new ArrayList<>();

    public Address(String street, String buildingName, String cityName, String state, String pincode) {
        this.street = street;
        this.buildingName = buildingName;
        CityName = cityName;
        State = state;
        this.pincode = pincode;
    }
}
