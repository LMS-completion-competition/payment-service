package com.iprody.crm.paymentservice.mediator;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentCurrency;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import com.iprody.crm.paymentservice.dto.PaymentCreateDto;
import com.iprody.crm.paymentservice.dto.PaymentDto;
import com.iprody.crm.paymentservice.mapper.PaymentMapper;
import com.iprody.crm.paymentservice.mapper.PaymentMapperImpl;
import com.iprody.crm.paymentservice.service.PaymentService;
import com.iprody.crm.paymentservice.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;


public class PaymentMediatorUnitTest {

    private PaymentService paymentService;
    private PaymentMediator paymentMediator;
    private Payment mockPayment;
    private PaymentCreateDto mockPaymentCreateDto;

    @BeforeEach
    public void setUp() {
        PaymentMapper paymentMapper = new PaymentMapperImpl();
        paymentService = mock(PaymentServiceImpl.class);
        paymentMediator = new PaymentMediator(paymentService, paymentMapper);
        UUID inquiryReferenceId = UUID.randomUUID();
        mockPaymentCreateDto = new PaymentCreateDto(
                inquiryReferenceId,
                BigDecimal.valueOf(250L),
                PaymentCurrency.USD);
        mockPayment = Payment.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(250L))
                .currency(PaymentCurrency.USD)
                .status(PaymentStatus.NEW)
                .inquiryReferenceId(inquiryReferenceId)
                .createdAt(Instant.now())
                .transactionReferenceId(1L)
                .build();
    }

    @Test
    public void whenCreate_ThenPaymentShouldBeCreated() {
        when(paymentService.create(ArgumentMatchers.any(Payment.class)))
                .thenReturn(Mono.just(mockPayment));
        Mono<PaymentDto> createdPaymentMono = paymentMediator.create(mockPaymentCreateDto);
        StepVerifier
                .create(createdPaymentMono)
                .consumeNextWith(
                        createdPayment -> {
                            assertThat(createdPayment.currency()).isEqualTo(PaymentCurrency.USD);
                            assertThat(createdPayment.amount()).isEqualTo(mockPayment.getAmount());
                            assertThat(createdPayment.inquiryReferenceId()).isEqualTo(mockPayment.getInquiryReferenceId());
                        })
                .verifyComplete();
    }

    @Test
    public void whenFindById_ThenReturnPayment() {
        when(paymentService.findByGuid(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Mono.just(mockPayment));
        Mono<PaymentDto> foundPaymentMono = paymentMediator.findById(UUID.randomUUID());
        StepVerifier
                .create(foundPaymentMono)
                .consumeNextWith(
                        foundPayment -> {
                            assertThat(foundPayment).isNotNull();
                            assertThat(foundPayment.amount()).isNotNull();
                            assertThat(foundPayment.inquiryReferenceId()).isNotNull();
                        })
                .verifyComplete();
    }
}
