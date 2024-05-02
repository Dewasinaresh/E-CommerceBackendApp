package com.nd.electronic.web.MTechDistributions.DTOS;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {
    private String fileName;
    private String massage;
    private Boolean success;
    private org.springframework.http.HttpStatus HttpStatus;
}
