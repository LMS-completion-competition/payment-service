package com.iprody.crm.paymentservice.job;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import com.iprody.crm.paymentservice.job.task.PaymentLookUpTask;
import com.iprody.crm.paymentservice.provider.adapters.PaymentProviderAdapter;
import com.iprody.crm.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Limit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.jobs.payment-look-up-job", name = "enabled", havingValue = "true")
public class PaymentProcessingJob {
    private final PaymentProviderAdapter<?, ?> paymentProviderAdapter;
    private final PaymentService paymentService;
    private final ExecutorService taskExecutor;

    private final List<PaymentStatus> processedStatuses =
            List.of(
                    PaymentStatus.NEW,
                    PaymentStatus.RETRY
            );

    @Scheduled(fixedRate = 10000)
    public void lookupTransaction() {
        Flux<Payment> payments = paymentService.findAllByStatusIn(processedStatuses, Limit.of(1000));
        payments.subscribe(payment -> {
            PaymentLookUpTask paymentLookUpTask = new PaymentLookUpTask(
                    paymentProviderAdapter,
                    payment,
                    paymentService
            );
            taskExecutor.submit(paymentLookUpTask);
        });
    }
}
