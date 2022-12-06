 package br.com.youtube.customer.model.response;

public class CustomerResponse {

	private Long id;
	private String name;
	private String document;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(Long id) {
		this.id = id;
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
