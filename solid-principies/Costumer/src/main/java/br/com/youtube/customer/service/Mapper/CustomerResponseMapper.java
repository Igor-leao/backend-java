package br.com.youtube.customer.service.Mapper;

import br.com.youtube.customer.model.response.CustomerResponse;
import br.com.youtube.customer.persistence.entity.Customer;

public class CustomerResponseMapper implements Mapper<Customer, CustomerResponse>{

	@Override
	public CustomerResponse map(Customer input) {
		if(input == null) {
			return null;
		}
		
		CustomerResponse customerResponse = new CustomerResponse();
		customerResponse.setId(input.getId());
		customerResponse.setName(input.getName());
		customerResponse.setDocument(input.getDocument());
		
		return customerResponse;
	}

}
