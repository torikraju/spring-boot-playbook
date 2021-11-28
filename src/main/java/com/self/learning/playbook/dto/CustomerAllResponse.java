package com.self.learning.playbook.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.PagedModel;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAllResponse extends ResponseDto {
    PagedModel<CustomerDto> data;
}
