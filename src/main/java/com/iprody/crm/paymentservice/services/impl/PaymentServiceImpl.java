package com.iprody.crm.paymentservice.services.impl;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import com.iprody.crm.paymentservice.exceptions.PaymentNotFoundException;
import com.iprody.crm.paymentservice.repositories.PaymentRepository;
import com.iprody.crm.paymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Mono<Payment> create(Payment payment) {
        payment.setGuid(UUID.randomUUID());
        return paymentRepository.save(payment);
    }

    @Override
    public Mono<Payment> findByGuid(UUID id) {
        return paymentRepository.findByGuid(id)
                .switchIfEmpty(
                        Mono.error(new PaymentNotFoundException(
                                String.format("Payment by id %s not found", id)
                        ))
                );
    }
}
