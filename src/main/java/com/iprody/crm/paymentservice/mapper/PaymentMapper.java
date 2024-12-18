package com.iprody.crm.paymentservice.mapper;

import com.iprody.crm.paymentservice.domain.entities.Payment;
import com.iprody.crm.paymentservice.dto.PaymentCreateDto;
import com.iprody.crm.paymentservice.dto.PaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDto to(Payment payment);

    Payment from(PaymentCreateDto createdPayment);
}
