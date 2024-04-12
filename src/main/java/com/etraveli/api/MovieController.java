package com.etraveli.api;

import com.etraveli.api.helper.APIHelperUtil;
import com.etraveli.dto.APIResponse;
import com.etraveli.dto.MovieDTO;
import com.etraveli.dto.request.SaveMovie;
import com.etraveli.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.etraveli.constant.AppConstants.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/movies")
public class MovieController {
  private MovieService movieService;


  @Operation(summary = "Save New Movie",description = "Save New Movie", responses = {
          @ApiResponse(responseCode = "200",description = MOVIE_SAVED_SUCCESSFULLY),
          @ApiResponse(responseCode = "500",description = INTERNAL_SERVER_ERROR)
  })
  @PostMapping
  public ResponseEntity<APIResponse<MovieDTO>> save(@Valid SaveMovie request) {
    return new APIHelperUtil<MovieDTO>()
            .createUnifiedResponse(movieService.save(request), HttpStatus.OK,MOVIE_SAVED_SUCCESSFULLY,MOVIE_SAVED_SUCCESSFULLY_CODE);
  }

  @Operation(summary = "Fetch All Movies",description = "Fetch All Movies", responses = {
          @ApiResponse(responseCode = "200",description = MOVIES_FETCHED_SUCCESSFULLY),
          @ApiResponse(responseCode = "500",description = INTERNAL_SERVER_ERROR)
  })
  @GetMapping
  public ResponseEntity<APIResponse<List<MovieDTO>>> fetchAll() {
    return new APIHelperUtil<List<MovieDTO>>()
            .createUnifiedResponse(movieService.findAll(), HttpStatus.OK,MOVIES_FETCHED_SUCCESSFULLY,MOVIES_FETCHED_SUCCESSFULLY_CODE);
  }

}
