package com.grocery.repository;

import com.grocery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*@Query("SELECT p FROM Project p WHERE p.projectName like %:projectName%")
    List<Product> findByProjectName(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE p.noOfTeammates like %:size%")
    List<Product> findByNoOfTeammates(@Param("size") String size);*/
}
