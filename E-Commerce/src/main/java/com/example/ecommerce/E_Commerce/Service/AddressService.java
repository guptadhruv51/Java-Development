package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.AddressDTO;
import com.example.ecommerce.E_Commerce.Models.User;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressByID(Long addressId);

    List<AddressDTO> getAddressByUserID(User user);

    AddressDTO updateAddressByID(Long addressId, AddressDTO addressDTO);

    String deleteAddressByID(Long addressId);
}
