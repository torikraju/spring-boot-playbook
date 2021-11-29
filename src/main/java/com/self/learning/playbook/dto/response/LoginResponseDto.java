package com.self.learning.playbook.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class LoginResponseDto {
    private boolean success;
    private String message;
    private String token;
    private Date expiresIn;
    private String refreshToken;
}
