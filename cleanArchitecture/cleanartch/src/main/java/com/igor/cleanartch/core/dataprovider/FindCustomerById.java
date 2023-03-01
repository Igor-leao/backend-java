package com.igor.cleanartch.core.dataprovider;

import com.igor.cleanartch.core.domain.Customer;

import java.util.Optional;

public interface FindCustomerById {

    Optional<Customer> find(final String id);
}
