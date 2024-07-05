package io.reflectoring.rentAcar.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleDataNotFoundException(DataNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }
    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handlePermissionException(PermissionException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = "Invalid argument type: " + ex.getName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }





    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Invalıd argument");

        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
            String fieldName = invalidFormatException.getPath().get(0).getFieldName();
            String fieldValue = invalidFormatException.getValue().toString();

            // Rol hataları için kontrol
            if (fieldName.equals("roles")) {
                errorResponse.setErrorMessage("Invalid Role Value");
                errorResponse.setDetails("The role value you entered is invalid"); // + Arrays.toString(MemberRole.values()));
            }
            // Kategori hataları için kontrol
            else if (fieldName.equals("bookCategory")) {
                errorResponse.setErrorMessage("Invalid Category Value");
                errorResponse.setDetails("The category value you entered is invalid"); // + Arrays.toString(BookCategory.values()));
            }
        } else {
            errorResponse.setErrorMessage("An error occurred");
                errorResponse.setDetails("Invalid JSON format");
        }

        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }




    @ExceptionHandler(BookException.class)
    public ResponseEntity<MyErrorDetails> bookExceptionHandler(BookException be , WebRequest wb){

        MyErrorDetails med = new MyErrorDetails();

        med.setTimestamp(LocalDateTime.now());
        med.setMessage(be.getMessage());
        med.setDetails(wb.getDescription(false));
        return new ResponseEntity<MyErrorDetails>(med, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


}