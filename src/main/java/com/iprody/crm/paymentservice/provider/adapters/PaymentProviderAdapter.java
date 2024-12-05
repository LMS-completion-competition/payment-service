package com.iprody.crm.paymentservice.provider.adapters;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import com.iprody.crm.paymentservice.provider.PaymentProvider;
import lombok.Getter;

/**
 * An abstract adapter class for a {@link PaymentProvider}, providing a base implementation
 * for initializing payment processing and retrieving payment statuses.
 */
@Getter
public abstract class PaymentProviderAdapter<T, R> {

    /**
     * The underlying payment provider used by this adapter.
     */
    private final  PaymentProvider<T, R> paymentProvider;

    /**
     * Constructs a new {@code PaymentProviderAdapter} with the specified payment provider.
     *
     * @param paymentProvider the payment provider to be used by this adapter
     */
    public PaymentProviderAdapter(PaymentProvider<T, R> paymentProvider) {
        this.paymentProvider = paymentProvider;
    }

    /**
     * Initializes the payment processing for the given payment.
     *
     * @param payment the {@link Payment} object containing payment details
     * @return the transaction ID representing the initialized payment
     */
    public abstract long initializePaymentProcessing(Payment payment);

    /**
     * Retrieves the status of the payment processing operation using the transaction ID.
     *
     * @param transactionId the unique identifier of the payment transaction
     * @return the {@link PaymentStatus} representing the current state of the payment
     */
    public abstract PaymentStatus getPaymentProcessingStatus(long transactionId);
}
