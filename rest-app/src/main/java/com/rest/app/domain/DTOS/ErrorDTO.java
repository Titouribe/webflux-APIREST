package com.rest.app.domain.DTOS;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {

    private String errorCode;
    private String errorMessage;
}
