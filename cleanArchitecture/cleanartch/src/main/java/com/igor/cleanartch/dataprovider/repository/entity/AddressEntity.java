package com.igor.cleanartch.dataprovider.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class AddressEntity {

    @Id
    private String street;

    private String city;
    private String state;
}
