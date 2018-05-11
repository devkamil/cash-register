package pl.devkamil.app.outputDevices;

import org.springframework.stereotype.Component;
import pl.devkamil.app.model.Printable;

import java.math.BigDecimal;
import java.util.Formatter;
import java.util.List;

@Component
public class Printer {
    private final static String LIST_OF_PRODUCTS = "Printer - list of Products: %n";
    private final static String SUM_OF_PRODUCTS = "Printer - sum of products: %.2f %n";

    public void print(List<Printable> listOfProducts){
        StringBuilder stringBuilder = new StringBuilder();
        for(Printable printable: listOfProducts){
            stringBuilder.append(printable.getPrintMessage());
        }
        System.out.format(LIST_OF_PRODUCTS + stringBuilder);
    }

    public void printSum(BigDecimal sumOfProducts){
        System.out.format(SUM_OF_PRODUCTS, sumOfProducts);
    }
}
