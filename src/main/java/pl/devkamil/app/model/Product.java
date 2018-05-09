package pl.devkamil.app.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product implements Printable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "fk_barcode_id", unique = true)
    private BarCode barCode;


    public Long getId(){
        return id;
    }

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
        return '\n' + name + "   " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        return barCode != null ? barCode.equals(product.barCode) : product.barCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (barCode != null ? barCode.hashCode() : 0);
        return result;
    }
}