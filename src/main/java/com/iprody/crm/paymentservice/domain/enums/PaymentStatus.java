package com.iprody.crm.paymentservice.domain.enums;

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
     * The payment is created and waiting to be processed.
     */
    NEW,

    /**
     * The payment is pending in look up process.
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
    ERROR,

    /**
     * The payment has to be processed one more time due to some amount of failed iterations.
     */
    RETRY
}
