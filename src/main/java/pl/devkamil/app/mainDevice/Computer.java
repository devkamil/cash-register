package pl.devkamil.app.mainDevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.inputDevices.BarCodeScanner;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.outputDevices.LcdDisplay;
import pl.devkamil.app.outputDevices.Printer;
import pl.devkamil.app.repository.ProductRepository;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Computer {

    private final static String EXIT = "exit";
    private final static String EMPTY_BAR_CODE = "";
    private List<Printable> listOfProducts = new ArrayList<>();

    @Autowired
    private BarCodeScanner barCodeScanner;

    @Autowired
    private LcdDisplay lcdDisplay;

    @Autowired
    private Printer printer;

    @Autowired
    private ProductRepository productRepository;


    public void run(){

        BarCode scannedBarCode = null;

        while(!EXIT.equals(scannedBarCode)){

            lcdDisplay.showInputMessage("Enter the bar code: ");
            scannedBarCode = barCodeScanner.scan();

            if(EXIT.equals(scannedBarCode.getBarCode())) {
                break;
            }

            try {
                verifyBarCode(scannedBarCode);
                Product product = findProductByBarCode(scannedBarCode);
                listOfProducts.add(product);
                lcdDisplay.showMessage(product.getName(), product.getPrice());
            }catch(InvalidBarCodeException ex){
                lcdDisplay.showErrorMessage(ex.getMessage());
            }catch(ProductNotFoundException ex){
                lcdDisplay.showErrorMessage(ex.getMessage());
            }
        }
        showAndPrintResult(listOfProducts);
    }

    public BigDecimal sumOfProducts(List<Printable> listOfProduct) {
        BigDecimal sumOfPrice = new BigDecimal("0");
        for(Printable product: listOfProduct){
            Product p = (Product) product;
            sumOfPrice = sumOfPrice.add(p.getPrice());
        }
        return sumOfPrice;
    }

    public boolean verifyBarCode(BarCode barCode) throws InvalidBarCodeException {
        if (!EMPTY_BAR_CODE.equals(barCode.getBarCode())){
            return true;
        }
        throw new InvalidBarCodeException();
    }

    public Product findProductByBarCode(BarCode barCode) throws ProductNotFoundException {
        Product product = productRepository.findByBarCodeBarCode(barCode.getBarCode());
        if(product != null) {
            return product;
        }
        throw new ProductNotFoundException(barCode);
    }

    public void showAndPrintResult(List<Printable> listPrintable){
        BigDecimal sumOfProducts = sumOfProducts(listPrintable);
        printer.print(listPrintable);
        printer.printSum(sumOfProducts);
        lcdDisplay.showSum(sumOfProducts);
        barCodeScanner.closeInput();
    }

}
