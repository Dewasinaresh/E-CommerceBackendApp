package com.nd.electronic.web.MTechDistributions.Services.impl;

import com.nd.electronic.web.MTechDistributions.DTOS.CategoryDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.CategoryEntity;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Repositories.CategoryRepo;
import com.nd.electronic.web.MTechDistributions.Services.CategoryService;
import com.nd.electronic.web.MTechDistributions.Utility.GeneratePageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper model;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        String catId;
        catId= String.valueOf(UUID.randomUUID());
        categoryDTO.setCategoryId(catId);
        final CategoryEntity categoryEntity = model.map(categoryDTO, CategoryEntity.class);
        CategoryEntity saveCategoryEntity= categoryRepo.save(categoryEntity);
        return model.map(saveCategoryEntity, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, String categoryId) {
        final CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category is not found"));
         category.setTitle(categoryDTO.getTitle());
         category.setDescription(categoryDTO.getDescription());
         category.setCategoryImage(categoryDTO.getCategoryImage());
        return model.map(category,CategoryDTO.class);
    }

    @Override
    public void deleteCategory(String categoryId) {

        final CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category is not found"));
        categoryRepo.delete(category);
    }

    @Override
    public PageableResponse<CategoryDTO> getAllCategry(int pageSize, int pageNumber, String sortBy,String sortDir ) {
        Sort sort=(sortDir.equalsIgnoreCase("Desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        final Page<CategoryEntity> PageData = categoryRepo.findAll(pageable);
        final PageableResponse<CategoryDTO> categoryDTOPageableResponse = GeneratePageableResponse.generatePageResponse(PageData, CategoryDTO.class);
        return categoryDTOPageableResponse ;
    }


    @Override
    public CategoryDTO getSingleCategory(String categoryId) {
        final CategoryEntity category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category is not found "));
        return model.map(category,CategoryDTO.class);
    }
}
