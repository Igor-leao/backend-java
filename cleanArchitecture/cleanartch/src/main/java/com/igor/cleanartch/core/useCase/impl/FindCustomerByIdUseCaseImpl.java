package com.igor.cleanartch.core.useCase.impl;

import com.igor.cleanartch.core.dataprovider.FindCustomerById;
import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.core.useCase.FindCustomerByIdUseCase;

public class FindCustomerByIdUseCaseImpl implements FindCustomerByIdUseCase {

    private final FindCustomerById findCustomerById;

    public FindCustomerByIdUseCaseImpl(FindCustomerById findCustomerById) {
        this.findCustomerById = findCustomerById;
    }
    @Override
    public Customer find(String id) {
        return findCustomerById.find(id).orElseThrow(() ->
                new RuntimeException(("Customer not found")));
    }
}
