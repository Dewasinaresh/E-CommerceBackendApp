package com.nd.electronic.web.MTechDistributions.Services;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDto;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UserService {
    //create
    public UserDto createUser(UserDto userDto);

    //Update User
    public UserEntity updateUser(UserDto userDto, String UserId);

    //delete User
    public String deleteUser(String userId);

    //getUser
    public List<UserDto> getAllUserDto();
    public UserDto getUserByName(String userName);

    public UserDto getUserByEmail(String Email);

}
