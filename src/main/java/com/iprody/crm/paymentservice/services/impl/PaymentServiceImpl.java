package com.iprody.crm.paymentservice.services.impl;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import com.iprody.crm.paymentservice.repositories.PaymentRepository;
import com.iprody.crm.paymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Mono<Payment> create(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Mono<Payment> findById(Long id) {
        return paymentRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new IllegalArgumentException(
                                String.format("Payment by id %s not found", id)
                        ))
                );
    }
}
