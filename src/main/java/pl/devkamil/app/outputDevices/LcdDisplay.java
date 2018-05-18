package pl.devkamil.app.outputDevices;

import java.math.BigDecimal;

public class LcdDisplay {
    private final static String INPUT_MESSAGE = "Enter the bar code: ";
    private final static String SUM_OF_PRODUCTS_MESSAGE = "LCD Display - sum of products: %.2f %n";
    private final static String SHOW_ONE_PRODUCT_MESSAGE = "Product name: %s, price: %.2f %n";
    private final static String ERROR_MESSAGE = "Error: ";

    public void showInputMessage(){
        System.out.println(INPUT_MESSAGE);
    }

    public void showSum(BigDecimal sumOfProducts){
        System.out.format(SUM_OF_PRODUCTS_MESSAGE, sumOfProducts);
    }

    public void showOneProductMessage(String productName, BigDecimal productPrice){
        System.out.format(SHOW_ONE_PRODUCT_MESSAGE, productName, productPrice);
    }

    public void showErrorMessage(String errorMessage){
        System.out.println(ERROR_MESSAGE + errorMessage);
    }
}