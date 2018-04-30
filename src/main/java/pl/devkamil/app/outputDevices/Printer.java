package pl.devkamil.app.outputDevices;

import pl.devkamil.app.model.Printable;

import java.math.BigDecimal;
import java.util.List;

public class Printer {

    public void print(List<Printable> listOfProducts){
        StringBuilder stringBuilder = new StringBuilder();
        for(Printable printable: listOfProducts){
            stringBuilder.append(printable.getPrintMessage());
        }
        System.out.println("Printer - list of Products: " + stringBuilder);
    }

    public void printSum(BigDecimal sumOfProducts){
        System.out.println("Printer - sum of products: " + sumOfProducts);
    }
}
