package com.etraveli.dto.request;

import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotBlank;

@RecordBuilder
public record SaveCustomer(@NotBlank(message = "name is mandatory") String customerName){
}
