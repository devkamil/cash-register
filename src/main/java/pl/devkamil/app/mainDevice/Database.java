package pl.devkamil.app.mainDevice;

import pl.devkamil.app.exceptions.ProductNotFoundException;
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
        setOfProducts.add(new Product("Product 1", new BigDecimal("19.99"), new BarCode("101")));
        setOfProducts.add(new Product("Product 2", new BigDecimal("29.99"), new BarCode("202")));
        setOfProducts.add(new Product("Product 3", new BigDecimal("39.99"), new BarCode("303")));
        setOfProducts.add(new Product("Product 4", new BigDecimal("49.99"), new BarCode("404")));
        setOfProducts.add(new Product("Product 5", new BigDecimal("59.99"), new BarCode("505")));
        setOfProducts.add(new Product("Product 8", new BigDecimal("888.88"), new BarCode("808")));
        setOfProducts.add(new Product("Product 9", new BigDecimal("99999.99"), new BarCode("909")));
    }


    public Product findProductByBarCode(BarCode barCode) throws ProductNotFoundException{
        for(Product product: setOfProducts){
            if(barCode.equals(product.getBarCode())) {
                return product;
            }
        }
        throw new ProductNotFoundException(barCode);
    }
}
