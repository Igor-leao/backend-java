package com.igor.cleanartch.core.useCase.impl;

import com.igor.cleanartch.core.dataprovider.FindAddressByZipCode;
import com.igor.cleanartch.core.dataprovider.UpdateCustomer;
import com.igor.cleanartch.core.domain.Customer;
import com.igor.cleanartch.core.useCase.FindCustomerByIdUseCase;
import com.igor.cleanartch.core.useCase.UpdateCustomerUseCase;

public class UpdateCustomerUseCaseImpl implements UpdateCustomerUseCase {
    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    private final FindAddressByZipCode findAddressByZipCode;

    private final UpdateCustomer updateCustomer;

    public UpdateCustomerUseCaseImpl(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            FindAddressByZipCode findAddressByZipCode,
            UpdateCustomer updateCustomer) {
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.findAddressByZipCode = findAddressByZipCode;
        this.updateCustomer = updateCustomer;
    }
    @Override
    public void update(Customer customer, String zipCode) {
        findCustomerByIdUseCase.find(customer.getId());
        var address = findAddressByZipCode.find(zipCode);
        customer.setAddress(address);
        updateCustomer.update(customer);

    }
}
