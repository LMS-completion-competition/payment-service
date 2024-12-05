package com.iprody.crm.paymentservice.service.impl;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import com.iprody.crm.paymentservice.exception.PaymentNotFoundException;
import com.iprody.crm.paymentservice.repository.PaymentRepository;
import com.iprody.crm.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
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
