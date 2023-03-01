package com.igor.cleanartch.entrypoint.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String zipCode;
}
