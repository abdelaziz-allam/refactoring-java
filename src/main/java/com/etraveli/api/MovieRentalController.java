package com.etraveli.api;


import com.etraveli.api.helper.APIHelperUtil;
import com.etraveli.dto.APIResponse;
import com.etraveli.dto.MovieRentalDTO;
import com.etraveli.dto.RentalInfo;
import com.etraveli.dto.RentalStatement;
import com.etraveli.dto.request.SaveRental;
import com.etraveli.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.etraveli.constant.AppConstants.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/rentals")
public class MovieRentalController {

  private final RentalService rentalService;

  @Operation(summary = "Get Customer Rental Statement By Customer Id",description = "Get Customer Rental Statement By Customer Id", responses = {
          @ApiResponse(responseCode = "200",description = STATEMENT_GENERATED_SUCCESSFULLY),
          @ApiResponse(responseCode = "404",description = CUSTOMER_NOT_FOUND)
  })
  @GetMapping("statement/customer/{id}")
  public ResponseEntity<APIResponse<String>> statement(@PathVariable UUID id) {
    String statement = rentalService.statement(id);
    return new APIHelperUtil<String>().createUnifiedResponse(statement, HttpStatus.OK,STATEMENT_GENERATED_SUCCESSFULLY,STATEMENT_GENERATED_SUCCESSFULLY_CODE);
  }

  @Operation(summary = "Get Customer Rental By Customer Id",description = "Get Customer Rental By Customer Id", responses = {
          @ApiResponse(responseCode = "200",description = RENTAL_INFO_GENERATED_SUCCESSFULLY),
          @ApiResponse(responseCode = "404",description = CUSTOMER_NOT_FOUND)
  })
  @GetMapping("/{id}")
  public ResponseEntity<APIResponse<List<MovieRentalDTO>>> getCustomerRental(@PathVariable UUID id) {
    List<MovieRentalDTO> rentalInfo = rentalService.getCustomerRental(id);
    return new APIHelperUtil<List<MovieRentalDTO>>().createUnifiedResponse(rentalInfo, HttpStatus.OK,RENTAL_INFO_GENERATED_SUCCESSFULLY,RENTAL_INFO_GENERATED_SUCCESSFULLY_CODE);
  }

  @Operation(summary = "Save Customer Rental",description = "Save Customer Rental", responses = {
          @ApiResponse(responseCode = "200",description = CUSTOMER_RENTAL_SAVED_SUCCESSFULLY),
          @ApiResponse(responseCode = "500",description = INTERNAL_SERVER_ERROR)
  })
  @PostMapping
  public ResponseEntity<APIResponse<RentalInfo>> saveRental(SaveRental request) {
    return new APIHelperUtil<RentalInfo>()
            .createUnifiedResponse(rentalService.saveCustomerRental(request), HttpStatus.OK,CUSTOMER_RENTAL_SAVED_SUCCESSFULLY,CUSTOMER_RENTAL_SAVED_SUCCESSFULLY_CODE);
  }

}
