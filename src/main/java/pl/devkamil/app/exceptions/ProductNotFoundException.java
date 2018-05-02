package pl.devkamil.app.exceptions;

import pl.devkamil.app.model.BarCode;

public class ProductNotFoundException extends Exception {

    private static final String MESSAGE = "Product with %s barcode doesn't exist in database";
    private BarCode barCode;


    public String getMessage() {
        return String.format(MESSAGE, getBarCode().getBarCode());
    }

    public BarCode getBarCode() {
        return barCode;
    }

    public ProductNotFoundException(BarCode barCode) {
        this.barCode = barCode;
    }
}
