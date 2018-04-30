package pl.devkamil.app.outputDevices;

import java.math.BigDecimal;

public class LcdDisplay {

    public void showSum(BigDecimal sum){
        System.out.println("LCD Display - sum of products: " + sum);
    }

    public String showMessage(String price, String name){
        return "Product name: " + name + ", price: " + price;
    }

    public String showErrorMessage(String errorMessage){
        return "Error: " + errorMessage;
    }
}
