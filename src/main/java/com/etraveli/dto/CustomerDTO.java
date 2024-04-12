package com.etraveli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.List;
import java.util.UUID;

@RecordBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CustomerDTO (UUID id, String name, List<MovieRentalDTO> rentals){
}
