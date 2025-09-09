package com.avsofthealthcare.exception;

import jakarta.servlet.ServletException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Map<String, String>> handleMultipartException(MultipartException ex) {
        logger.error("MultipartException occurred", ex);

        // Find root cause
        Throwable rootCause = ex;
        while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
            rootCause = rootCause.getCause();
        }

        Map<String, String> error = new HashMap<>();
        String message;

        logger.error("Root cause class: {}", rootCause.getClass().getName());
        logger.error("Root cause message: {}", rootCause.getMessage());

        // Specific known causes in Spring Boot 3.x
        if (rootCause instanceof org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException) {
            message = "Uploaded file exceeds the maximum allowed size for a single file.";
        }
        else if (rootCause instanceof org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException) {
            message = "Total request size exceeds the maximum allowed limit.";
        }
        else if (rootCause.getMessage() != null && rootCause.getMessage().toLowerCase().contains("invalid content type")) {
            message = "Invalid file type. Please upload a supported file format.";
        }
        else if (rootCause.getMessage() != null && rootCause.getMessage().toLowerCase().contains("file name too long")) {
            message = "Uploaded file name is too long.";
        }
        else if (rootCause instanceof IllegalStateException) {
            message = "Request size exceeds the configured maximum limit.";
        }
        else if (rootCause instanceof org.apache.tomcat.util.http.fileupload.FileUploadException) {
            message = "File upload failed: " + rootCause.getMessage();
        }
        else {
            message = rootCause.getMessage() != null
                    ? rootCause.getMessage()
                    : "An unexpected file upload error occurred.";
        }

        error.put("error", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }



    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        logger.error("MaxUploadSizeExceededException occurred: ", ex);
        Map<String, String> error = new HashMap<>();
        error.put("error", "File size exceeds maximum allowed size (10MB)");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("IllegalArgumentException occurred: ", ex);
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        logger.error("Unexpected exception occurred: ", ex);
        Map<String, String> error = new HashMap<>();
        error.put("error", "An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}

