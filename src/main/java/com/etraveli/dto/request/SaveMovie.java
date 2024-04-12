package com.etraveli.dto.request;

import com.etraveli.enums.MovieType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SaveMovie (@NotBlank(message = "title is mandatory") String title,
                         @NotBlank(message = "code is mandatory") String code,
                         @NotNull(message = "type is mandatory") MovieType type){
}
