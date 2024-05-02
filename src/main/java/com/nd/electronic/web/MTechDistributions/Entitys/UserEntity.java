package com.nd.electronic.web.MTechDistributions.Entitys;


import com.nd.electronic.web.MTechDistributions.validatorUtils.ImageName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Entity(name = "Users")
public class UserEntity {

    @Id
    @Column(name = "User_Id",nullable = false)
    private String userId;
    @Column(name = "User_Name",length = 50)
    private String name;
    @Column(name = "User_Email",unique = true,updatable = false,nullable = false)
    private String email;
    @Column(name="User_Password",unique = true)
    private String password;
    @Column(name = "User_Gender",length = 10)
    private String gender;
    @Column(name = "User_about",length =1000)
    private String about;

    @Column(name = "User_ImageName")
    private String imageName;

    @Column(name="Cart_Id")
    private CartEntity cart;


}
