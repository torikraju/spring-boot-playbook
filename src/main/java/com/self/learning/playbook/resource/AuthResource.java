package com.self.learning.playbook.resource;

import com.self.learning.playbook.annotation.ApiController;
import com.self.learning.playbook.dto.request.LoginRequestDto;
import com.self.learning.playbook.dto.response.LoginResponseDto;
import com.self.learning.playbook.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@ApiController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthResource {

    private final AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginRequestDto requestDto) {
        return new ResponseEntity<>(authorizationService.authentication(requestDto), HttpStatus.OK);
    }


}
