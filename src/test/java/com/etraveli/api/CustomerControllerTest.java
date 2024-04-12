package com.etraveli.api;

import com.etraveli.dto.APIResponse;
import com.etraveli.dto.CustomerDTO;
import com.etraveli.dto.MovieDTO;
import com.etraveli.dto.MovieRentalDTO;
import com.etraveli.dto.request.SaveCustomer;
import com.etraveli.enums.MovieType;
import com.etraveli.enums.ResponseStatus;
import com.etraveli.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CustomerControllerTest {
  @Mock
  private CustomerService customerService;
  @InjectMocks
  private CustomerController customerController;

  @Test
  void save() {
    SaveCustomer request = SaveCustomer.builder()
            .customerName("Abdelaziz Allam")
            .build();
    CustomerDTO response = CustomerDTO.builder()
            .id(UUID.randomUUID())
            .name("Abdelaziz Allam")
            .build();

    when(customerService.save(request)).thenReturn(response);

    ResponseEntity<APIResponse<CustomerDTO>> actualResponse = customerController.save(request);

    verify(customerService, times(1)).save(request);
    assertEquals(ResponseStatus.SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertEquals(response, Objects.requireNonNull(actualResponse.getBody()).result());
  }

  @Test
  void fetchAll() {
    List<CustomerDTO> response = List.of(
            CustomerDTO.builder()
                    .id(UUID.randomUUID())
                    .name("Abdelaziz Allam")
                    .rentals(List.of(MovieRentalDTO.builder()
                                    .movie(MovieDTO.builder()
                                                    .id(UUID.randomUUID())
                                                    .title("You've Got Mail")
                                                    .code("F001")
                                                    .type(MovieType.REGULAR)
                                                    .build())
                                    .days(5)
                            .build()))
                    .build());

    when(customerService.findAll()).thenReturn(response);

    ResponseEntity<APIResponse<List<CustomerDTO>>> actualResponse = customerController.fetchAll();

    verify(customerService, times(1)).findAll();
    assertEquals(ResponseStatus.SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertEquals(Objects.requireNonNull(actualResponse.getBody()).result().size(), response.size());
  }
}