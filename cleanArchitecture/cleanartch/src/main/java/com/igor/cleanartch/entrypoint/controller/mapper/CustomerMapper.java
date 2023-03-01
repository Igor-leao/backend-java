package com.igor.cleanartch.entrypoint.controller.mapper;

import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.entrypoint.controller.request.CustomerRequest;
import com.igor.cleanartch.entrypoint.controller.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "sping")
public interface CustomerMapper {

    @Mapping(target ="id", ignore = true)
    @Mapping(target ="address", ignore = true)
    @Mapping(target ="isValidCpf", ignore = true)
    Customer tocustomer(CustomerRequest customerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

}
