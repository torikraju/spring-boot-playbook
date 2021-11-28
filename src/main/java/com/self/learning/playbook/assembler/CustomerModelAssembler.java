package com.self.learning.playbook.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.self.learning.playbook.dto.CustomerDto;
import com.self.learning.playbook.entity.Customer;
import com.self.learning.playbook.resource.CustomerResource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerDto> {

    private ModelMapper modelMapper;

    public CustomerModelAssembler(ModelMapper modelMapper) {
        super(CustomerResource.class, CustomerDto.class);
        this.modelMapper=modelMapper;
    }

    @Override
    public CustomerDto toModel(Customer entity) {
        CustomerDto customerDto = instantiateModel(entity);
        modelMapper.map(entity, customerDto);
        return customerDto;
    }

    @Override
    public CollectionModel<CustomerDto> toCollectionModel(Iterable<? extends Customer> entities) {
        CollectionModel<CustomerDto> actorModels = super.toCollectionModel(entities);
        actorModels.add(linkTo(methodOn(CustomerResource.class).findAll()).withSelfRel());
        return actorModels;
    }


}
