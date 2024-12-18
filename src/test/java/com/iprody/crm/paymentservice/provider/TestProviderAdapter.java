package com.iprody.crm.paymentservice.provider;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.domain.enums.PaymentStatus;
import com.iprody.crm.paymentservice.dto.TestTransactionCreateDto;
import com.iprody.crm.paymentservice.dto.TestTransactionResponseDto;
import com.iprody.crm.paymentservice.provider.adapters.PaymentProviderAdapter;
import org.springframework.stereotype.Component;

@Component
public class TestProviderAdapter extends PaymentProviderAdapter<TestTransactionCreateDto, TestTransactionResponseDto> {

    protected TestProviderAdapter(TestProvider defaultPaymentProvider) {
        super(defaultPaymentProvider);
    }

    @Override
    public long initializePaymentProcessing(Payment payment) {
        TestTransactionCreateDto testDto = new TestTransactionCreateDto(payment.getAmount(), payment.getCurrency());
        return  super.getPaymentProvider().initializePaymentProcessing(testDto).getTransactionId();
    }

    @Override
    public PaymentStatus getPaymentProcessingStatus(long transactionId) {
        TestTransactionResponseDto providerResponse = super.getPaymentProvider()
                .getPaymentProcessingStatus(transactionId);
        int responseCode = providerResponse.getResponseCode();
        return switch (responseCode) {
            case 200 -> PaymentStatus.SUCCEED;
            case 400 -> PaymentStatus.CANCELED;
            case 500 -> PaymentStatus.ERROR;
            default -> PaymentStatus.NEW;
        };
    }
}
