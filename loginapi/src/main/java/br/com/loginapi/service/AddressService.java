package br.com.loginapi.service;

import br.com.loginapi.dto.AddressResponseDTO;
import br.com.loginapi.model.Address;
import br.com.loginapi.model.UserProfile;
import br.com.loginapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;
    public Address registerAddress(Address address){
        return addressRepository.save(address);
    }
    public AddressResponseDTO convertToAddressResponseDTO(Address address){
        return new AddressResponseDTO(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getState(),
                address.getCountry(),
                address.getComplement()
        );
    }
}
