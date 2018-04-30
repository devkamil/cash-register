package pl.devkamil.app.mainDevice;

import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Database {

    //    TODO Change List to Set or Map

    private List<Product> listOfProducts;

    public List<Product> getListOfProducts() {
        return listOfProducts;
    }


    public void testList(){
        listOfProducts = new ArrayList();
        getListOfProducts().add(new Product("Produkt 1", new BigDecimal("19.99"), new BarCode("101")));
        getListOfProducts().add(new Product("Produkt 2", new BigDecimal("29.99"), new BarCode("202")));
        getListOfProducts().add(new Product("Produkt 3", new BigDecimal("39.99"), new BarCode("303")));
        getListOfProducts().add(new Product("Produkt 4", new BigDecimal("49.99"), new BarCode("404")));
        getListOfProducts().add(new Product("Produkt 5", new BigDecimal("59.99"), new BarCode("505")));
    }
}
