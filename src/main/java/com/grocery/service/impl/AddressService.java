package com.grocery.service.impl;

import com.grocery.entity.Address;
import com.grocery.entity.Users;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.AddressRepository;
import com.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean saveAddress(Address address, Integer userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserID", String.valueOf(userId)));
        address.setUser(user);
        Address address1 = addressRepository.save(address);
        if (address1.getAddressId() > 0) {
            return true;
        }
        return false;
    }

    public List<Address> getAddress(Integer userId) {
        List<Address> address = addressRepository.findByUser_PersonId(userId);
        return address;
    }

    public boolean updateAddress(Integer addressId,Address address) {

        Address address1 = addressRepository.findById(addressId).
                orElseThrow(() -> new ResourceNotFoundException("Address", "AddressId", String.valueOf(address.getAddressId())));;

        address1.setAddress1(address.getAddress1());
        address1.setAddress2(address.getAddress2());
        address1.setCity(address.getCity());
        address1.setState(address.getState());
        address1.setZipCode(address.getZipCode());
        try
        {
            addressRepository.save(address1);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
