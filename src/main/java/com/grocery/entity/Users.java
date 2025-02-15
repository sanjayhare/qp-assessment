package com.grocery.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.grocery.annotations.FieldsValueMatch;
import com.grocery.annotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords do not match!")})
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer personId;

    @NotNull(message = "Name cannot be blank")
    @Size(min = 3, message = "Name be must be at least 3 characters long")
    private String name;

    @NotNull(message = "Gender cannot be blank")
    private String gender;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String emailId;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message="dateOfBirth must not be blank")
    private String dateOfBirth;

    @NotBlank(message="address must not be blank")
    private String address;

    @NotBlank(message="pwd must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message="pwd must not be blank")
    @Size(min = 8, message = "ConfirmPwd Password must be at least 8 characters long")
    @Transient
    private String confirmPwd;

    private String authorities;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

}
