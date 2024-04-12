package com.etraveli.service;

import com.etraveli.dto.MovieDTO;
import com.etraveli.dto.MovieDTOBuilder;
import com.etraveli.dto.request.SaveMovie;
import com.etraveli.entity.Movie;
import com.etraveli.repo.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {
  private MovieRepository movieRepository;

  public MovieDTO save(SaveMovie request) {
    Movie entity = movieRepository.save(new Movie(request.code(), request.title(), request.type()));
    return MovieDTOBuilder.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .code(entity.getCode())
            .type(entity.getType())
            .build();
  }

  public List<MovieDTO> findAll() {
    List<MovieDTO> result = new ArrayList<>();
    List<Movie> entities = movieRepository.findAll();
    entities.forEach(entity ->
            result.add(MovieDTOBuilder.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .code(entity.getCode())
                    .type(entity.getType())
                    .build()));
    return result;
  }

}
