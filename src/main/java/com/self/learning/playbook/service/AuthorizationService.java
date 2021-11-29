package com.self.learning.playbook.service;

import com.self.learning.playbook.dto.request.LoginRequestDto;
import com.self.learning.playbook.dto.response.LoginResponseDto;

public interface AuthorizationService {
    LoginResponseDto authentication(LoginRequestDto requestDto);
}
