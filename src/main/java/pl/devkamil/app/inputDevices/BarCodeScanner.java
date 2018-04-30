package pl.devkamil.app.inputDevices;

import pl.devkamil.app.model.BarCode;

import java.util.Scanner;

public class BarCodeScanner {

    private BarCode barCode;
    private Scanner input = new Scanner(System.in);

    public Scanner getInput() {
        return input;
    }

    public BarCode getBarCode() {
        return barCode;
    }

    public BarCodeScanner(){
        barCode = new BarCode();
    }

    public BarCode scan(){
        System.out.println("Enter the bar code: ");
        barCode.setBarCode(input.nextLine());
        return barCode;
    }

    public void closeInput(){
        input.close();
    }
}
