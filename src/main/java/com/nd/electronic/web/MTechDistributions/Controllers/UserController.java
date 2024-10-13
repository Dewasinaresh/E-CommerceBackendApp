package com.nd.electronic.web.MTechDistributions.Controllers;

import com.nd.electronic.web.MTechDistributions.DTOS.ApiResponseMassage;
import com.nd.electronic.web.MTechDistributions.DTOS.ImageResponse;
import com.nd.electronic.web.MTechDistributions.DTOS.UserDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Services.FileUploadService;
import com.nd.electronic.web.MTechDistributions.Services.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserServiceImpl userService;
    @Autowired
    public FileUploadService fileService;
    @Value("$(user.profiler.file.path)")
    private final String filePath="";
    @PostMapping("/createNewUser")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
        final UserDTO savedNewUser = userService.createUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/User/{userId}")
    public ResponseEntity<UserDTO> updateUser
    (@Validated @PathVariable(name = "userId" )String userId, @RequestBody UserDTO userDto)
    {
        final UserDTO updatedUserDTO = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(updatedUserDTO,HttpStatus.OK);
    }
    //Delete

    @DeleteMapping("/Delete/{userId}")
    public ResponseEntity<ApiResponseMassage> deleteuser(@PathVariable(name = "userId") String userId) throws IOException {
        final String deleteMassage = userService.deleteUser(userId);
        ApiResponseMassage massage=ApiResponseMassage.builder()
                .massage(deleteMassage)
                .success(true)
                .HttpStatus(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(massage,HttpStatus.OK);

    }

    //getAllUser
    @GetMapping("/getAllUser")
    public ResponseEntity<PageableResponse<UserDTO>> getAlluser(
            @RequestParam(value = "PageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "PageSize",defaultValue = "2",required = false) int pageSize,
            @RequestParam(value = "SortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "SortDir",defaultValue = "asc",required = false)String sortDir
            ){
        final PageableResponse<UserDTO> response = userService.getAllUserDto(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    //SearchByName
    @GetMapping("/getByName/{Name}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable(name = "Name") String userName){
        final UserDTO userByName = userService.getUserByName(userName);
        return new ResponseEntity<>(userByName,HttpStatus.OK);

    }
    //SearchByEmail


    @GetMapping("/getByMail/{Email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable(name = "Email")String userEmail){

        final UserDTO userByEmail = userService.getUserByEmail(userEmail);
        return new ResponseEntity<>(userByEmail,HttpStatus.OK);

    }
    @PostMapping("Image/{UserId}")
    public ResponseEntity<ImageResponse> uploadImage(
            @PathVariable("UserId") String userId,
            @RequestParam(value = "Image")MultipartFile file
            ) throws IOException {
        //upload
        final String fileupload = fileService.fileupload(file, filePath);
        ImageResponse imageResponse=ImageResponse.builder()
                .fileName(fileupload)
                .success(true)
                .HttpStatus(HttpStatus.CREATED)
                .massage("file is uploaded")
                .build();

        //update along user
        final UserDTO userById = userService.getUserById(userId);
        userById.setImageName(fileupload);
        final UserDTO userDto = userService.updateUser(userById, userId);
        return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);

    }

//Server Image
    @GetMapping("Image/{userId}")
    public void serverImage(
            @PathVariable(name = "userId") String userId,
            HttpServletResponse response
    ) throws IOException {
        final UserDTO userDetails = userService.getUserById(userId);
        final InputStream servefile = fileService.servefile(filePath, userDetails.getImageName());
         response.setContentType(String.valueOf(MediaType.IMAGE_PNG));
        StreamUtils.copy(servefile,response.getOutputStream());

    }

}
