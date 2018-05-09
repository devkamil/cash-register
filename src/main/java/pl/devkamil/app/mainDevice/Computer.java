package pl.devkamil.app.mainDevice;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.devkamil.app.database.HibernateUtil;
import pl.devkamil.app.exceptions.InvalidBarCodeException;
import pl.devkamil.app.exceptions.ProductNotFoundException;
import pl.devkamil.app.inputDevices.BarCodeScanner;
import pl.devkamil.app.model.BarCode;
import pl.devkamil.app.model.Printable;
import pl.devkamil.app.model.Product;
import pl.devkamil.app.outputDevices.LcdDisplay;
import pl.devkamil.app.outputDevices.Printer;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Computer {

    private final static String EXIT = "exit";
    private final static String EMPTY_BAR_CODE = "";
    private BarCodeScanner barCodeScanner;
    private LcdDisplay lcdDisplay;
    private Printer printer;
    private List<Printable> listOfProducts;
    private HibernateUtil hibernateUtil;

    public Computer(){
        barCodeScanner = new BarCodeScanner();
        lcdDisplay = new LcdDisplay();
        printer = new Printer();
        listOfProducts = new ArrayList();
        hibernateUtil = new HibernateUtil();
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
        Product product = new Product();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Product P WHERE P.barCode.barCode = :barCode";
            Query query = session.createQuery(hql);
            query.setParameter("barCode", barCode.getBarCode());
            product = (Product) query.getSingleResult();
            session.close();
        }catch(NoResultException ex){
            throw new ProductNotFoundException(barCode);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return product;
    }

    public void showAndPrintResult(List<Printable> listPrintable){
        BigDecimal sumOfProducts = sumOfProducts(listPrintable);
        printer.print(listPrintable);
        printer.printSum(sumOfProducts);
        lcdDisplay.showSum(sumOfProducts);
        barCodeScanner.closeInput();
        HibernateUtil.getSessionFactory().close();
    }

}
