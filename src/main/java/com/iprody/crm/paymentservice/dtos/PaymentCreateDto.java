package com.iprody.crm.paymentservice.dtos;

import com.iprody.crm.paymentservice.domains.enums.PaymentCurrency;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * PaymentCreateDto is a Data Transfer Object used for creating a new payment.
 * This record stores the necessary details for initiating a payment transaction,
 * such as the inquiry reference ID, payment amount, and currency.
 * <p>
 * This record is immutable and serves as input data for payment creation operations.
 * The fields are validated using {@code @NotNull} annotations to ensure that
 * none of the essential data is missing.
 * </p>
 *
 * @param inquiryReferenceId   the reference ID of the inquiry associated with the payment (must not be null)
 * @param amount               the amount to be paid in the specified currency (must not be null)
 * @param currency             the currency of the payment (e.g., USD, EUR) (must not be null)
 */
public record PaymentCreateDto(@NotNull(message = "inquiry reference should not be null") UUID inquiryReferenceId,
                               @NotNull(message = "payment amount should not be null") BigDecimal amount,
                               @NotNull(message = "payment currency should not be null") PaymentCurrency currency) {
}
