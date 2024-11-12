package com.iprody.crm.paymentservice.controllers;

import com.iprody.crm.paymentservice.dtos.PaymentCreateDto;
import com.iprody.crm.paymentservice.dtos.PaymentDto;
import com.iprody.crm.paymentservice.mediators.PaymentMediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Tag(name = "Payments API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentMediator paymentMediator;

    @GetMapping
    @Operation(summary = "method for finding payments by id", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully found payment"),
        @ApiResponse(responseCode = "400", description = "Missing payment id"),
        @ApiResponse(responseCode = "404", description = "Schema not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public Mono<PaymentDto> findPaymentById(@RequestParam("id") UUID id) {
        return paymentMediator.findById(id);
    }

    @PostMapping
    @Operation(summary = "method for creating payments", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully created payment"),
        @ApiResponse(responseCode = "400", description = "Missing request body or one of required fields"),
        @ApiResponse(responseCode = "404", description = "Schema not found"),
        @ApiResponse(responseCode = "500", description = "Internal error")
    })
    public Mono<PaymentDto> createPayment(@Valid @RequestBody PaymentCreateDto createdPayment) {
        return paymentMediator.create(createdPayment);
    }
}
