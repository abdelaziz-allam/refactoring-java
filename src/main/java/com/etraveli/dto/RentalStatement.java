package com.etraveli.dto;

import io.soabase.recordbuilder.core.RecordBuilder;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@RecordBuilder
public record RentalStatement(String customerName,String movieTitle,double totalAmount, int frequentEnterPoints) {
}
