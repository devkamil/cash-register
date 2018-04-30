package pl.devkamil.app.mainDevice;

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
    BarCodeScanner barCodeScanner = new BarCodeScanner();
    Database database = new Database();
    List<Printable> listOfProducts = new ArrayList();
    LcdDisplay lcdDisplay = new LcdDisplay();
    Printer printer = new Printer();


    public BigDecimal sumOfProducts(List<Printable> listOfProduct) {
        BigDecimal sumOfPrice = new BigDecimal("0");
        for(Printable product: listOfProduct){
            Product p = (Product) product;
            sumOfPrice = sumOfPrice.add(p.getPrice());
        }
        return sumOfPrice;
    }

    public boolean verifyBarCode(BarCode barCode){
        if(!"".equals(barCode.getBarCode())){
            return true;
        } else {
            return false;
        }
    }

    public Product findProduct(BarCode barCode, Database database){
        Product product = new Product();
        for(Product p: database.getListOfProducts()){
            if(barCode.equals(p.getBarCode())) {
                product = p;
                break;
            }else{
                product = null;
            }
        }
        return product;
    }


    public void run(){
        database.testList();

        while(!EXIT.equals(barCodeScanner.barCode.getBarCode())){
            BarCode scannedBarCode = barCodeScanner.scan();

            if(verifyBarCode(scannedBarCode) == true){
                Product product = findProduct(scannedBarCode, database);
                if(product != null){
                    listOfProducts.add(product);
                }else if(!EXIT.equals(barCodeScanner.barCode.getBarCode())){
                    System.out.println("Product doesn't exist in database");
                }
            }else{
                System.out.println("Invalid bar code, try again");
            }
        }
        BigDecimal sumOfProducts = sumOfProducts(listOfProducts);
        printer.print(listOfProducts);
        printer.printSum(sumOfProducts);
        lcdDisplay.showSum(sumOfProducts);
        barCodeScanner.closeInput();
    }


}
