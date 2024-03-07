package com.nd.electronic.web.MTechDistributions.Services.impl;

import com.nd.electronic.web.MTechDistributions.DTOS.UserDto;
import com.nd.electronic.web.MTechDistributions.Entitys.UserEntity;
import com.nd.electronic.web.MTechDistributions.Repositories.UserRepo;
import com.nd.electronic.web.MTechDistributions.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    private ModelMapper Models;

    @Override
    public UserDto createUser(UserDto userDto) {
        String rondomUserId = UUID.randomUUID().toString();
        userDto.setUserId( rondomUserId );
        // convert UserDto into userEntity
        UserEntity userEntity= userDtoIntoEntity(userDto);

        final UserEntity savedUser = userRepo.save(userEntity);
        // Convert UserEntity into userDto
        UserDto newUserDto = userEntityToDto(savedUser);

        //return new saved users
        return newUserDto;

    }


    @Override
    public UserEntity updateUser(UserDto userDto, String UserId) {

        // get user entity from Repository
        final UserEntity userEntity = userRepo.findById(UserId).orElseThrow(() -> new RuntimeException(" User is not find "));
        //convert  entity into DTO
        final UserDto userDto1 = userEntityToDto(userEntity);
        //Update User
        final UserDto buildUserDto = userDto1.builder()
                .name(userDto.getName())
                .gender(userDto.getGender())
                .password(userDto.getPassword())
                .about(userDto.getAbout())
                .email(userDto.getEmail()).build();
        //  Convert and return
        return userDtoIntoEntity(buildUserDto);
    }


    @Override
    public String deleteUser(String userId) {
        final UserEntity userEntity = userRepo.findById(userId).orElseThrow(() -> new RuntimeException(" User is not find "));
        userRepo.delete(userEntity);


        return "Deleted User whose ID is "+userId;
    }

    @Override
    public List<UserDto> getAllUserDto() {

        final List<UserEntity> allUser = userRepo.findAll();

        final List<UserDto> ListUseDto = allUser.stream().map(user -> userEntityToDto(user)).collect(Collectors.toList());

        return ListUseDto;

    }

    @Override
    public UserDto getUserByName(String userName) {

        // We will do for finderMethods
        final UserEntity userEntity = userRepo.findByName(userName);
        return userEntityToDto(userEntity);

    }

    @Override
    public UserDto getUserByEmail(String Email)
    {
        final UserEntity UserByEmail = userRepo.findByEmail(Email);
        final UserDto userDto = userEntityToDto(UserByEmail);
        return userDto;
    }




    private UserDto userEntityToDto(UserEntity savedUser) {


//        final UserDto buildUserDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .gender(savedUser.getGender())
//                .about(savedUser.getAbout())
//                .build();

        return Models.map(savedUser, UserDto.class );
    }


    private UserEntity userDtoIntoEntity(UserDto userDto) {

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
}
