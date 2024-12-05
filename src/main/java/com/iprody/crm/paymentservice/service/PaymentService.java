package com.iprody.crm.paymentservice.service;

import com.iprody.crm.paymentservice.domain.entities.Payment;

import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import org.springframework.data.domain.Limit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PaymentService {
    Mono<Payment> create(Payment payment);

    Mono<Payment> findByGuid(UUID id);
}