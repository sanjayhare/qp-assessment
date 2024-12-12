package com.grocery.mapper;


import com.grocery.dto.UserListDto;
import com.grocery.entity.Users;

public class UsersListMapper {
    public static UserListDto mappedToUserDTO (UserListDto userListDto, Users users){

        userListDto.setId(users.getPersonId());
        userListDto.setCustomerName(users.getName());
        userListDto.setDate(users.getDateOfBirth());
        userListDto.setLocation(users.getAddress());
        return userListDto;
    }
}
