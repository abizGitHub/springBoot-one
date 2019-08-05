package itsurena.ir.demo2.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError notNullConsErrors(Exception ex) {

        System.out.println(ex.getMessage());

        /*Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            System.out.println(violation);
        }
        */
        return new ApiError(601, "unknown unique constraint");
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError usernameUniqueConsError(ConstraintViolationException ex) {
        if (ex.getConstraintName().contains("UQ_USERNAME"))
            return new ApiError(601, "username already exist");
        else
            return new ApiError(601, "unknown unique constraint");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiError userNotFoundError(UserNotFoundException ex) {
        return new ApiError(601, ex.getMessage());
    }

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        StringBuffer errors = new StringBuffer();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            //String fieldName = ((FieldError) error).getField();
            errors.append(error.getDefaultMessage());
            errors.append("\n");
        });
        return new ApiError(602, "errors.toString()");
    }
*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
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
}
