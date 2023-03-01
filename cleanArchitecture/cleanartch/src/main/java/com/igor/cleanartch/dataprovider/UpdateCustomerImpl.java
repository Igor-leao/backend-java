package com.igor.cleanartch.dataprovider;

import com.igor.cleanartch.core.dataprovider.UpdateCustomer;
import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.dataprovider.repository.CustomerRepository;
import com.igor.cleanartch.dataprovider.repository.mapper.CustomerEntityMapper;

public class UpdateCustomerImpl implements UpdateCustomer {

    private CustomerRepository customerRepository;

    private CustomerEntityMapper customerEntityMapper;
    public UpdateCustomerImpl(CustomerRepository customerRepository,
                              CustomerEntityMapper customerEntityMapper) {
        this.customerRepository = customerRepository;
        this.customerEntityMapper = customerEntityMapper;
    }
    @Override
    public void update(Customer customer) {
        var customerEntity = customerEntityMapper.toCustomerEntity(customer);
        customerRepository.save(customerEntity);

    }
}
