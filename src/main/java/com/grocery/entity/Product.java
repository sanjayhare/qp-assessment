package com.grocery.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "products")
@Data
public class Product extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer productId;

    private String productName;

    private String productDescription;

    private Double productPrice;

    private Long productQuantity;

}
