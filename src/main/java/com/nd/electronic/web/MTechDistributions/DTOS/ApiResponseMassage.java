package com.nd.electronic.web.MTechDistributions.DTOS;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMassage {
    private String massage;
    private Boolean success;
    private org.springframework.http.HttpStatus HttpStatus;
}
