package com.crm.exception.handler;

import com.crm.api.Response;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleBusinessException(Exception ex) {
        return buildError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, "error");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + Objects.requireNonNull(e.getDefaultMessage()))
                .collect(Collectors.joining("; "));
        return buildError(errors, HttpStatus.BAD_REQUEST, "validationErrors");
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Response> handleInvalidEnumValue(InvalidFormatException ex) {
        String message = "Invalid value provided";
        if (ex.getTargetType().isEnum() && !ex.getPath().isEmpty()) {
            String fieldName = ex.getPath().get(0).getFieldName();
            message = String.format("Invalid value '%s' for field '%s'", ex.getValue(), fieldName);
        }
        return buildError(message, HttpStatus.BAD_REQUEST, "invalidEnumValue");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return buildError("Duplicate entry error", HttpStatus.CONFLICT, "dataIntegrityViolation");
    }

    private ResponseEntity<Response> buildError(String message, HttpStatus status, String dataKey) {
        return ResponseEntity.status(status).body(Response.error(message, status));
    }
}
