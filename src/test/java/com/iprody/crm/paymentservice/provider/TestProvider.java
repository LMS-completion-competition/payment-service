package com.iprody.crm.paymentservice.provider;

import com.iprody.crm.paymentservice.dto.TestTransactionCreateDto;
import com.iprody.crm.paymentservice.dto.TestTransactionResponseDto;
import com.iprody.crm.paymentservice.provider.PaymentProvider;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TestProvider implements PaymentProvider<TestTransactionCreateDto, TestTransactionResponseDto> {
    @Override
    public TestTransactionResponseDto initializePaymentProcessing(TestTransactionCreateDto paymentDetails) {
        return new TestTransactionResponseDto(10000000, 201, "Successfully created");
    }

    @Override
    public TestTransactionResponseDto getPaymentProcessingStatus(long providerTransactionId) {
        TestTransactionResponseDto[] array = {new TestTransactionResponseDto(10000000, 200, "Successfully processed"),
            new TestTransactionResponseDto(10000000, 300, "Still processing"),
            new TestTransactionResponseDto(10000000, 400, "Transaction was canceled"),
            new TestTransactionResponseDto(10000000, 500, "Internal Server Error")
        };
        Random random = new Random();
        return array[random.nextInt(array.length)];
    }
}
