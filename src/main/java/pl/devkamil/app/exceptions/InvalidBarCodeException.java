package pl.devkamil.app.exceptions;


public class InvalidBarCodeException extends Exception {

    private static final String INVALID_BAR_CODE_EXCEPTION_MESSAGE = "Invalid bar code, try again";

    public String getMessage() {
        return INVALID_BAR_CODE_EXCEPTION_MESSAGE;
    }
}
