package com.iprody.crm.paymentservice.dto;

import com.iprody.crm.paymentservice.domain.enums.PaymentCurrency;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * PaymentDto is a Data Transfer Object that encapsulates the details of a payment transaction.
 * This record stores information about the payment such as its unique identifier,
 * inquiry reference, amount, currency, status, and creation time.
 * <p>
 * This record is immutable and is intended to be used for transferring payment data between
 * different layers of the application (e.g., services, controllers, or external systems).
 * </p>
 *
 * @param guid                  the unique identifier of the payment
 * @param inquiryReferenceId   the reference ID of the inquiry associated with the payment
 * @param amount               the amount of the payment transaction
 * @param currency             the currency in which the payment was made (e.g., USD, EUR)
 * @param status               the current status of the payment (e.g., PENDING, COMPLETED, FAILED)
 * @param createdAt            the timestamp when the payment was created
 */
public record PaymentDto(UUID guid,
                         UUID inquiryReferenceId,
                         BigDecimal amount,
                         PaymentCurrency currency,
                         PaymentStatus status,
                         Instant createdAt) {
}
