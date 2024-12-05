package com.iprody.crm.paymentservice.mediator;

import com.iprody.crm.paymentservice.dto.PaymentCreateDto;
import com.iprody.crm.paymentservice.dto.PaymentDto;
import com.iprody.crm.paymentservice.mapper.PaymentMapper;
import com.iprody.crm.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * The {@code PaymentMediator} interface defines methods for creating and retrieving payment information.
 * It acts as an abstraction layer that mediates between the client and the payment processing service.
 * The operations are asynchronous and return a {@link Mono} that emits the result once available.
 *
 * <p>This interface provides two main operations:</p>
 * <ul>
 *   <li>Creating a new payment</li>
 *   <li>Retrieving payment details by its ID</li>
 * </ul>
 *
 * <p>Example usage:</p>
 * <pre>
 *     PaymentCreateDto paymentRequest = new PaymentCreateDto();
 *     paymentMediator.create(paymentRequest)
 *                    .subscribe(payment -> System.out.println("Payment created: " + payment));
 * </pre>
 *
 * @author [Akyl Nasirdinov]
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class PaymentMediator {

    private final PaymentService paymentService;

    private final PaymentMapper paymentMapper;

    /**
     * Creates a new payment based on the provided {@code PaymentCreateDto}.
     * The payment creation process is asynchronous and returns a {@link Mono}
     * that emits the created {@link PaymentDto} once the operation is completed.
     *
     * @param payment the payment details required to create a new payment
     * @return a {@link Mono} emitting the created {@link PaymentDto}, or an error if the creation fails
     */
    public Mono<PaymentDto> create(PaymentCreateDto payment) {
        return Mono.just(paymentMapper.from(payment))
                .flatMap(paymentService::create)
                .map(paymentMapper::to);
    }

    /**
     * Retrieves the payment details for a given payment ID.
     * The operation is asynchronous and returns a {@link Mono} that emits the {@link PaymentDto}
     * corresponding to the specified ID once available.
     *
     * @param id the unique identifier of the payment to retrieve
     * @return a {@link Mono} emitting the found {@link PaymentDto}, or an empty {@link Mono} if no payment is found
     */
    public Mono<PaymentDto> findById(UUID id) {
        return paymentService.findByGuid(id)
                .map(paymentMapper::to);
    }
}
