package pl.devkamil.app.inputDevices;

import pl.devkamil.app.model.BarCode;

import java.util.Scanner;

public class BarCodeScanner {

    public BarCode barCode = new BarCode();
    private Scanner input = new Scanner(System.in);


    public Scanner getInput() {
        return input;
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
