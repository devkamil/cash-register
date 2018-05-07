package pl.devkamil.app.model;


import javax.persistence.*;

@Entity
public class BarCode {

    @Id
    @GeneratedValue
    @Column(name = "barcode_id")
    private Long id;
    @Column(name = "barcode")
    private String barCode;

//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode_id")
    private Product product;

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

        return barCode != null ? barCode.equals(barCode1.barCode) : barCode1.barCode == null;
    }

    @Override
    public int hashCode() {
        return barCode != null ? barCode.hashCode() : 0;
    }
}
