package com.self.learning.playbook.dto.internal;

import lombok.Data;

@Data
public class ValidateToken {
    private Boolean status;
    private String message;
    private String email;
}
