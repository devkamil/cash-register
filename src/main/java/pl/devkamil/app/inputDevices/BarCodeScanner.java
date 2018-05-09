package pl.devkamil.app.inputDevices;

import org.springframework.stereotype.Component;
import pl.devkamil.app.model.BarCode;

import java.util.Scanner;

@Component
public class BarCodeScanner {

    private BarCode barCode;
    private Scanner input;

    public Scanner getInput() {
        return input;
    }

    public BarCode getBarCode() {
        return barCode;
    }


    public BarCodeScanner(){
        barCode = new BarCode();
        input = new Scanner(System.in);
    }

    public BarCode scan(){
        barCode.setBarCode(input.nextLine());
        return barCode;
    }

    public void closeInput(){
        input.close();
    }
}
