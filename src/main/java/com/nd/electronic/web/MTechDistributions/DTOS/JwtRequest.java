package com.nd.electronic.web.MTechDistributions.DTOS;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtRequest {

    private String userName;
    private String password;


}
