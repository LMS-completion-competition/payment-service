package com.iprody.crm.paymentservice.service;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentCurrency;
import com.iprody.crm.paymentservice.exception.PaymentNotFoundException;
import com.iprody.crm.paymentservice.repository.PaymentRepository;
import com.iprody.crm.paymentservice.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    PaymentRepository paymentRepository;
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    public void whenCreate_ThenPaymentShouldBeCreated() {
        Payment payment = Payment.builder()
                .amount(BigDecimal.valueOf(200L))
                .currency(PaymentCurrency.USD)
                .build();
        when(paymentRepository.save(ArgumentMatchers.any(Payment.class))).thenReturn(
                Mono.just(Payment.builder()
                        .id(1L)
                        .guid(UUID.randomUUID())
                        .amount(payment.getAmount())
                        .inquiryReferenceId(payment.getInquiryReferenceId())
                        .currency(payment.getCurrency())
                        .build()));

        Mono<Payment> createdPaymentMono = paymentService.create(payment);
        StepVerifier
                .create(createdPaymentMono)
                .consumeNextWith(
                        createdPayment -> {
                            assertThat(createdPayment.getId()).isNotNull();
                            assertThat(createdPayment.getGuid()).isNotNull();
                            assertThat(createdPayment.getCurrency()).isEqualTo(PaymentCurrency.USD);
                            assertThat(createdPayment.getAmount()).isEqualTo(payment.getAmount());
                        })
                .verifyComplete();
    }

    @Test
    public void whenFindById_ThenReturnPayment() {
        UUID existingPaymentId = UUID.randomUUID();
        Payment existingPayment = Payment.builder()
                .guid(existingPaymentId)
                .amount(BigDecimal.valueOf(200L))
                .currency(PaymentCurrency.USD)
                .build();
        when(paymentRepository.findByGuid(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Mono.just(existingPayment));
        Mono<Payment> foundPaymentMono = paymentService.findByGuid(existingPaymentId);
        StepVerifier.create(foundPaymentMono)
                .consumeNextWith(foundPayment -> assertThat(foundPayment).isNotNull())
                .verifyComplete();
    }

    @Test
    public void givenNotExistingId_WhenFindById_ThenReturnMonoError() {
        UUID nonExistingPaymentId = UUID.randomUUID();
        String exceptionMessage = String.format("Payment by id %s not found", nonExistingPaymentId);
        when(paymentRepository.findByGuid(eq(nonExistingPaymentId)))
                .thenReturn(Mono.empty());
        Mono<Payment> monoWithError = paymentService.findByGuid(nonExistingPaymentId);
        StepVerifier.create(monoWithError)
                .consumeErrorWith(error -> {
                    assertThat(error).hasMessage(exceptionMessage);
                    assertThat(error).isInstanceOf(PaymentNotFoundException.class);
                })
                .verify();
    }

}
