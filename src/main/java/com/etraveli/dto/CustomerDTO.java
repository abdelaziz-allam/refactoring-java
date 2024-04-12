package com.etraveli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CustomerDTO (UUID id, String name, List<MovieRentalDTO> rentals){
}
