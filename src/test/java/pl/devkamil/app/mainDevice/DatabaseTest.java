package pl.devkamil.app.mainDevice;

import org.junit.Before;
import org.junit.Test;

import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Product;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private Database database;
    private BarCode existBarCode;
    private BarCode nonExistBarCode;
    private static final String NON_EXIST_PRODUCT = "";
    private static final String EXIST_PRODUCT = "101";


    @Before
    public void start(){
        database = new Database();
        existBarCode = new BarCode(EXIST_PRODUCT);
        nonExistBarCode = new BarCode(NON_EXIST_PRODUCT);

    }

    @Test
    public void shouldFindProductInDatabase() throws ProductNotFoundException{
        Product product = database.findProductByBarCode(existBarCode);
        assertEquals(product.getName(), "Produkt 1");
        assertEquals(product.getPrice().toString(), "19.99");
        assertEquals(product.getBarCode(), existBarCode);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldThrownExceptionIfProductNotFoundInDatabase() throws ProductNotFoundException {
        database.findProductByBarCode(nonExistBarCode);
    }
}
