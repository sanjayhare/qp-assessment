package com.grocery.service.impl;

import com.grocery.dto.UserListDto;
import com.grocery.dto.UsersDto;
import com.grocery.entity.Cart;
import com.grocery.entity.SecurityUser;
import com.grocery.entity.Users;
import com.grocery.exception.CustomerAlreadyExistsException;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.mapper.UsersListMapper;
import com.grocery.mapper.UsersMapper;
import com.grocery.repository.CartRepository;
import com.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public UsersDto getUserDetails(String userID) {
        boolean isUpdated = false;
        Users user = userRepository.findById(Integer.parseInt(userID)).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userID));
        UsersDto usersDto = UsersMapper.mappedToUserDTO(new UsersDto(), user);
        return usersDto;
    }

    public void createUser(Users user) {
        Optional<Users> optionalUsers = userRepository.findByMobileNumberOrEmailId(user.getMobileNumber(), user.getEmailId());
        if (optionalUsers.isPresent() == true && optionalUsers.get().getMobileNumber().equalsIgnoreCase(user.getMobileNumber())) {
            throw new CustomerAlreadyExistsException("User already registered with given mobileNumber : " + user.getMobileNumber());
        } else if (optionalUsers.isPresent() == true && optionalUsers.get().getEmailId().equalsIgnoreCase(user.getEmailId())) {
            throw new CustomerAlreadyExistsException("User already registered with given EmailId: " + user.getEmailId());
        }

        user.setAuthorities("ROLE_USER");
        Users savedUser =userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);

    }

    public boolean updateUser(UsersDto userDto) {
        boolean isUpdated = false;
        Users existingUser = userRepository.findById(userDto.getPersonId()).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", String.valueOf(userDto.getPersonId())));
        if (existingUser != null) {

            Users user = UsersMapper.mappedToUser(userDto, existingUser);
            userRepository.save(user);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean deleteUser(String userID) {
        boolean isDeleted = false;
        Users existingUser = userRepository.findById(Integer.parseInt(userID)).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", userID));
        if (existingUser != null) {
            userRepository.deleteById((Integer.parseInt(userID)));
            isDeleted = true;
        }
        return isDeleted;


    }

    public List<UserListDto> getAllUsers() {

        List<Users> users = (List<Users>) userRepository.findAll();
        List<UserListDto> userListDto = new ArrayList<UserListDto>();

        for (int i = 0; i < users.size(); i++) {
            Users user = users.get(i);
            UserListDto userDto = UsersListMapper.mappedToUserDTO(new UserListDto(), user);
            userListDto.add(userDto);
        }


        /*users.forEach(
              users1 -> UsersListMapper.mappedToUserDTO(new UserListDto(),users1)

        );*/

        return userListDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<Users> user = userRepository.findByEmailId(username);
        return user.map(SecurityUser::new).orElseThrow(() -> new ResourceNotFoundException("User", "UserID", username));
    }
}
