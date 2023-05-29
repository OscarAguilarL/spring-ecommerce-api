package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.PaymentTypeDto;
import com.sweet_smash.ecommerce.entities.PaymentType;
import com.sweet_smash.ecommerce.repositories.PaymentTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PaymentTypeService {

    private final PaymentTypeRepository repository;

    public PaymentTypeService(PaymentTypeRepository repository) {
        this.repository = repository;
    }

    public List<PaymentType> getAll() {
        return this.repository.findAll();
    }

    public PaymentType getById(long id) {
        return this.repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment type not found")
        );
    }

    public PaymentType create(PaymentTypeDto dto) {
        PaymentType paymentType = new PaymentType(dto.getName(), dto.getLabel());

        return this.repository.save(paymentType);
    }
}
