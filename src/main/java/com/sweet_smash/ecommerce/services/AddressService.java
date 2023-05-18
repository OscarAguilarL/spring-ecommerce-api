package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.AddressDto;
import com.sweet_smash.ecommerce.entities.Address;
import com.sweet_smash.ecommerce.entities.User;
import com.sweet_smash.ecommerce.repositories.AddressRepository;
import com.sweet_smash.ecommerce.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public Address getUserAddress(Long userId) {
        return addressRepository.findAddressByUserId(userId);
    }

    public Address createAddress(AddressDto addressDto) {
        User user = userRepository.findById(addressDto.getUserId()).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        Address address = new Address(
                addressDto.getStreet(),
                addressDto.getNumber(),
                addressDto.getZipcode(),
                user
        );

        return addressRepository.save(address);
    }
}
