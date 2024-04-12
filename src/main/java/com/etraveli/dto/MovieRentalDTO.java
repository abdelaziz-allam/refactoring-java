package com.etraveli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record MovieRentalDTO(CustomerDTO customer,MovieDTO movie, int days) {
}
