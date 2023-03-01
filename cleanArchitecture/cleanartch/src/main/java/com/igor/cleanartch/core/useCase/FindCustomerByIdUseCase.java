package com.igor.cleanartch.core.useCase;

import com.igor.cleanartch.core.domain.Customer;

public interface FindCustomerByIdUseCase {
    Customer find(final String id);
}
