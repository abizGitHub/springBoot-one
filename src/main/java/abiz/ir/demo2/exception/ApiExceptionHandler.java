package abiz.ir.demo2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity usernameUniqueConsError(ApiException ex) {
        if (ex.type.equals(ApiExceptionType.USER_NAME_ALREADY_EXIST))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());

        if (ex.type.equals(ApiExceptionType.USER_NOT_FOUND))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());

        boolean notModified = ex.type.equals(ApiExceptionType.PASSWORD_MIN_LENGTH)
                || ex.type.equals(ApiExceptionType.PASSWORD_MAX_LENGTH)
                || ex.type.equals(ApiExceptionType.USER_NAME_MIN_LENGTH)
                || ex.type.equals(ApiExceptionType.USER_NAME_MAX_LENGTH)
                || ex.type.equals(ApiExceptionType.FIRST_NAME_MIN_LENGTH)
                || ex.type.equals(ApiExceptionType.FIRST_NAME_MAX_LENGTH)
                || ex.type.equals(ApiExceptionType.LAST_NAME_MIN_LENGTH)
                || ex.type.equals(ApiExceptionType.LAST_NAME_MAX_LENGTH)
                || ex.type.equals(ApiExceptionType.PASSWORD_MUST_NOT_NULL)
                || ex.type.equals(ApiExceptionType.USER_NAME_MUST_NOT_NULL)
                || ex.type.equals(ApiExceptionType.FIRST_NAME_MUST_NOT_NULL)
                || ex.type.equals(ApiExceptionType.LAST_NAME_MUST_NOT_NULL);

        if (notModified)
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ex.getMessage());

        if (ex.type.equals(ApiExceptionType.PASSWORD_INCORRECT_ERROR))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

        if (ex.type.equals(ApiExceptionType.USERS_LIST_IS_EMPTY))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
