package com.grocery.repository;


import com.grocery.entity.Address;
import com.grocery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByUser_PersonId(Integer userId);



}
