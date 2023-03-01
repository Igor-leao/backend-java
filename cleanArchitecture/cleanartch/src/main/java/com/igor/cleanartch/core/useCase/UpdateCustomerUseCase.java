package com.igor.cleanartch.core.useCase;

import com.igor.cleanartch.core.domain.Customer;

public interface UpdateCustomerUseCase {
    void update(Customer customer, String zipCode);
}
