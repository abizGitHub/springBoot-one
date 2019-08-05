package itsurena.ir.demo2.exception;

public class UserNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "User Does Not Exist";
    }
}
