package pl.devkamil.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.devkamil.app.mainDevice.Computer;


@SpringBootApplication
public class Main {

    public static void main(String[] args){

        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        Computer computer = ctx.getBean(Computer.class);
        computer.run();
    }
}
