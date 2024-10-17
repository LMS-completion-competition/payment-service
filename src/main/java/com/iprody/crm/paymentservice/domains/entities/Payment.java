package com.iprody.crm.paymentservice.domains.entities;

import com.iprody.crm.paymentservice.domains.enums.PaymentCurrency;
import com.iprody.crm.paymentservice.domains.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "payments")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    Long id;
    Long inquiryReferenceId;
    BigDecimal amount;
    PaymentCurrency currency;
    Long transactionReferenceId;
    @Builder.Default
    PaymentStatus status = PaymentStatus.NEW;
    @CreatedDate
    Instant createdAt;
    @LastModifiedDate
    Instant updatedAt;

}