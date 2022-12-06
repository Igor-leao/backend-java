package br.com.youtube.customer.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.youtube.customer.persistence.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
