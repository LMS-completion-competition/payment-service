package com.iprody.crm.paymentservice.exceptions;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ErrorResponse {
    @Builder.Default
    Instant timestamp = Instant.now();
    String message;
    @Builder.Default
    List<String> details = new ArrayList<>();
    UUID requestResourceGuid;
}
