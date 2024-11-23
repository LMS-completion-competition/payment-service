package com.iprody.crm.paymentservice.repositories;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
    Mono<Payment> findByGuid(UUID guid);
}
