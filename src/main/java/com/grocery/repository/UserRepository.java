package com.grocery.repository;



import com.grocery.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByMobileNumberOrEmailId(String mobileNumber, String email);
    Optional<Users> findByMobileNumber(String mobileNumber);
    Optional<Users>findByEmailId(String email);


}
