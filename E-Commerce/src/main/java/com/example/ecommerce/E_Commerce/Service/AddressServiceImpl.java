package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.AddressDTO;
import com.example.ecommerce.E_Commerce.Models.Address;
import com.example.ecommerce.E_Commerce.Models.User;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.E_Commerce.repository.AddressRepository;
import com.example.ecommerce.E_Commerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService
{
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user)
    {
        Address address=modelMapper.map(addressDTO,Address.class);
        List<Address> addressList=user.getAddressList();
        addressList.add(address);
        user.setAddressList(addressList);

        address.setUser(user);
        Address savedAddress=addressRepository.save(address);
        return modelMapper.map(savedAddress,AddressDTO.class);

    }
    @Override
    public List<AddressDTO> getAllAddresses()
    {
        List<Address> addressList=addressRepository.findAll();

        List<AddressDTO> addressDTOList=addressList.
                stream().
                map(address -> modelMapper.map(address,AddressDTO.class)).collect(Collectors.toList());

        return addressDTOList;
    }
    @Override
    public AddressDTO getAddressByID(Long addressId) {
        Address address=addressRepository.findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address","AddressId",addressId));

        return modelMapper.map(address, AddressDTO.class);

    }
    @Override
    public List<AddressDTO> getAddressByUserID(User user)
    {
        List<Address> addressList=user.getAddressList();

        List<AddressDTO> addressDTOList=addressList.
                stream().
                map(address -> modelMapper.map(address,AddressDTO.class)).collect(Collectors.toList());

        return addressDTOList;

    }


    @Override
    public AddressDTO updateAddressByID(Long addressId, AddressDTO addressDTO)
    {
        Address oldAddress=addressRepository
                .findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address","address ID",addressId));


        oldAddress.setCityName(addressDTO.getCityName());
        oldAddress.setPincode(addressDTO.getPincode());
        oldAddress.setState(addressDTO.getState());
        oldAddress.setBuildingName(addressDTO.getBuildingName());
        oldAddress.setStreet(addressDTO.getStreet());
        Address updatedAddress=addressRepository.save(oldAddress);
        User user=updatedAddress.getUser();
        user.getAddressList().removeIf(address -> address.getAddressId().equals(addressId));
        user.getAddressList().add(updatedAddress);
        userRepository.save(user);
        return modelMapper.map(updatedAddress,AddressDTO.class);
    }

    @Override
    public String deleteAddressByID(Long addressId)
    {
        Address oldAddress=addressRepository
                .findById(addressId)
                .orElseThrow(()->new ResourceNotFoundException("Address","address ID",addressId));
        User user =oldAddress.getUser();
        user.getAddressList().removeIf(address -> address.getAddressId().equals(addressId));
        userRepository.save(user);

        addressRepository.delete(oldAddress);
        return "Address Deleted Successfully with addressID: "+addressId;
    }
}
