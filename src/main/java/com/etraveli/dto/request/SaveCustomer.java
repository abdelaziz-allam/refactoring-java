package com.etraveli.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SaveCustomer(@NotBlank(message = "name is mandatory") String customerName){
}
