package com.igor.cleanartch.dataprovider;

import com.igor.cleanartch.core.dataprovider.FindCustomerById;
import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.dataprovider.repository.CustomerRepository;
import com.igor.cleanartch.dataprovider.repository.mapper.CustomerEntityMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class FindCustomerByIdImpl implements FindCustomerById {

    private CustomerRepository customerRepository;

    private CustomerEntityMapper customerEntityMapper;

    public FindCustomerByIdImpl(
            CustomerRepository customerRepository,
            CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
    }
    @Override
    public Optional<Customer> find(String id) {
        var customerEntity = customerRepository.findById(id);
        return customerEntity.map(entity -> customerEntityMapper.toCustomerRepository(entity));
    }
}
