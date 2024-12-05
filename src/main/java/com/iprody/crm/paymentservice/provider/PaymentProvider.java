package com.iprody.crm.paymentservice.provider;

/**
 * Represents a generic interface for payment providers to handle payment processing.
 *
 * @param <T> the type of the payment details object
 * @param <R> the type of the response object returned by the methods
 */
public interface PaymentProvider<T, R> {

    /**
     * Initializes the payment processing using the provided payment details.
     *
     * @param paymentDetails an object containing the necessary details for the payment
     * @return a response object indicating the result of the payment initialization
     */
    R initializePaymentProcessing(T paymentDetails);

    /**
     * Retrieves the status of a payment processing operation based on the provider's transaction ID.
     *
     * @param providerTransactionId the unique identifier of the transaction within the provider's system
     * @return a response object indicating the current status of the payment processing
     */
    R getPaymentProcessingStatus(long providerTransactionId);
}
