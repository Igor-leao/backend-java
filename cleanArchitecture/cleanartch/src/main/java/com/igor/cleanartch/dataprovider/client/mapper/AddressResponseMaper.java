package com.igor.cleanartch.dataprovider.client.mapper;

import com.igor.cleanartch.core.domain.Address;
import com.igor.cleanartch.dataprovider.client.response.AddressResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressResponseMaper {
    Address toAddress(AddressResponse addressResponse);

}
