package br.com.youtube.customer.service;


import java.awt.print.Pageable;
import java.util.Optional;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.model.response.CustomerResponse;
import br.com.youtube.customer.persistence.entity.Customer;
import br.com.youtube.customer.persistence.repository.CustomerRepository;
import br.com.youtube.customer.service.Mapper.Mapper;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private Mapper<CustomerRequest, Customer> requestMapper ;
	
	@Autowired
	private Mapper<Customer, CustomerResponse>  responseMapper;
	
	@Override
	public CustomerResponse create(CustomerRequest CustomerRequest) {
		LOGGER.info("Criando um registro do cliente");
		Assert.notNull(CustomerRequest, "Request Inválida");
		Customer customer = this.requestMapper.map(CustomerRequest);
			return customerRepository.saveAndFlush(customer).map((Customer input) -> this.responseMapper.map(input));
	}
	/*
	 * O VINCULO DE SERVICE COM SERVICEIMPL É UM DESIGN PATERNS
	 */
	@Override
	public Page<CustomerResponse> getAll(Pageable pageable) {
		LOGGER.info("Buscando todos os registros");
		Assert.notNull(pageable, "Página inválida");
		return customerRepository.findAll(pageable).map(customer -> this.responseMapper.map(customer));
	}

	@Override
	public Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest) {
		LOGGER.info("Atualizando registros");
		Assert.notNull(id, "Página inválida");
		Customer customerUpdate = this.requestMapper.map(customerRequest);
		return customerRepository.findById(id).map(customer -> {
			customer.setDocument(customerUpdate.getDocument());
			customer.setName(customerUpdate.getName());
			return this.responseMapper.map(customerRepository.saveAndFlush(customer));
		});
		
	}

	@Override
	public Optional<CustomerResponse> get(Long id) {
		LOGGER.info("Buscando registros");
		Assert.notNull(id, "Id inválido");
		return customerRepository.findById(id).map(this.responseMapper::map);
	}

	@Override
	public boolean delete(Long id) {
		LOGGER.info("Removendo registros");
		Assert.notNull(id, "Id inválido");
		try {
			customerRepository.deleteById(id);
			return true;
			} catch (Exception e) {
				LOGGER.warn("Erro ao remover o registro {}", id);
			}
		return false;
	}
}
