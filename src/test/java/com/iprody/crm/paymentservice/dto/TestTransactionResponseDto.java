package com.iprody.crm.paymentservice.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestTransactionResponseDto {
    long transactionId;
    int responseCode;
    String message;
}
