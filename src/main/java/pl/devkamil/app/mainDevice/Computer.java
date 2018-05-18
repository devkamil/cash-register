package pl.devkamil.app.mainDevice;

import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.outputDevices.LcdDisplay;
import pl.devkamil.app.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Computer {

    private final static String EXIT = "exit";
    private List<Printable> listOfProducts;
    private ProductService productService;
    private LcdDisplay lcdDisplay;


    public Computer(){
        productService = new ProductService();
        lcdDisplay = new LcdDisplay();
    }


    public void run(){

        listOfProducts = new ArrayList();
        BarCode scannedBarCode = null;

        while(!EXIT.equals(scannedBarCode)){

            lcdDisplay.showInputMessage();
            scannedBarCode = productService.barCodeScan();

            if(EXIT.equals(scannedBarCode.getBarCode())) {
                break;
            }

            try {
                productService.verifyBarCode(scannedBarCode);
                Product product = productService.findProductByBarCode(scannedBarCode);
                listOfProducts.add(product);
                lcdDisplay.showOneProductMessage(product.getName(), product.getPrice());
            }catch(InvalidBarCodeException ex){
                lcdDisplay.showErrorMessage(ex.getMessage());
            }catch(ProductNotFoundException ex){
                lcdDisplay.showErrorMessage(ex.getMessage());
            }
        }
        BigDecimal sumOfProducts = productService.sumOfProducts(listOfProducts);
        lcdDisplay.showSum(sumOfProducts);
        productService.printResult(listOfProducts, sumOfProducts);
        productService.closeInput();
    }
}
