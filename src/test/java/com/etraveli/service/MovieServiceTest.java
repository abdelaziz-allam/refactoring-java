package com.etraveli.service;

import com.etraveli.dto.MovieDTO;
import com.etraveli.dto.MovieDTOBuilder;
import com.etraveli.dto.request.SaveMovie;
import com.etraveli.dto.request.SaveMovieBuilder;
import com.etraveli.entity.Movie;
import com.etraveli.enums.MovieType;
import com.etraveli.repo.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class MovieServiceTest {

  @Mock
  private MovieRepository movieRepository;
  @InjectMocks
  private MovieService movieService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSave() {
    UUID movieId = UUID.randomUUID();
    SaveMovie request = SaveMovieBuilder.builder()
            .title("You've Got Mail")
            .code("F001")
            .type(MovieType.REGULAR)
            .build();
    MovieDTO expected = MovieDTOBuilder.builder()
            .id(movieId)
            .title("You've Got Mail")
            .code("F001")
            .type(MovieType.REGULAR)
            .build();
    Movie entity = new Movie(movieId, "F001","You've Got Mail",MovieType.REGULAR);

    when(movieRepository.save(any())).thenReturn(entity);

    MovieDTO response = movieService.save(request);

    assertNotNull(response);
    assertThat(expected, is(response));
  }

  @Test
  void testFindAll() {
    List<Movie> movieList = List.of(
            new Movie(UUID.randomUUID(), "You've Got Mail","F001", MovieType.REGULAR),
            new Movie(UUID.randomUUID(), "Cars", "F003",MovieType.CHILDREN)
    );
    List<MovieDTO> expected = List.of(
            MovieDTOBuilder.builder().id(UUID.randomUUID()).title("You've Got Mail").code("F001").type(MovieType.REGULAR).build(),
            MovieDTOBuilder.builder().id(UUID.randomUUID()).title("Cars").code("F003").type(MovieType.CHILDREN).build()
    );

    when(movieRepository.findAll()).thenReturn(movieList);

    List<MovieDTO> response = movieService.findAll();

    assertNotNull(response);
    assertThat(Objects.requireNonNull(response), hasSize(expected.size()));

  }
}