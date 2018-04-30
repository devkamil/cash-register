package pl.devkamil.app.model;

import java.util.Objects;

public class BarCode {
    private String barCode;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public BarCode(){}

    public BarCode(String barCode) {
        this.barCode = barCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarCode barCode1 = (BarCode) o;
        return Objects.equals(barCode, barCode1.barCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(barCode);
    }
}
