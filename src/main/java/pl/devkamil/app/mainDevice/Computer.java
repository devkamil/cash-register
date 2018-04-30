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


    public BigDecimal sumOfProducts(List<Printable> listOfProduct) {
        BigDecimal sumOfPrice = new BigDecimal("0");
        for(Printable product: listOfProduct){
            Product p = (Product) product;
            sumOfPrice = sumOfPrice.add(p.getPrice());
        }
        return sumOfPrice;
    }

    public boolean verifyBarCode(BarCode barCode){
        return (!EMPTY_BAR_CODE.equals(barCode.getBarCode()));
    }

    public Product findProductByBarCode(BarCode barCode){
        return database.findProductByBarCode(barCode);
    }


    public void run(){


        while(!EXIT.equals(barCodeScanner.getBarCode().getBarCode())){

            lcdDisplay.showInputMessage("Enter the bar code: ");
            BarCode scannedBarCode = barCodeScanner.scan();

            if(EXIT.equals(scannedBarCode))
                break;

            if(verifyBarCode(scannedBarCode)){
                Product product = findProductByBarCode(scannedBarCode);
                if(product != null){
                    listOfProducts.add(product);
                    lcdDisplay.showMessage(product.getName(), product.getPrice());
                }else {
//                    if(!EXIT.equals(barCodeScanner.getBarCode().getBarCode())){
                    lcdDisplay.showErrorMessage("Product doesn't exist in database");
                }
            }else{
                lcdDisplay.showErrorMessage("Invalid bar code, try again");
            }
        }
        showAndPrintResult(listOfProducts);
    }


    public void showAndPrintResult(List<Printable> listPrintable){
        BigDecimal sumOfProducts = sumOfProducts(listPrintable);
        printer.print(listPrintable);
        printer.printSum(sumOfProducts);
        lcdDisplay.showSum(sumOfProducts);
        barCodeScanner.closeInput();
    }

}
