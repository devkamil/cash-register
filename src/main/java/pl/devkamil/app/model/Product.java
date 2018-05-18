package pl.devkamil.app.model;

import java.math.BigDecimal;

public class Product implements Printable {

    private String name;
    private BigDecimal price;
    private BarCode barCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BarCode getBarCode() {
        return barCode;
    }

    public void setBarCode(BarCode barCode) {
        this.barCode = barCode;
    }

    public Product() {
    }

    public Product(String name, BigDecimal price, BarCode barCode) {
        this.name = name;
        this.price = price;
        this.barCode = barCode;
    }


    public String getPrintMessage() {
        return String.format("%-20s %8.2f %n", name, price);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        return barCode != null ? barCode.equals(product.barCode) : product.barCode == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (barCode != null ? barCode.hashCode() : 0);
        return result;
    }
}