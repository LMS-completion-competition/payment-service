package com.iprody.crm.paymentservice.repositories;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
}
