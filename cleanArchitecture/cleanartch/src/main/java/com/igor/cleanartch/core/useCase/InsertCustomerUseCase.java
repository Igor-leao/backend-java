package com.igor.cleanartch.core.useCase;

import com.igor.cleanartch.core.domain.Customer;

public interface InsertCustomerUseCase {
    void insert(Customer customer, String zipCode);
}
