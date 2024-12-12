package com.example.ecommerce.E_Commerce.controller;

import com.example.ecommerce.E_Commerce.DTOs.AddressDTO;
import com.example.ecommerce.E_Commerce.Models.User;
import com.example.ecommerce.E_Commerce.Service.AddressService;
import com.example.ecommerce.E_Commerce.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController
{
    @Autowired
    AuthUtil authUtil;
    @Autowired
    AddressService addressService;
    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody  AddressDTO addressDTO)
    {
        User user=authUtil.loggedInUser();
        AddressDTO savedAddressDto=addressService.createAddress(addressDTO,user);
        return new ResponseEntity<>(savedAddressDto, HttpStatus.CREATED);
    }

    @GetMapping("/addresses/")
    public ResponseEntity<List<AddressDTO>> getAllAddress()
    {
        List<AddressDTO> addressDTOList=addressService.getAllAddresses();
        return new ResponseEntity<>(addressDTOList, HttpStatus.FOUND);
    }
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressByID(@PathVariable Long addressId)
    {
        AddressDTO addressDTO=addressService.getAddressByID(addressId);
        return new ResponseEntity<>(addressDTO, HttpStatus.FOUND);
    }

    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddress()
    {
        User user=authUtil.loggedInUser();
        List<AddressDTO> addressDTOList =addressService.getAddressByUserID(user);
        return new ResponseEntity<>(addressDTOList, HttpStatus.FOUND);
    }

    @PutMapping("/addresses/{addressID")
    public ResponseEntity<AddressDTO> updateAddressByID(@PathVariable Long addressId, @RequestBody AddressDTO addressDTO)
    {
        AddressDTO updatedAddressByID=addressService.updateAddressByID(addressId,addressDTO);
        return new ResponseEntity<>(updatedAddressByID, HttpStatus.FOUND);
    }

    @DeleteMapping("/users/addresses/{addressID}")
    public ResponseEntity<String> deleteAddressByID(@PathVariable Long addressId)
    {
        String status=addressService.deleteAddressByID(addressId);
        return new ResponseEntity<>(status, HttpStatus.FOUND);
    }

}
