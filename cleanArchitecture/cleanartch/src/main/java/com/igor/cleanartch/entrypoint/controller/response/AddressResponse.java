package com.igor.cleanartch.entrypoint.controller.response;

import lombok.Data;

@Data
public class AddressResponse {
    private String street;

    private String city;
    private String state;
}
