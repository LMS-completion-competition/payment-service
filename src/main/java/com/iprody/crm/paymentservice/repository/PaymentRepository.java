package com.iprody.crm.paymentservice.repository;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import org.springframework.data.domain.Limit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
    Mono<Payment> findByGuid(UUID guid);
}
