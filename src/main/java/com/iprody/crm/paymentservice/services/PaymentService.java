package com.iprody.crm.paymentservice.services;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import reactor.core.publisher.Mono;

public interface PaymentService {
    Mono<Payment> create(Payment payment);

    Mono<Payment> findById(Long id);
}
