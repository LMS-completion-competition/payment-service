package com.iprody.crm.paymentservice.domains.enums;

/**
 * Enum representing the different statuses of a payment.
 * {@link #NEW}
 * {@link #PENDING}
 * {@link #SUCCEED}
 * {@link #CANCELED}
 * {@link #ERROR}
 */
public enum PaymentStatus {

    /**
     * The payment is only created in database and has not started been processing.
     */
    NEW,

    /**
     * The payment is pending and has started been processed by some provider.
     */
    PENDING,

    /**
     * The payment has been successfully processed.
     */
    SUCCEED,

    /**
     * The payment has been canceled by the user or system.
     */
    CANCELED,

    /**
     * The payment has failed due to an error.
     */
    ERROR
}
