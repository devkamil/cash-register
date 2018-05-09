package pl.devkamil.app.outputDevices;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LcdDisplay {

    public void showInputMessage(String enterMessage){
        System.out.println(enterMessage);
    }

    public void showSum(BigDecimal sum){
        System.out.println("LCD Display - sum of products: " + sum);
    }

    public void showMessage(String name, BigDecimal price){
        System.out.println("Product name: " + name + ", price: " + price);
    }

    public void showErrorMessage(String errorMessage){
        System.out.println("Error: " + errorMessage);
    }
}
