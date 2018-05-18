package pl.devkamil.app.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.mainDevice.Database;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static ProductNotFoundException productNotFoundException;
    private static final String NON_EXISTS_PRODUCT_BAR_CODE = "";
    private static final String EXISTS_PRODUCT_BAR_CODE = "101";
    private static final String TEST_PRODUCT_BAR_CODE = "5555";
    private static BarCode nonExistsProductBarCode;
    private static BarCode existsProductBarCode;
    private static BarCode testProductBarCode;
    private static Product existsProduct;
    private static Product testProduct;
    private ProductService productService;

    @Mock
    private static Database database;


    @Before
    public void setUp() throws ProductNotFoundException {
        productService = new ProductService();
        Mockito.when(database.findProductByBarCode(nonExistsProductBarCode)).thenThrow(productNotFoundException);
        Mockito.when(database.findProductByBarCode(testProductBarCode)).thenReturn(testProduct);
        Mockito.when(database.findProductByBarCode(existsProductBarCode)).thenReturn(existsProduct);
    }

    @BeforeClass
    public static void setUpClass() {
        productNotFoundException = new ProductNotFoundException(nonExistsProductBarCode);
        nonExistsProductBarCode = new BarCode(NON_EXISTS_PRODUCT_BAR_CODE);
        existsProductBarCode = new BarCode(EXISTS_PRODUCT_BAR_CODE);
        testProductBarCode = new BarCode(TEST_PRODUCT_BAR_CODE);
        existsProduct = new Product("Product 1", new BigDecimal("19.99"), existsProductBarCode);
        testProduct = new Product("Product TestProduct", new BigDecimal("555.55"), testProductBarCode);
    }


    @Test
    public void shouldPassIfBarCodeExistInDatabase() throws InvalidBarCodeException {
        assertTrue(productService.verifyBarCode(existsProductBarCode));
    }

    @Test (expected = InvalidBarCodeException.class)
    public void shouldThrowExceptionIfBarCodeIsInvalid() throws InvalidBarCodeException {
        productService.verifyBarCode(nonExistsProductBarCode);
    }

    @Test
    public void shouldFindAndReturnProduct() throws ProductNotFoundException {
        assertEquals(existsProduct, productService.findProductByBarCode(existsProductBarCode));
    }

    @Test (expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenProductNotFoundInDatabase() throws ProductNotFoundException {
        productService.findProductByBarCode(nonExistsProductBarCode);
    }

    @Test
    public void shouldPassIfTwoProductsNotEquals() throws ProductNotFoundException {
        assertNotEquals(testProduct, productService.findProductByBarCode(existsProductBarCode));
    }

    @Test
    public void shouldPassIfCorrectlyAddsAllPricesOfProducts(){
        List<Printable> printableTestList = new ArrayList();
        printableTestList.add(testProduct);
        printableTestList.add(existsProduct);
        assertEquals(new BigDecimal("575.54"), productService.sumOfProducts(printableTestList));
    }

    @Test
    public void shouldReturnZeroIfMethodsGetsAEmptyList(){
        assertEquals(BigDecimal.ZERO, productService.sumOfProducts(new ArrayList<Printable>()));
    }

    @Test (expected = NullPointerException.class)
    public void shouldThrowNullPointerException(){
        productService.sumOfProducts(null);
    }

}
