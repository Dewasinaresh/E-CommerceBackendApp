package com.nd.electronic.web.MTechDistributions.DTOS;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {

    private String token;
    private UserDTO userDTO;
}
