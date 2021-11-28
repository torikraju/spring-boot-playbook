package com.self.learning.playbook.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@Data
public class CustomerDto extends RepresentationModel<CustomerDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
}
