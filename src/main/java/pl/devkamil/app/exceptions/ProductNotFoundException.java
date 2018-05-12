package pl.devkamil.app.exceptions;

import pl.devkamil.app.model.BarCode;

public class ProductNotFoundException extends Exception {

    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "Product with %s barcode doesn't exist in database";
    private BarCode barCode;


    public String getMessage() {
        return String.format(NOT_FOUND_EXCEPTION_MESSAGE, barCode.getBarCode());
    }

    public ProductNotFoundException(BarCode barCode) {
        this.barCode = barCode;
    }
}
