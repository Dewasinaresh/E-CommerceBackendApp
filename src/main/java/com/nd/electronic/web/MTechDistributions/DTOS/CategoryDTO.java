package com.nd.electronic.web.MTechDistributions.DTOS;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotNull(message = "Id is null")
    @NotEmpty(message = "Invalid Id")
    private String categoryId;
    @Length(min = 3,message = "Invalid title , min size is 3 is req")
    @NotEmpty@NotNull@NotBlank
    private String title;
    @Length(min =10 ,max = 100,message = "Invalid Descriptions")
    @NotEmpty@NotNull@NotBlank
    private String description;
    @NotBlank
    @NotEmpty
    private String categoryImage;
}