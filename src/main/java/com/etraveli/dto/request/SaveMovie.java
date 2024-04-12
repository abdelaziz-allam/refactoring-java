package com.etraveli.dto.request;

import com.etraveli.enums.MovieType;
import io.soabase.recordbuilder.core.RecordBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RecordBuilder
public record SaveMovie (@NotBlank(message = "title is mandatory") String title,
                         @NotBlank(message = "code is mandatory") String code,
                         @NotNull(message = "type is mandatory") MovieType type){
}
