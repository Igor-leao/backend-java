package com.igor.cleanartch.dataprovider.repository.mapper;

import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.dataprovider.repository.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustomerEntity(Customer customer);

    Customer toCustomerRepository(CustomerEntity customerEntity);
}
