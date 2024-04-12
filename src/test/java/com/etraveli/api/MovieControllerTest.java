package com.etraveli.api;

import com.etraveli.dto.APIResponse;
import com.etraveli.dto.MovieDTO;
import com.etraveli.dto.MovieDTOBuilder;
import com.etraveli.dto.request.SaveMovie;
import com.etraveli.dto.request.SaveMovieBuilder;
import com.etraveli.enums.MovieType;
import com.etraveli.enums.ResponseStatus;
import com.etraveli.service.MovieService;
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
class MovieControllerTest {
  @Mock
  private MovieService movieService;
  @InjectMocks
  private MovieController movieController;

  @Test
  void save() {
    SaveMovie request = SaveMovieBuilder.builder()
            .title("You've Got Mail")
            .code("F001")
            .type(MovieType.REGULAR)
            .build();
    MovieDTO response = MovieDTOBuilder.builder()
            .id(UUID.randomUUID())
            .title("You've Got Mail")
            .code("F001")
            .type(MovieType.REGULAR)
            .build();
    when(movieService.save(request)).thenReturn(response);

    ResponseEntity<APIResponse<MovieDTO>> actualResponse = movieController.save(request);

    verify(movieService, times(1)).save(request);
    assertEquals(ResponseStatus.SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertEquals(response, Objects.requireNonNull(actualResponse.getBody()).result());
  }

  @Test
  void fetchAll() {
    List<MovieDTO> response = List.of(
            MovieDTOBuilder.builder()
                    .id(UUID.randomUUID())
                    .title("You've Got Mail")
                    .code("F001")
                    .type(MovieType.REGULAR)
                    .build(),
            MovieDTOBuilder.builder().id(UUID.randomUUID())
                    .title("Matrix")
                    .code("F002")
                    .type(MovieType.REGULAR)
                    .build(),
            MovieDTOBuilder.builder().id(UUID.randomUUID())
                    .title("Cars")
                    .code("F003")
                    .type(MovieType.CHILDREN)
                    .build(),
            MovieDTOBuilder.builder().id(UUID.randomUUID())
                    .title("Fast & Furious X")
                    .code("F004")
                    .type(MovieType.NEW)
                    .build());
    when(movieService.findAll()).thenReturn(response);

    ResponseEntity<APIResponse<List<MovieDTO>>> actualResponse = movieController.fetchAll();

    verify(movieService, times(1)).findAll();
    assertEquals(ResponseStatus.SUCCESS, Objects.requireNonNull(actualResponse.getBody()).status());
    assertEquals(Objects.requireNonNull(actualResponse.getBody()).result().size(), response.size());
  }

}