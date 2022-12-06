package br.com.youtube.customer.ws.v1;

import java.awt.print.Pageable;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.model.response.CustomerResponse;
import br.com.youtube.customer.persistence.entity.Customer;
import br.com.youtube.customer.service.CustomerService;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

public class CustomerController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest){
		LOGGER.info("Requisição recebida");
		return ResponseEntity.ok(customerService.create(customerRequest));
	}
	
	@GetMapping
	public ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable){
		LOGGER.info("Buscandoos os registros");
		Page<CustomerResponse> customerResponses = customerService.getAll(pageable);
		return ResponseEntity.ok(customerResponses);
		
		@PutMapping("/{id}")
		public ResponseEntity<CustomerResponse> update(@PathVariable("id") Long id, CustomerRequest customerRequest) {
		LOGGER.info("Iniciando  a atualização");
		Optional<CustomerResponse> update = customerService.update(id, customerRequest);
		if(!update.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(update.get());
		}
	}
}
