package com.etraveli.api.helper;

import com.etraveli.dto.APIResponse;
import com.etraveli.dto.APIResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.etraveli.enums.ResponseStatus.ERROR;
import static com.etraveli.enums.ResponseStatus.SUCCESS;
public class APIHelperUtil<T> {


  public  ResponseEntity<APIResponse<T>> createUnifiedResponse(T body, HttpStatus status, String message, String code) {
    APIResponse<T> apiResponse = getApiResponseInstance(body, status,code);
    APIResponse<T> response = APIResponseBuilder
            .<T>builder()
            .status(apiResponse.status())
            .code(code)
            .result(body)
            .message(message)
            .build();
    return new ResponseEntity<>(response, status);
  }

  private APIResponse<T> getApiResponseInstance(T body, HttpStatus status,String code) {
    return APIResponseBuilder
            .<T>builder()
            .status(status.is2xxSuccessful() ? SUCCESS : ERROR)
            .code(code)
            .result(body)
            .build();
  }
}
