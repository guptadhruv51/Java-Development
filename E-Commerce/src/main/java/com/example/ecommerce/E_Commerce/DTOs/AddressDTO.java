package com.example.ecommerce.E_Commerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class AddressDTO
{

    private Long addressId;

    private String street;


    private String buildingName;

    private String CityName;


    private String State;


    private String pincode;

}
