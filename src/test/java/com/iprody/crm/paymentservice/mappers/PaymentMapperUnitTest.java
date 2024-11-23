package com.iprody.crm.paymentservice.mappers;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import com.iprody.crm.paymentservice.domains.enums.PaymentCurrency;
import com.iprody.crm.paymentservice.domains.enums.PaymentStatus;
import com.iprody.crm.paymentservice.dtos.PaymentCreateDto;
import com.iprody.crm.paymentservice.dtos.PaymentDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PaymentMapperUnitTest {

    private final PaymentMapperImpl paymentMapper = new PaymentMapperImpl();
    private PaymentCreateDto mockPaymentCreateDto;
    private Payment mockPayment;

    @BeforeEach
    public void setUp() {
        Instant createdDate = Instant.now();
        UUID inquiryReferenceId = UUID.randomUUID();
        mockPaymentCreateDto = new PaymentCreateDto(
                inquiryReferenceId,
                BigDecimal.valueOf(250L),
                PaymentCurrency.USD);
        mockPayment = Payment.builder()
                .id(1L)
                .guid(UUID.randomUUID())
                .amount(BigDecimal.valueOf(250L))
                .currency(PaymentCurrency.USD)
                .status(PaymentStatus.NEW)
                .inquiryReferenceId(inquiryReferenceId)
                .createdAt(createdDate)
                .transactionReferenceId(1L)
                .build();
    }


    @Test
    public void givenPaymentCreateDto_thenShouldBeMappedToPayment() {
        Payment mappedPayment = paymentMapper.from(mockPaymentCreateDto);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mappedPayment).isNotNull();
        softAssertions.assertThat(mappedPayment.getCurrency()).isEqualTo(mockPaymentCreateDto.currency());
        softAssertions.assertThat(mappedPayment.getAmount()).isEqualTo(mockPaymentCreateDto.amount());
        softAssertions.assertThat(mappedPayment.getInquiryReferenceId()).isEqualTo(mockPaymentCreateDto.inquiryReferenceId());
        softAssertions.assertAll();
    }

    @Test
    public void givenPayment_whenIsNull_thenReturnsNullPaymentDto() {
        PaymentDto mappedPaymentDto = paymentMapper.to(null);
        assertThat(mappedPaymentDto).isNull();
    }

    @Test
    public void givenPaymentCreateDto_whenIsNull_thenReturnsNullPayment() {
        Payment mappedPayment = paymentMapper.from(null);
        assertThat(mappedPayment).isNull();
    }

    @Test
    public void givenPayment_thenShouldBeMappedToPaymentDto() {
        PaymentDto mappedPaymentDto = paymentMapper.to(mockPayment);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(mappedPaymentDto).isNotNull();
        softAssertions.assertThat(mappedPaymentDto.guid()).isEqualTo(mockPayment.getGuid());
        softAssertions.assertThat(mappedPaymentDto.amount()).isEqualTo(mockPayment.getAmount());
        softAssertions.assertThat(mappedPaymentDto.currency()).isEqualTo(mockPayment.getCurrency());
        softAssertions.assertThat(mappedPaymentDto.inquiryReferenceId()).isEqualTo(mockPayment.getInquiryReferenceId());
        softAssertions.assertThat(mappedPaymentDto.createdAt()).isEqualTo(mockPayment.getCreatedAt());
        softAssertions.assertThat(mappedPaymentDto.status()).isEqualTo(mockPayment.getStatus());
        softAssertions.assertAll();
    }
}
