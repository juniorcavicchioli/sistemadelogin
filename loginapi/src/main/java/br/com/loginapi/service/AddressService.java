package br.com.loginapi.service;

import br.com.loginapi.dto.response.AddressResponseDTO;
import br.com.loginapi.dto.update.AddressUpdateDTO;
import br.com.loginapi.model.Address;
import br.com.loginapi.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

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

    public Address convertUpdateDTOToAddress(AddressUpdateDTO addressUpdateDTO) {
        if (addressUpdateDTO == null)
            return null;
        return new Address(
                null,
                addressUpdateDTO.street(),
                addressUpdateDTO.number(),
                addressUpdateDTO.neightborhood(),
                addressUpdateDTO.city(),
                addressUpdateDTO.state(),
                addressUpdateDTO.zipcode(),
                addressUpdateDTO.country(),
                addressUpdateDTO.complement(),
                null
        );
    }

    public Address updateAddress(Long id, Address address) {
        if (address == null)
            return null;
        Address findedAddress = getAddressById(id).get();
        findedAddress.setStreet(address.getStreet());
        findedAddress.setNumber(address.getNumber());
        findedAddress.setNeighborhood(address.getNeighborhood());
        findedAddress.setCity(address.getCity());
        findedAddress.setState(address.getState());
        findedAddress.setZipcode(address.getZipcode());
        findedAddress.setCountry(address.getCountry());

        return addressRepository.save(findedAddress);
    }
}
