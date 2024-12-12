package com.grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@AllArgsConstructor
public class AuthenticateResponseDto {
    private String jwtToken;
}
