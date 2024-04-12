package com.etraveli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record MovieRentalDTO(CustomerDTO customer,MovieDTO movie, int days) {
}
