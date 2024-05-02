package com.nd.electronic.web.MTechDistributions.Controllers;

import com.nd.electronic.web.MTechDistributions.DTOS.CategoryDTO;
import com.nd.electronic.web.MTechDistributions.DTOS.ImageResponse;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Services.CategoryService;
import com.nd.electronic.web.MTechDistributions.Services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ImageResponse imageResponse;
    @Value("$(category.file.path)")
    private String filePath;
    //create
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(
            @Validated
            @RequestBody CategoryDTO categoryDTO
    ){
        final CategoryDTO category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    //delete
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable(name = "categoryId") String catId
    ){
        categoryService.deleteCategory(catId);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    //update
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
           @PathVariable(name = "categoryId" ) String categoryId ,
           @RequestBody CategoryDTO categoryDto
    ){
        final CategoryDTO categoryDTO = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @GetMapping("/getAllCategory")
    public ResponseEntity<PageableResponse<CategoryDTO>> getAllCategory(
            @RequestParam(name = "PageSize",defaultValue = "1",required = false)int pageSize,
            @RequestParam(name = "PageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(name = "SortBy",defaultValue = "Title",required = false)String sortBy,
            @RequestParam(name = "SortDir",defaultValue = "ASC",required = false)String sortDir

    ){
        final PageableResponse<CategoryDTO> allCategry = categoryService.getAllCategry(pageSize, pageNumber, sortBy, sortDir);
        return new ResponseEntity<>(allCategry,HttpStatus.OK);
    }
    //get single
    @GetMapping("/SingleCategory")
    public ResponseEntity<CategoryDTO> getSingleCategory(
            @RequestParam(name = "categoryId")String categoryId
    ){
        final CategoryDTO singleCategory = categoryService.getSingleCategory(categoryId);
        return new ResponseEntity<>(singleCategory,HttpStatus.OK);

    }


//upload image
    @PutMapping("/upload/{categoryId}")
    public ResponseEntity<ImageResponse> uploadImage(
            @PathVariable(name = "categoryId") String categoryId,
            @RequestParam(name = "Image")MultipartFile file
            ) throws IOException {
        final String fileupload = fileUploadService.fileupload(file, filePath);
        CategoryDTO categoryDTO = categoryService.getSingleCategory(categoryId);
        categoryDTO.setCategoryImage(fileupload);
        categoryService.updateCategory(categoryDTO,categoryId);
        imageResponse.setFileName(fileupload);
        imageResponse.setMassage("File is uploaded");
        imageResponse.setSuccess(true);
        imageResponse.setHttpStatus(HttpStatus.OK);
        return new ResponseEntity<>(imageResponse,HttpStatus.OK);
    }

    //serve image


}
