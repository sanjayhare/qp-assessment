package com.grocery.controller;


import com.grocery.constant.GroceryConstants;
import com.grocery.dto.ResponseDto;
import com.grocery.dto.UserListDto;
import com.grocery.dto.UsersDto;
import com.grocery.entity.Users;
import com.grocery.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/grocery/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/updateUser")
    public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UsersDto usersDto) {
        //System.out.println("User by me"+user.toString());
        boolean isUpdated = userService.updateUser(usersDto);
        if (isUpdated == true) {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(GroceryConstants.STATUS_417, GroceryConstants.MESSAGE_417_UPDATE));
        }
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<ResponseDto> updateUser(@RequestParam String userID) {
        //System.out.println("User by me"+user.toString());
        boolean isDeleted = userService.deleteUser(userID);
        if (isDeleted == true) {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(new ResponseDto(GroceryConstants.STATUS_201, GroceryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(GroceryConstants.STATUS_417, GroceryConstants.MESSAGE_417_DELETE));
        }
    }
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserListDto>> fetchAllUserDetails() {
        List<UserListDto> userListDetails = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userListDetails);
    }

    @GetMapping("/fetchUser")
    public ResponseEntity<UsersDto> fetchUserDetails(@RequestParam String userID) {
        UsersDto userDetails = userService.getUserDetails(userID);
        return ResponseEntity.status(HttpStatus.OK).body(userDetails);
    }
}
