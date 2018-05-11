package pl.devkamil.app.outputDevices;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LcdDisplay {
    private final static String INPUT_MESSAGE = "Enter the bar code: ";
    private final static String SUM_OF_PRODUCTS = "LCD Display - sum of products: %.2f %n";
    private final static String SHOW_MESSAGE = "Product name: %s, price: %.2f %n";
    private final static String ERROR = "Error: ";

    public void showInputMessage(){
        System.out.println(INPUT_MESSAGE);
    }

    public void showSum(BigDecimal sum){
        System.out.format(SUM_OF_PRODUCTS, sum);
    }

    public void showMessage(String name, BigDecimal price){
        System.out.format(SHOW_MESSAGE, name, price);
    }

    public void showErrorMessage(String errorMessage){
        System.out.println(ERROR + errorMessage);
    }
}
