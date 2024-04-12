package com.etraveli.api;

import com.etraveli.api.helper.APIHelperUtil;
import com.etraveli.dto.APIResponse;
import com.etraveli.dto.CustomerDTO;
import com.etraveli.dto.RentalInfo;
import com.etraveli.dto.request.SaveCustomer;
import com.etraveli.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.etraveli.constant.AppConstants.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
  private CustomerService customerService;

  @Operation(summary = "Save New Customer",description = "Save New Customer", responses = {
          @ApiResponse(responseCode = "200",description = CUSTOMER_SAVED_SUCCESSFULLY),
          @ApiResponse(responseCode = "500",description = INTERNAL_SERVER_ERROR)
  })
  @PostMapping
  public ResponseEntity<APIResponse<CustomerDTO>> save(@Valid SaveCustomer request) {
    return new APIHelperUtil<CustomerDTO>()
            .createUnifiedResponse(customerService.save(request), HttpStatus.OK,CUSTOMER_SAVED_SUCCESSFULLY,CUSTOMER_SAVED_SUCCESSFULLY_CODE);
  }

  @Operation(summary = "Fetch All Customers",description = "Fetch All Customers", responses = {
          @ApiResponse(responseCode = "200",description = CUSTOMER_FETCHED_SUCCESSFULLY),
          @ApiResponse(responseCode = "500",description = INTERNAL_SERVER_ERROR)
  })
  @GetMapping
  public ResponseEntity<APIResponse<List<CustomerDTO>>> fetchAll() {
    return new APIHelperUtil<List<CustomerDTO>>()
            .createUnifiedResponse(customerService.findAll(), HttpStatus.OK,CUSTOMER_FETCHED_SUCCESSFULLY,CUSTOMER_FETCHED_SUCCESSFULLY_CODE);
  }


}
