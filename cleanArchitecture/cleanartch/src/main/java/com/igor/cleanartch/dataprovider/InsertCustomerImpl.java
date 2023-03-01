package com.igor.cleanartch.dataprovider;

import com.igor.cleanartch.core.dataprovider.InsertCustomer;
import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.dataprovider.repository.CustomerRepository;
import com.igor.cleanartch.dataprovider.repository.mapper.CustomerEntityMapper;

public class InsertCustomerImpl implements InsertCustomer {

    private final  CustomerRepository customerRepository;

    private final CustomerEntityMapper customerEntityMapper;

    public InsertCustomerImpl(
            CustomerRepository customerRepository,
            CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
    }

    @Override
    public void insert(Customer customer) {
        var customerEnity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEnity);

    }
}
