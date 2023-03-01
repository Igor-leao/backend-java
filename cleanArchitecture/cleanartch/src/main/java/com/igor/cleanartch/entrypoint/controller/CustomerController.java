package com.igor.cleanartch.entrypoint.controller;

import com.igor.cleanartch.core.dataprovider.UpdateCustomer;
import com.igor.cleanartch.core.useCase.FindCustomerByIdUseCase;
import com.igor.cleanartch.core.useCase.InsertCustomerUseCase;
import com.igor.cleanartch.core.useCase.UpdateCustomerUseCase;
import com.igor.cleanartch.entrypoint.controller.request.CustomerRequest;
import com.igor.cleanartch.entrypoint.controller.mapper.CustomerMapper;
import com.igor.cleanartch.entrypoint.controller.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/customers")
@RestController
public class CustomerController {

    private final InsertCustomerUseCase insertCustomerUseCase;
    private final CustomerMapper customerMapper;

    private final FindCustomerByIdUseCase findCustomerByIdUseCase;

    private final UpdateCustomerUseCase updateCustomerUseCase;



    public CustomerController(
            InsertCustomerUseCase insertCustomerUseCase,
            CustomerMapper customerMapper,
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            UpdateCustomerUseCase updateCustomerUseCase){
        this.insertCustomerUseCase = insertCustomerUseCase;
        this.customerMapper = customerMapper;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity <Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerMapper.tocustomer(customerRequest);
        insertCustomerUseCase.insert(customer, customerRequest.getZipCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final String id) {
        var customer = findCustomerByIdUseCase.find(id);
        var customerResponse = customerMapper.toCustomerResponse(customer);
       return ResponseEntity.ok().body(customerResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final String id,
           @Valid @RequestBody CustomerRequest customerRequest) {

        var customer = customerMapper.tocustomer(customerRequest);
        customer.setId(id);
        updateCustomerUseCase.update(customer, customerRequest.getZipCode());
        return ResponseEntity.noContent().build();

    }

}
