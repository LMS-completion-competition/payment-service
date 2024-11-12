package com.iprody.crm.paymentservice.services;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PaymentService {
    Mono<Payment> create(Payment payment);

    Mono<Payment> findByGuid(UUID id);
}
