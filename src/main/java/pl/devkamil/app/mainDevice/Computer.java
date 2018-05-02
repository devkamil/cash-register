package pl.devkamil.app.mainDevice;

import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.inputDevices.BarCodeScanner;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.outputDevices.LcdDisplay;
import pl.devkamil.app.outputDevices.Printer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Computer {

    private final static String EXIT = "exit";
    private final static String EMPTY_BAR_CODE = "";
    private BarCodeScanner barCodeScanner;
    private Database database;
    private LcdDisplay lcdDisplay;
    private Printer printer;
    private List<Printable> listOfProducts;


    public Computer(){
        barCodeScanner = new BarCodeScanner();
        database = new Database();
        lcdDisplay = new LcdDisplay();
        printer = new Printer();
        listOfProducts = new ArrayList();
    }


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

    private BigDecimal sumOfProducts(List<Printable> listOfProduct) {
        BigDecimal sumOfPrice = new BigDecimal("0");
        for(Printable product: listOfProduct){
            Product p = (Product) product;
            sumOfPrice = sumOfPrice.add(p.getPrice());
        }
        return sumOfPrice;
    }

    private boolean verifyBarCode(BarCode barCode) throws InvalidBarCodeException {
        if (!EMPTY_BAR_CODE.equals(barCode.getBarCode())){
            return true;
        }
        throw new InvalidBarCodeException();
    }

    private Product findProductByBarCode(BarCode barCode) throws ProductNotFoundException {
        return database.findProductByBarCode(barCode);
    }

    private void showAndPrintResult(List<Printable> listPrintable){
        BigDecimal sumOfProducts = sumOfProducts(listPrintable);
        printer.print(listPrintable);
        printer.printSum(sumOfProducts);
        lcdDisplay.showSum(sumOfProducts);
        barCodeScanner.closeInput();
    }

}
