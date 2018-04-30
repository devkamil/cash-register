package pl.devkamil.app.mainDevice;

import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Product;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class Database {

    private Set<Product> setOfProducts;

    public Database(){
        initMockProducts();
    }

    private void initMockProducts(){
        setOfProducts = new HashSet<Product>();
        setOfProducts.add(new Product("Produkt 1", new BigDecimal("19.99"), new BarCode("101")));
        setOfProducts.add(new Product("Produkt 2", new BigDecimal("29.99"), new BarCode("202")));
        setOfProducts.add(new Product("Produkt 3", new BigDecimal("39.99"), new BarCode("303")));
        setOfProducts.add(new Product("Produkt 4", new BigDecimal("49.99"), new BarCode("404")));
        setOfProducts.add(new Product("Produkt 5", new BigDecimal("59.99"), new BarCode("505")));
    }


    public Product findProductByBarCode(BarCode barCode){
        Product product = null;
        for(Product p: setOfProducts){
            if(barCode.equals(p.getBarCode())) {
                product = p;
                break;
            }
        }
        return product;
    }
}
