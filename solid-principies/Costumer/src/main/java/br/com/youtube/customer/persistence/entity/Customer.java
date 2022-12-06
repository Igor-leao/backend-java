package br.com.youtube.customer.persistence.entity;

import java.util.function.Function;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name =  "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "document", nullable = false)
	private String document;
	 
	
	public <R> R map(Function<Customer, R> func) {
		return func.apply(this);
		
	}

//	no  controller tenho que utilizar o 
	@restController
	@requestMapping onde passo meu endpoint(/"boocking")
	@requestBody o corpo da requisição
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDocument() {
		return document;
	}


	public void setDocument(String document) {
		this.document = document;
	}


	public Long getId() {
		return id;
	}
}
