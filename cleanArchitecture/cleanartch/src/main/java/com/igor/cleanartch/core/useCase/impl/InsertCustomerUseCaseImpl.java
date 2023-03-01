package com.igor.cleanartch.core.useCase.impl;

import com.igor.cleanartch.core.dataprovider.FindAddressByZipCode;
import com.igor.cleanartch.core.dataprovider.InsertCustomer;
import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.core.useCase.InsertCustomerUseCase;

public class InsertCustomerUseCaseImpl implements InsertCustomerUseCase {

    private final FindAddressByZipCode findAddressByZIpCode;

    private final InsertCustomer insertCustomer;

    public InsertCustomerUseCaseImpl(FindAddressByZipCode findAddressByZIpCode,
                                     InsertCustomer insertCustomer) {
        this.findAddressByZIpCode = findAddressByZIpCode;
        this.insertCustomer = insertCustomer;
    }
    @Override
    public void insert(Customer customer, String zipCode) {
        var address = findAddressByZIpCode.find(zipCode);
        customer.setAddress(address);
        insertCustomer.insert(customer);
    }
}
