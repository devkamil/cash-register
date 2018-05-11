package pl.devkamil.app.mainDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Component
public class Computer {

    private final static String EXIT = "exit";
    private List<Printable> listOfProducts;


    @Autowired
    private ProductService productService;


    public void run(){

        listOfProducts = new ArrayList<>();
        BarCode scannedBarCode = null;

        while(!EXIT.equals(scannedBarCode)){

            productService.showInputMessage();
            scannedBarCode = productService.barCodeScan();

            if(EXIT.equals(scannedBarCode.getBarCode())) {
                break;
            }

            try {
                productService.verifyBarCode(scannedBarCode);
                Product product = productService.findProductByBarCode(scannedBarCode);
                listOfProducts.add(product);
                productService.showOneProductMessage(product.getName(), product.getPrice());
            }catch(InvalidBarCodeException ex){
                productService.showErrorMessage(ex.getMessage());
            }catch(ProductNotFoundException ex){
                productService.showErrorMessage(ex.getMessage());
            }
        }
        productService.showAndPrintResult(listOfProducts);
    }
}
