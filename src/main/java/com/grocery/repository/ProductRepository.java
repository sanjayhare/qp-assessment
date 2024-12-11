package com.grocery.repository;

import com.grocery.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*@Query("SELECT p FROM Project p WHERE p.projectName like %:projectName%")
    List<Product> findByProjectName(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE p.noOfTeammates like %:size%")
    List<Product> findByNoOfTeammates(@Param("size") String size);*/

    Product findByProductName(String productName);

}
