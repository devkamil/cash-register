package pl.devkamil.app.mainDevice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ComputerTest {
    private static final String NON_EXIST_PRODUCT = "";
    private static final String EXIST_PRODUCT = "101";
    private Computer computer;
    private BarCode existBarCode;
    private BarCode nonExistBarCode;

    @Mock
    private Database database;


    @Before
    public void setUp() {
        computer = new Computer();
        existBarCode = new BarCode(EXIST_PRODUCT);
        nonExistBarCode = new BarCode(NON_EXIST_PRODUCT);
    }


    @Test
    public void shouldPassIfBarCodeExistInDatabase() throws InvalidBarCodeException {
        assertTrue(computer.verifyBarCode(existBarCode));
    }

    @Test (expected = InvalidBarCodeException.class)
    public void shouldThrowExceptionIfBarCodeIsInvalid() throws InvalidBarCodeException {
        computer.verifyBarCode(nonExistBarCode);
    }

    @Test
    public void shouldFindAndReturnProduct() throws ProductNotFoundException {
        Product product = new Product("Product 1", new BigDecimal("19.99"), existBarCode);
        Mockito.when(database.findProductByBarCode(existBarCode)).thenReturn(product);
        assertEquals(product, database.findProductByBarCode(existBarCode));
    }

    @Test (expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenProductNotFoundInDatabase() throws ProductNotFoundException {
        ProductNotFoundException exception = new ProductNotFoundException(nonExistBarCode);
        Mockito.when(database.findProductByBarCode(nonExistBarCode)).thenThrow(exception);
        database.findProductByBarCode(nonExistBarCode);
    }

    @Test
    public void shouldPassIfTwoProductsNotEquals() throws ProductNotFoundException {
        Product product = new Product("Product_test_product", new BigDecimal("398"), new BarCode("010101"));
        assertNotEquals(product, database.findProductByBarCode(existBarCode));
    }

    @Test
    public void shouldPassIfCorrectlyAddsAllPricesOfProducts(){
        List<Printable> printableList = new ArrayList();
        printableList.add(new Product("Product 1", new BigDecimal("19.99"), new BarCode("101")));
        printableList.add(new Product("Product 2", new BigDecimal("29.99"), new BarCode("202")));
        assertEquals(new BigDecimal("49.98"), computer.sumOfProducts(printableList));
    }

    @Test
    public void shouldReturnZeroIfMethodsGetsAEmptyList(){
        assertEquals(new BigDecimal("0"), computer.sumOfProducts(new ArrayList<Printable>()));
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerException(){
        computer.sumOfProducts(null);
    }






}
