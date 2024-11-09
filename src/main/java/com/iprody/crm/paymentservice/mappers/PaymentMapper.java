package com.iprody.crm.paymentservice.mappers;

import com.iprody.crm.paymentservice.domains.entities.Payment;
import com.iprody.crm.paymentservice.dtos.PaymentCreateDto;
import com.iprody.crm.paymentservice.dtos.PaymentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDto to(Payment payment);

    Payment from(PaymentCreateDto createdPayment);
}
