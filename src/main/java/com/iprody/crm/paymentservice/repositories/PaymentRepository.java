package com.iprody.crm.paymentservice.repositories;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, Long> {
}
