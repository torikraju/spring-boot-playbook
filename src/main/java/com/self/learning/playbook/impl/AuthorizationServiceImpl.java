package com.self.learning.playbook.impl;

import com.self.learning.playbook.constant.SecurityConstant;
import com.self.learning.playbook.dto.request.LoginRequestDto;
import com.self.learning.playbook.dto.response.LoginResponseDto;
import com.self.learning.playbook.entity.User;
import com.self.learning.playbook.service.AuthorizationService;
import com.self.learning.playbook.service.UserService;
import com.self.learning.playbook.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @Override
    public LoginResponseDto authentication(LoginRequestDto requestDto) {
        LoginResponseDto responseDto = new LoginResponseDto();
        responseDto.setSuccess(true);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User user = userService.findUserByEmail(requestDto.getEmail());
            String tokens = JwtTokenUtil.generateToken(user, SecurityConstant.AUTH_TOKEN_SECRET, SecurityConstant.EXPIRATION_TIME_TOKEN);
            Date now = new Date(System.currentTimeMillis());
            Date expireDate = new Date(now.getTime() + SecurityConstant.EXPIRATION_TIME_TOKEN);
            responseDto.setExpiresIn(expireDate);
            responseDto.setToken(SecurityConstant.TOKEN_PREFIX + " " + tokens);
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setMessage("Invalid email or password");
        }
        return responseDto;
    }
}
