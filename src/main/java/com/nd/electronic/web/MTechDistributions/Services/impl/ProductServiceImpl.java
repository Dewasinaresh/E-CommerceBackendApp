package com.nd.electronic.web.MTechDistributions.Services.impl;

import com.nd.electronic.web.MTechDistributions.DTOS.ImageResponse;
import com.nd.electronic.web.MTechDistributions.DTOS.ProductDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Entitys.ProductEntity;
import com.nd.electronic.web.MTechDistributions.Repositories.ProductRepo;
import com.nd.electronic.web.MTechDistributions.Services.ProductService;
import com.nd.electronic.web.MTechDistributions.Utility.GeneratePageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDTO createProduct(ProductDTO product) {
        String prdId= String.valueOf(UUID.randomUUID());
        product.setId(prdId);
        final ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        final ProductEntity savedProductEntity = productRepo.save(productEntity);
        return modelMapper.map(savedProductEntity, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO,String productId) {
        //Get old product
        final ProductEntity productEntity = productRepo.findById(productId).orElseThrow(()-> new RuntimeException("User is not found "));
        //update product
        productEntity.setProductTitle(productDTO.getProductTitle());
        productEntity.setDiscriptions(productDTO.getDiscriptions());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setLive(productDTO.isLive());
        productEntity.setStock(productDTO.isStock());
        productEntity.setAvailableQuantity(productDTO.getAvailableQuantity());
        productEntity.setProductDate(productDTO.getProductDate());
        productEntity.setDiscountPrice(productDTO.getDiscountPrice());
        // save product
        final ProductEntity productEntityUpdated = productRepo.save(productEntity);
        return modelMapper.map(productEntityUpdated, ProductDTO.class);

    }

    @Override
    public void deleteProduct(String productId) {
        final ProductEntity productEntity = productRepo.findById(productId).orElseThrow(()-> new RuntimeException("User is not found "));
        productRepo.delete(productEntity);
    }

    @Override
    public PageableResponse<ProductDTO> getAllProduct(int pageNumber, int pageSize, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by("productTitle").ascending():Sort.by("title").descending();
        final Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        final Page<ProductEntity> productList = productRepo.findAll(pageRequest);
        final PageableResponse<ProductDTO> productDTOPageableResponse = GeneratePageableResponse.generatePageResponse(productList, ProductDTO.class);
        return productDTOPageableResponse;
    }

    @Override
    public ProductDTO getSingleProduct(String productId) {
        final ProductEntity productEntity = productRepo.findById(productId).orElseThrow(()-> new RuntimeException("User is not found "));
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    @Override
    public PageableResponse<ProductDTO> getLiveProduct(int pageNumber,int pageSize,String sortDir) {
      Sort sort = (sortDir.equalsIgnoreCase("desc"))?Sort.by("title").descending():Sort.by("title").ascending();
        final Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        final Page<ProductEntity> liveProducts = productRepo.findByliveTrue(pageRequest);
        final PageableResponse<ProductDTO> productDTOPageableResponse = GeneratePageableResponse.generatePageResponse(liveProducts, ProductDTO.class);
        return productDTOPageableResponse;
    }






}
