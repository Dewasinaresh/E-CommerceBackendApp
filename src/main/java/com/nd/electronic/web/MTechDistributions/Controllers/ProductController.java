package com.nd.electronic.web.MTechDistributions.Controllers;


import com.nd.electronic.web.MTechDistributions.DTOS.ApiResponseMassage;
import com.nd.electronic.web.MTechDistributions.DTOS.ImageResponse;
import com.nd.electronic.web.MTechDistributions.DTOS.ProductDTO;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import com.nd.electronic.web.MTechDistributions.Services.FileUploadService;
import com.nd.electronic.web.MTechDistributions.Services.ProductService;
import com.nd.electronic.web.MTechDistributions.exceptionUtils.ResouceNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
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
import java.io.OutputStream;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private ApiResponseMassage apiResponseMassage;
    @Autowired
    private FileUploadService fileUploadService;
    //create
    @Value("$(product.profiler.file.path)")
    private String filePath;


    @PostMapping("/newProduct")
    public ResponseEntity<ProductDTO> createProduct(
           @Validated @RequestBody ProductDTO productDTO
    ){
        final ProductDTO newCreatedProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(newCreatedProduct, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(
        @RequestBody ProductDTO productDTO,
        @PathVariable(name = "productId") String productId

    ){
        final ProductDTO updatedProduct = productService.updateProduct(productDTO, productId);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/delete/")
    public ResponseEntity<ApiResponseMassage> deleteProduct(
            @RequestParam(name ="productId") String productId
    )
    {
        productService.deleteProduct(productId);
        final ApiResponseMassage APIResDelete = ApiResponseMassage.builder()
                .massage("Deleted")
                .success(true)
                .HttpStatus(HttpStatus.OK).build();
        return new ResponseEntity<>(APIResDelete,HttpStatus.OK);
    }
    //getAll
    @GetMapping("/getAll")
    public ResponseEntity<PageableResponse<ProductDTO>> getAll(
        @RequestParam(name = "pageNumber",defaultValue = "0",required = false) int pageNumber,
        @RequestParam(name = "pageSize",defaultValue = "1",required = false) int pageSize,
        @RequestParam(name = "SortDir",defaultValue = "asc",required = false) String sortDir
    ){
        final PageableResponse<ProductDTO> allProduct = productService.getAllProduct(pageNumber, pageSize, sortDir);
        return new ResponseEntity<>(allProduct,HttpStatus.OK);

    }
    //getAllLive
    @GetMapping("/getLiveProduct")
    public ResponseEntity<PageableResponse<ProductDTO>> getLiveprdct(
            @RequestParam(name = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(name = "pageSize",defaultValue = "1",required = false) int pageSize,
            @RequestParam(name = "SortDir",defaultValue = "asc",required = false) String sortDir
    ){
        final PageableResponse<ProductDTO> allProduct = productService.getLiveProduct(
                pageNumber, pageSize, sortDir);
        return new ResponseEntity<>(allProduct,HttpStatus.OK);

    }

    @GetMapping("/product/{productId}")
    public  ResponseEntity<ProductDTO> getSinglePrdct(
            @PathVariable(name="productId") String productId
    ){
        final ProductDTO singleProduct = productService.getSingleProduct(productId);
        return new ResponseEntity<>(singleProduct,HttpStatus.OK);
    }


    //upload Image
    @PutMapping("ImageUpload")
    public ResponseEntity<ImageResponse> uploadImage(
            @RequestParam MultipartFile files
    ) throws IOException {
        final String originalFilename = files.getOriginalFilename();
        final String fileupload = fileUploadService.fileupload(files, filePath);
        final ImageResponse imageIsUploaded = ImageResponse.builder().
                massage("image is uploaded").
                success(true).
                fileName(fileupload).
                HttpStatus(HttpStatus.OK).
                build();
        return new ResponseEntity<>(imageIsUploaded,HttpStatus.OK);

    }

    //serveImage
    @GetMapping("/{productId}")
    public void serveImage(
            @PathVariable(name = "productId") String productId,
            HttpServletResponse response
    ) throws IOException {
        try {
            final ProductDTO singleProduct = productService.getSingleProduct(productId);
            final String fileName = singleProduct.getFileName();
            final InputStream servefile = fileUploadService.servefile(filePath, fileName);
            response.setContentType(String.valueOf(MediaType.IMAGE_PNG));
            StreamUtils.copy(servefile,response.getOutputStream());

        } catch (IOException e) {
            throw new ResouceNotFoundException();
        }

    }




}
