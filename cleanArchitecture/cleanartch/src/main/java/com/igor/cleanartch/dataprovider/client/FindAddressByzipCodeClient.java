package com.igor.cleanartch.dataprovider.client;


import com.igor.cleanartch.dataprovider.client.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
      name = "FindAddressByZipCodeClient",
      url = "${igor.client.address.url}"
)
public interface FindAddressByzipCodeClient {
@GetMapping("/{zipCode}")
    AddressResponse find(@PathVariable("zipCOde") String zipCode);
}
