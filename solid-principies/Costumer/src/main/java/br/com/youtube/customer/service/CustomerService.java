package br.com.youtube.customer.service;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.model.response.CustomerResponse;

public interface CustomerService {

	CustomerResponse create(CustomerRequest CustomerRequest);
	
	Page<CustomerResponse>  getAll(Pageable pageble);
	Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest);
	Optional<CustomerResponse> get (Long id);
	boolean delete(Long id);
	
}
