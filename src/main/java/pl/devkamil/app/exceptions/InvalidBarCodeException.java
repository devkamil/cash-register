package pl.devkamil.app.exceptions;


public class InvalidBarCodeException extends Exception {

    private static final String MESSAGE = "Invalid bar code, try again";

    public String getMessage() {
        return MESSAGE;
    }
}
