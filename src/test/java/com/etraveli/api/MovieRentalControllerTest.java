package com.etraveli.api;

import com.etraveli.dto.*;
import com.etraveli.dto.request.SaveRental;
import com.etraveli.dto.request.SaveRentalBuilder;
import com.etraveli.enums.MovieType;
import com.etraveli.enums.ResponseStatus;
import com.etraveli.service.RentalService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.etraveli.enums.ResponseStatus.SUCCESS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class MovieRentalControllerTest {

  @Mock
  private RentalService rentalService;

  @InjectMocks
  private MovieRentalController movieRentalController;

  @Test
  void testStatement() {
    UUID customerId = UUID.randomUUID();
    String statement = "Rental Record for C. U. Stomer\\n\\tHarry potter\\t4.5\\n\\tThe Avengers\\t8.0\\nAmount owed is 8.0\\nYou earned 2 frequent points\\n";
    when(rentalService.statement(customerId)).thenReturn(statement);

    ResponseEntity<APIResponse<String>> actualResponse = movieRentalController.statement(customerId);

    verify(rentalService, times(1)).statement(customerId);
    assertEquals(SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertEquals(Objects.requireNonNull(actualResponse.getBody()).result(),statement);
  }

  @Test
  void testGetCustomerRental() {
    UUID customerId = UUID.randomUUID();
    List<MovieRentalDTO> response = List.of( MovieRentalDTOBuilder.builder()
                    .days(5)
                    .customer(CustomerDTOBuilder.builder()
                            .id(UUID.randomUUID())
                            .name("Abdelaziz Allam")
                            .build())
                    .movie(MovieDTOBuilder.builder()
                            .id(UUID.randomUUID())
                            .title("You've Got Mail")
                            .code("F001")
                            .type(MovieType.REGULAR)
                            .build())
            .build());

    when(rentalService.getCustomerRental(customerId)).thenReturn(response);

    ResponseEntity<APIResponse<List<MovieRentalDTO>>> actualResponse = movieRentalController.getCustomerRental(customerId);

    verify(rentalService, times(1)).getCustomerRental(customerId);
    assertEquals(SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertThat(Objects.requireNonNull(actualResponse.getBody()).result(), hasSize(response.size()));

  }

  @Test
  void testSaveRental() {
    UUID customerId = UUID.randomUUID();
    UUID movieId = UUID.randomUUID();
    SaveRental request = SaveRentalBuilder.builder()
            .customerId(customerId)
            .movieId(movieId)
            .days(5)
            .build();
    RentalInfo response = RentalInfoBuilder.builder()
            .customerId(customerId)
            .customerName("Abdelaziz Allam")
            .movieName("You've Got Mail")
            .type(MovieType.REGULAR)
            .days(5)
            .build();

    when(rentalService.saveCustomerRental(request)).thenReturn(response);

    ResponseEntity<APIResponse<RentalInfo>> actualResponse = movieRentalController.saveRental(request);

    verify(rentalService, times(1)).saveCustomerRental(request);
    assertEquals(SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertEquals(response, Objects.requireNonNull(actualResponse.getBody()).result());
  }

}