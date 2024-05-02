package com.nd.electronic.web.MTechDistributions.config;

import com.nd.electronic.web.MTechDistributions.DTOS.ApiResponseMassage;
import com.nd.electronic.web.MTechDistributions.DTOS.ImageResponse;
import com.nd.electronic.web.MTechDistributions.Entitys.PageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Configuration
public class ProjectConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
    @Bean
    public <T> PageableResponse<T> getPageableResponse(){
      return new PageableResponse<T>();
    }
    @Bean
    public ImageResponse getImageRes(){
        return new ImageResponse();
    }


    @Bean
    public ApiResponseMassage getApiRes(){
        return new ApiResponseMassage();
    }
}
