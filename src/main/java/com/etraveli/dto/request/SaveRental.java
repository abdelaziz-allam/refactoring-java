package com.etraveli.dto.request;

import lombok.Builder;

import java.util.UUID;

@Builder
public record SaveRental(UUID customerId, UUID movieId, int days) {
}
