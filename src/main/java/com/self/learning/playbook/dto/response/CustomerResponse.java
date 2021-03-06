package com.self.learning.playbook.dto.response;

import com.self.learning.playbook.dto.CustomerDto;
import com.self.learning.playbook.dto.ResponseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.PagedModel;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerResponse extends ResponseDto implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    private CustomerDto data;
}
