package com.nd.electronic.web.MTechDistributions.Services;

import com.nd.electronic.web.MTechDistributions.DTOS.CategoryDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;

import java.util.List;

public interface CategoryService {
    //create
    public CategoryDTO createCategory(CategoryDTO categoryDTO);
    //update
    public CategoryDTO updateCategory(CategoryDTO categoryDTO,String categoryId);
    //delete
    public void deleteCategory(String categoryId);
    //getAll
    public PageableResponse<CategoryDTO> getAllCategry(int pageSize, int pageNumber, String sortBy,String sortDir);
    //get single
    public CategoryDTO getSingleCategory(String categoryId);
    //

}
