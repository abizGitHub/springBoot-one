package itsurena.ir.demo2.exception;

public class ApiException extends Exception {

    ApiExceptionType type;



    public ApiException(ApiExceptionType type) {
        this.type = type;
    }

    public ApiExceptionType getType() {
        return type;
    }

    public void setType(ApiExceptionType type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        String message = "";
        switch (type) {
            case USER_NOT_FOUND:
                message = "USER_NOT_FOUND";
                break;
            case USER_NAME_MUST_NOT_NULL:
                message = "USER_NAME_MUST_NOT_NULL";
                break;
            case FIRST_NAME_MUST_NOT_NULL:
                message = "FIRST_NAME_MUST_NOT_NULL";
                break;
            case LAST_NAME_MUST_NOT_NULL:
                message = "LAST_NAME_MUST_NOT_NULL";
                break;
            case PASSWORD_MUST_NOT_NULL:
                message = "PASSWORD_MUST_NOT_NULL";
                break;
            case PASSWORD_INCORRECT_ERROR:
                message = "PASSWORD_INCORRECT_ERROR";
                break;
            case USER_NAME_MIN_LENGTH:
                message = "USER_NAME_MIN_LENGTH";
                break;
            case USER_NAME_MAX_LENGTH:
                message = "USER_NAME_MAX_LENGTH";
                break;
            case FIRST_NAME_MIN_LENGTH:
                message = "FIRST_NAME_MIN_LENGTH";
                break;
            case FIRST_NAME_MAX_LENGTH:
                message = "FIRST_NAME_MAX_LENGTH";
                break;
            case LAST_NAME_MIN_LENGTH:
                message = "LAST_NAME_MIN_LENGTH";
                break;
            case LAST_NAME_MAX_LENGTH:
                message = "LAST_NAME_MAX_LENGTH";
                break;
            case PASSWORD_MIN_LENGTH:
                message = "PASSWORD_MIN_LENGTH";
                break;
            case PASSWORD_MAX_LENGTH:
                message = "PASSWORD_MAX_LENGTH";
                break;
            case USER_NAME_ALREADY_EXIST:
                message = "USER_NAME_ALREADY_EXIST";
                break;
            case USERS_LIST_IS_EMPTY:
                message = "USERS_LIST_IS_EMPTY";
                break;
            default:
                message = "UNKNOWN_ERROR";
        }
        return message;
    }
}
