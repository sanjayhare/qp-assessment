package com.grocery.controller;

import com.grocery.constant.GroceryConstants;
import com.grocery.dto.ResponseDto;
import com.grocery.entity.Address;
import com.grocery.service.impl.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grocery/address")
public class AddressController {


    @Autowired
    private AddressService addressService;

    @PostMapping("/save/{userId}")
    public ResponseEntity<ResponseDto> saveAddress(@PathVariable Integer userId, @RequestBody Address address) {
        boolean isAddressSaved = addressService.saveAddress(address, userId);
        if (isAddressSaved == true) {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(GroceryConstants.STATUS_417, GroceryConstants.MESSAGE_417_UPDATE));
        }
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Address>> getAddress(@PathVariable Integer userId) {

        List<Address> userAddress = addressService.getAddress(userId);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(userAddress);
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity<ResponseDto> updateAddress(@PathVariable Integer addressId, @RequestBody Address address) {
        boolean isAddressUpdated = addressService.updateAddress(addressId, address);

        if (isAddressUpdated == true) {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(GroceryConstants.STATUS_417, GroceryConstants.MESSAGE_417_UPDATE));
        }
    }
}
