package com.etraveli.dto;

import com.etraveli.enums.MovieType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.UUID;

@RecordBuilder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record MovieDTO (UUID id, String code, String title, MovieType type){
}
