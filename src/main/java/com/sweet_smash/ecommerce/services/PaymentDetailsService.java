package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.PaymentDetailsDto;
import com.sweet_smash.ecommerce.entities.PaymentDetails;
import com.sweet_smash.ecommerce.entities.PaymentType;
import com.sweet_smash.ecommerce.repositories.PaymentDetailsRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDetailsService {

    private final PaymentDetailsRepository detailsRepository;
    private final PaymentTypeService paymentTypeService;

    public PaymentDetailsService(PaymentDetailsRepository detailsRepository,
                                 PaymentTypeService paymentTypeService) {
        this.detailsRepository = detailsRepository;
        this.paymentTypeService = paymentTypeService;
    }

    public PaymentDetails createPaymentDetail(double amount, boolean status, long paymentTypeId) {
        PaymentType paymentType = paymentTypeService.getById(paymentTypeId);
        PaymentDetails details = new PaymentDetails(amount, status, paymentType);
        return detailsRepository.save(details);
    }
}
