package com.etraveli.dto;

import com.etraveli.enums.ResponseStatus;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record APIResponse<T>(ResponseStatus status,
                             T result,
                             String code,
                             String message) {
}