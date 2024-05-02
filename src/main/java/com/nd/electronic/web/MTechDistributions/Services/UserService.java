package com.nd.electronic.web.MTechDistributions.Services;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;

import java.io.IOException;

public interface UserService {
    //create
    public UserDTO createUser(UserDTO userDto);

    //Update User
    public UserDTO updateUser(UserDTO userDto, String UserId);

    //delete User
    public String deleteUser(String userId) throws IOException;


    //getUser
    public PageableResponse<UserDTO> getAllUserDto(int pageNumber, int pageSize, String sortBy, String sortDir);
    public UserDTO getUserByName(String userName);

    public UserDTO getUserByEmail(String Email);
    public UserDTO getUserById(String userId);

}
