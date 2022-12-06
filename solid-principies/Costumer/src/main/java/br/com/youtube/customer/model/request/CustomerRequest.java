package br.com.youtube.customer.model.request;

import javax.validation.constraints.NotBlank;

public class CustomerRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String document;
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
}
