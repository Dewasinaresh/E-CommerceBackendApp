package com.nd.electronic.web.MTechDistributions.Services;

import com.nd.electronic.web.MTechDistributions.DTOS.ProductDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;

import java.util.List;

public interface ProductService {
    //Create
    ProductDTO createProduct(ProductDTO product);
    //update
    ProductDTO updateProduct(ProductDTO productDTO,String productId);
    //delete
    void deleteProduct(String productId);
    //find All
    PageableResponse<ProductDTO> getAllProduct(int pageNumber,int pageSize,String SortDir);
    //get single
    ProductDTO getSingleProduct(String productId);
    //getl all live
    PageableResponse<ProductDTO> getLiveProduct(int pageNumber,int pageSize,String SortDir);


}
