package com.etraveli.constant;

public final class AppConstants {

  public static final String CUSTOMER_NOT_FOUND = "Customer not found";
  public static final String MOVIE_NOT_FOUND = "Movie not found";
  public static final String CUSTOMER_SAVED_SUCCESSFULLY = "Customer saved successfully";
  public static final String CUSTOMER_FETCHED_SUCCESSFULLY = "Customers fetched successfully";
  public static final String MOVIE_SAVED_SUCCESSFULLY = "Movie saved successfully";
  public static final String MOVIES_FETCHED_SUCCESSFULLY = "Movies fetched successfully";
  public static final String CUSTOMER_RENTAL_SAVED_SUCCESSFULLY = "Customer Rental Saved Successfully";
  public static final String STATEMENT_GENERATED_SUCCESSFULLY = "Customer Rental Statement Retrieved Successfully";
  public static final String RENTAL_INFO_GENERATED_SUCCESSFULLY = "Customer Rental Retrieved Successfully";
  public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

  public static final String CUSTOMER_SAVED_SUCCESSFULLY_CODE = "CUSTOMER_SAVED_001";
  public static final String CUSTOMER_FETCHED_SUCCESSFULLY_CODE = "CUSTOMER_FETCHED_001";

  public static final String MOVIE_SAVED_SUCCESSFULLY_CODE = "MOVIE_SAVED_001";
  public static final String MOVIES_FETCHED_SUCCESSFULLY_CODE = "MOVIES_FETCHED_001";
  public static final String CUSTOMER_RENTAL_SAVED_SUCCESSFULLY_CODE = "CUSTOMER_RENTAL_SAVED_002";
  public static final String RENTAL_INFO_GENERATED_SUCCESSFULLY_CODE = "CUSTOMER_RENTAL_LOADED_002";
  public static final String STATEMENT_GENERATED_SUCCESSFULLY_CODE = "CUSTOMER_STATEMENT_001";

  public static class Loggers {
    private Loggers(){}
    public static final String METHOD_LOG_BEFORE_METHOD_CALL =
            "Before method {} . {} is called.";

    public static final String METHOD_LOG_AFTER_METHOD_CALL =
            "After method {} .{} is called.";

    public static final String METHOD_LOG_PARAMETER_USED =
            "Parameters used in: {} .{} are ::: {}";

    public static final String EXCEPTION_STACKTRACE = "Exception stacktrace :: {} ";

    public static final String LOG_API_LOGGER_REQUEST_RECEIVED =
            "REST Request received for {} . With body :\n{}";
    public static final String LOG_API_LOGGER_RESPONSE = "REST Response sent with status: ";
    public static final String LOG_API_LOGGER_RESPONSE_REQUEST = " for request : ";
    public static final String LOG_API_LOGGER_HEADERS = "\n Response headers -> [";
    public static final String LOG_API_LOGGER_BODY = "\n Response Body -> ";
    public static final String LOG_API_LOGGER_RESPONSE_HTTP_METHOD = "POST";
    public static final String LOG_API_LOGGER_RESPONSE_ENCODING =
            "Error While Encoding Response {}";
    public static final String APIS_LOGGING_ENABLED = "apis.logging.enabled";
    public static final String UNSUPPORTED_ENCODING = "Unsupported-Encoding";

  }


  public static class APIs {
    private APIs() {}
    public static final String CORRELATION_ID_HEADER = "transactionId";
    public static final String REST_LIVELINESS = "/actuator/health/liveliness";
    public static final String REST_READINESS = "/actuator/health/readiness";
    public static final String HTTP_RESPONSE_CODE_HEADER = "http_response_code";
    public static final String HTTP_SOURCE_HEADER = "source";

  }

  public static class Formats {
    private Formats() {}
    public static final String EMPTY_STRING = "";
    public static final String COMMA_DELIMITER = ", ";
    public static final String EQUAL_SIGN = " = ";
    public static final String CLOSING_BRACKET = "]";
    public static final String SPACE = " ";
  }


  private AppConstants() {}
}