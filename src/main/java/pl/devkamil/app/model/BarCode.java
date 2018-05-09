package pl.devkamil.app.model;


import javax.persistence.*;

@Entity
@Table(name = "barcode")
public class BarCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "barcode_id")
    private Long id;

    @Column(name = "barcode_value")
    private String barCode;

    public Long getId(){
        return id;
    }

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

        if (id != null ? !id.equals(barCode1.id) : barCode1.id != null) return false;
        return barCode != null ? barCode.equals(barCode1.barCode) : barCode1.barCode == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (barCode != null ? barCode.hashCode() : 0);
        return result;
    }
}
