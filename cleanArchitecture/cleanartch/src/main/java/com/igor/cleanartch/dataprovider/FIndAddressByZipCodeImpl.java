package com.igor.cleanartch.dataprovider;

import com.igor.cleanartch.core.dataprovider.FindAddressByZipCode;
import com.igor.cleanartch.core.domain.Address;
import com.igor.cleanartch.dataprovider.client.FindAddressByzipCodeClient;
import com.igor.cleanartch.dataprovider.client.mapper.AddressResponseMaper;

public class FIndAddressByZipCodeImpl implements FindAddressByZipCode {

    private final FindAddressByzipCodeClient findAddressByzipCodeClient;

private final AddressResponseMaper addressResponseMaper;

   public FIndAddressByZipCodeImpl(
           FindAddressByzipCodeClient findAddressByzipCodeClient,
           AddressResponseMaper addressResponseMaper
   ) {
       this.addressResponseMaper= addressResponseMaper;
       this.findAddressByzipCodeClient = findAddressByzipCodeClient;
   }


    @Override
    public Address find(String zipCode) {
        var addressResponse = findAddressByzipCodeClient.find(zipCode);
        return addressResponseMaper.toAddress(addressResponse);
    }
}
