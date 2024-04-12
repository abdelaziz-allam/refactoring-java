package com.etraveli.dto.request;

import io.soabase.recordbuilder.core.RecordBuilder;

import java.util.UUID;

@RecordBuilder
public record SaveRental(UUID customerId, UUID movieId, int days) {
}
