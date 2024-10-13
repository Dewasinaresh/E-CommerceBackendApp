package com.nd.electronic.web.MTechDistributions.Entitys;


import com.nd.electronic.web.MTechDistributions.validatorUtils.ImageName;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Entity(name = "Users")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "User_Id",nullable = false)
    private String userId;
    @Column(name = "User_Name",length = 50)
    private String name;
    @Column(name = "User_Email",unique = true,updatable = false,nullable = false)
    private String email;
    @Column(name="User_Password",unique = true,length = 500)
    private String password;
    @Column(name = "User_Gender",length = 10)
    private String gender;
    @Column(name = "User_about",length =1000)
    private String about;

    @Column(name = "User_ImageName")
    private String imageName;

    @OneToOne(mappedBy = "userEntity",cascade = CascadeType.REMOVE)
    private CartEntity cart;

    @OneToMany(mappedBy = "userEntity",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<OrdersEntity> ordersEntityList=new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
