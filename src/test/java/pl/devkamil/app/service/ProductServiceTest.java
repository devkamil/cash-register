package pl.devkamil.app.service;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    private static final String NON_EXISTS_PRODUCT_BAR_CODE = "";
    private static final String EXISTS_PRODUCT_BAR_CODE = "101";
    private static final String TEST_PRODUCT_BAR_CODE = "5555";
    private static BarCode nonExistsProductBarCode;
    private static BarCode existsProductBarCode;
    private static BarCode testProductBarCode;
    private static Product existsProduct;
    private static Product testProduct;

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;


    @BeforeClass
    public static void setUpClass() {
        nonExistsProductBarCode = new BarCode(NON_EXISTS_PRODUCT_BAR_CODE);
        existsProductBarCode = new BarCode(EXISTS_PRODUCT_BAR_CODE);
        testProductBarCode = new BarCode(TEST_PRODUCT_BAR_CODE);
        existsProduct = new Product("Product 1", new BigDecimal("19.99"), existsProductBarCode);
        testProduct = new Product("Product TestProduct", new BigDecimal("555.55"), testProductBarCode);
    }

    @Before
    public void setUp() {
        Mockito.when(productRepository.findByBarCodeBarCode(TEST_PRODUCT_BAR_CODE)).thenReturn(testProduct);
        Mockito.when(productRepository.findByBarCodeBarCode(EXISTS_PRODUCT_BAR_CODE)).thenReturn(existsProduct);
    }


    @Test
    public void whenValidBarCode_thenProductShouldBeFound() throws ProductNotFoundException {
        BarCode testBarCode = productService.findProductByBarCode(testProductBarCode).getBarCode();
        assertThat(testBarCode.getBarCode()).isEqualTo(TEST_PRODUCT_BAR_CODE);
    }

    @Test (expected = ProductNotFoundException.class)
    public void shouldThrowExceptionWhenProductNotFoundInDatabase() throws ProductNotFoundException {
        ProductNotFoundException productNotFoundException = new ProductNotFoundException(nonExistsProductBarCode);
        Mockito.when(productService.findProductByBarCode(nonExistsProductBarCode)).thenThrow(productNotFoundException);
        productService.findProductByBarCode(nonExistsProductBarCode);
    }

    @Test (expected = NullPointerException.class)
    public void whenNullAsArgument_thenThrowNullPointerException() throws ProductNotFoundException {
        productService.findProductByBarCode(null);
    }

    @Test
    public void shouldReturnTrueIfBarCodeIsValid() throws InvalidBarCodeException {
        assertTrue(productService.verifyBarCode(existsProductBarCode));
    }

    @Test (expected = InvalidBarCodeException.class)
    public void shouldThrowExceptionIfBarCodeIsInvalid() throws InvalidBarCodeException {
        productService.verifyBarCode(nonExistsProductBarCode);
    }

    @Test
    public void shouldFindAndReturnProduct() throws ProductNotFoundException {
        assertEquals(testProduct, productService.findProductByBarCode(testProductBarCode));
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