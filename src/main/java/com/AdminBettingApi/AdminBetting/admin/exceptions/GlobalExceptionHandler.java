package com.AdminBettingApi.AdminBetting.admin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, Object>> handleUnauthorizedExceptions(UnauthorizedException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());
    }
@ExceptionHandler(MissingFieldException.class)
public ResponseEntity<Map<String, Object>> handleMissingFieldException(MissingFieldException ex) {
        return buildErrorResponse(HttpStatus.NOT_ACCEPTABLE, "Not acceptable", ex.getMessage());
}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundExceptions(UserNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "NOT FOUND", ex.getMessage());
    }




    //  Helper Method for Consistent Error Responses
    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", Optional.of(status.value()));
        response.put("error", error);
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }
}
