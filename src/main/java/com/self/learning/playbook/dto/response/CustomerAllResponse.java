package com.self.learning.playbook.dto.response;

import com.self.learning.playbook.dto.CustomerDto;
import com.self.learning.playbook.dto.ResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.PagedModel;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAllResponse extends ResponseDto {
    PagedModel<CustomerDto> data;
}
