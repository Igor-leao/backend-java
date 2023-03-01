package com.igor.cleanartch.dataprovider.repository.entity;

import com.igor.cleanartch.dataprovider.repository.entity.AddressEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "costumers")
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String cpf;
    private AddressEntity address;
    private Boolean isValidCpf;

}
