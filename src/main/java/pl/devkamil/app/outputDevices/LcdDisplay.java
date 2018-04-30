package pl.devkamil.app.outputDevices;

import java.math.BigDecimal;

public class LcdDisplay {

    public void showSum(BigDecimal sum){
        System.out.println("LCD Display - sum of products: " + sum);
    }

    public void showMessage(String name, String price){
        System.out.println("Product name: " + name + ", price: " + price);
    }

    public void showErrorMessage(String errorMessage){
        System.out.println("Error: " + errorMessage);
    }
}
