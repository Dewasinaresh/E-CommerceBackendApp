package com.nd.electronic.web.MTechDistributions.DTOS;

import com.nd.electronic.web.MTechDistributions.validatorUtils.ImageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {


    private String userId;
    @NotNull(message = "Null name is not allowed")
    @Size(min=10,max =20, message = "size is matched")
    @NotBlank(message="name  is blank")
    private String name;
    @NotBlank(message = "email is blank")
    private String email;
    @NotEmpty(message =" password is empty")
    private String password;
    @Size(min=3,max=6,message = "invalid size of gender")
    @NotBlank(message = "gender is blank")
    private String gender;
    @NotEmpty(message = "about is Empty")
    private String about;

    //Custom Validat
    @ImageName(message = "Image name is not currect ")
    private String imageName;

}
