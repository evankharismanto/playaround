package com.backendDevelopment.withtest.dbrestservice.throwable;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@AllArgsConstructor
@JsonInclude(content = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder

public class APIExceptionHandler {
    private String errorCode;
    private String message;
}
