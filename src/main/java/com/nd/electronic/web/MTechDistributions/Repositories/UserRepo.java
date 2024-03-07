package com.nd.electronic.web.MTechDistributions.Repositories;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDto;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity,String> {

    public UserEntity findByName(String userName);
    public UserEntity findByEmail(String userEmail);





}
