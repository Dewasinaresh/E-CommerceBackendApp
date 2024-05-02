package com.nd.electronic.web.MTechDistributions.Services.impl;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import com.nd.electronic.web.MTechDistributions.Repositories.UserRepo;
import com.nd.electronic.web.MTechDistributions.Services.UserService;
import com.nd.electronic.web.MTechDistributions.Utility.GeneratePageableResponse;
import com.nd.electronic.web.MTechDistributions.exceptionUtils.ResouceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    PageableResponse<UserDTO> response;
    @Autowired
    private ModelMapper Models;
    Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    @Value("$(user.profiler.file.path)")
    private String filePath="";

    @Override
    public UserDTO createUser(UserDTO userDto) {
        String rondomUserId = UUID.randomUUID().toString();
        userDto.setUserId( rondomUserId );
        // convert UserDto into userEntity
        UserEntity userEntity= userDtoIntoEntity(userDto);

        final UserEntity savedUser = userRepo.save(userEntity);
        // Convert UserEntity into userDto
        UserDTO newUserDTO = userEntityToDto(savedUser);

        //return new saved users
        return newUserDTO;

    }


    @Override
    public UserDTO updateUser(UserDTO userDto, String UserId) {

        // get user entity from Repository
        final UserEntity userEntity = userRepo.findById(UserId).orElseThrow(() -> new ResouceNotFoundException(" User is not find "));
        //convert  entity into DTO
        final UserDTO userDTO1 = userEntityToDto(userEntity);
         return userDTO1;
    }


    @Override
    public String deleteUser(String userId) throws IOException {

        final UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new ResouceNotFoundException(" User is not find "));
        String fullFilePath =filePath+userEntity.getImageName();
        Files.delete(Path.of(fullFilePath));
        log.info("file is deleted from {}",fullFilePath);
        userRepo.delete(userEntity);
        return "Deleted User whose ID is "+userId;
    }

    @Override
    public PageableResponse<UserDTO> getAllUserDto(int pageNumber, int pageSize, String sortBy, String sortDir)
    {
        Sort sort= ((sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending()));
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        final Page<UserEntity> specificPageData = userRepo.findAll(pageable);
        response= GeneratePageableResponse.generatePageResponse(specificPageData, UserDTO.class);
        return response;

    }

    @Override
    public UserDTO getUserByName(String userName) {

        // We will do for finderMethods
        final UserEntity userEntity = userRepo.findByName(userName);
        return userEntityToDto(userEntity);

    }

    @Override
    public UserDTO getUserByEmail(String Email)
    {
        final UserEntity UserByEmail = userRepo.findByEmail(Email);
        final UserDTO userDto = userEntityToDto(UserByEmail);
        return userDto;
    }




    private UserDTO userEntityToDto(UserEntity savedUser) {


//        final UserDto buildUserDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .gender(savedUser.getGender())
//                .about(savedUser.getAbout())
//                .build();

        return Models.map(savedUser, UserDTO.class );
    }


    private UserEntity userDtoIntoEntity(UserDTO userDto) {

        //DTO to Entity
//        final UserEntity buildUser = UserEntity.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .gender(userDto.getGender())
//                .about(userDto.getAbout())
//                .build();

        return  Models.map(userDto,UserEntity.class);
    }

    @Override
    public UserDTO getUserById(String userId) {
        final List<UserEntity> byId = userRepo.findAllById(Collections.singleton(userId));
        final UserDTO userDto = userEntityToDto((UserEntity) byId);
        return userDto;
    }
}
