package com.iprody.crm.paymentservice.job.task;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import com.iprody.crm.paymentservice.provider.adapters.PaymentProviderAdapter;
import com.iprody.crm.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PaymentLookUpTask implements Runnable {

    private final PaymentProviderAdapter<?, ?> paymentProviderAdapter;

    private final Payment payment;

    private final PaymentService paymentService;

    @Override
    public void run() {
        paymentService.updatePaymentStatusToPending(payment)
                .subscribe(processedPayment -> {
                    PaymentStatus providerStatus = PaymentStatus.RETRY;
                    try {
                        providerStatus = paymentProviderAdapter
                                .getPaymentProcessingStatus(payment.getTransactionReferenceId());
                    } catch (Exception e) {
                        log.error("Error occurred during running PaymentLookUpTask with cause {}", e.getMessage());
                    }
                    log.info(String.format("provider returned status %s for payment with id %s",
                            providerStatus,
                            payment.getId()));
                    payment.setStatus(providerStatus);
                    paymentService.update(payment); });
    }
}
