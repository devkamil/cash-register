package pl.devkamil.app.service;

import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.inputDevices.BarCodeScanner;
import pl.devkamil.app.mainDevice.Database;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.outputDevices.Printer;

import java.math.BigDecimal;
import java.util.List;

public class ProductService {

    private final static String EMPTY_BAR_CODE = "";
    private BarCodeScanner barCodeScanner;
    private Database database;
    private Printer printer;

    public ProductService(){
        barCodeScanner = new BarCodeScanner();
        database = new Database();
        printer = new Printer();
    }

    public BarCode barCodeScan(){
        return barCodeScanner.scan();
    }

    public Product findProductByBarCode(BarCode barCode) throws ProductNotFoundException {
        return database.findProductByBarCode(barCode);
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

    public void printResult(List<Printable> listPrintable, BigDecimal sumOfProducts){
        printer.print(listPrintable);
        printer.printSum(sumOfProducts);
    }

    public void closeInput(){
        barCodeScanner.closeInput();
    }
}
