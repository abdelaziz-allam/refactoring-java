package com.etraveli.dto;

import com.etraveli.enums.MovieType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record MovieDTO (UUID id, String code, String title, MovieType type){
}
