package com.iprody.crm.paymentservice.dto;

import com.iprody.crm.paymentservice.domain.enums.PaymentCurrency;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestTransactionCreateDto {
    BigDecimal transactionAmount;
    PaymentCurrency currency;
}
