package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "parts")
public class Part extends BaseEntity{

    @Column(name = "part_name",unique = true, nullable = false)
    private String partName;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name="quantity", nullable = false)
    private Integer quantity;

    public Part() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
