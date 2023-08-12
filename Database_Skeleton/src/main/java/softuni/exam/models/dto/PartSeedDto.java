package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PartSeedDto {
    @Expose
    @Size(min=2, max=19)
    private String partName;
    @Expose
    @Min(10)
    @Max(2000)
    private double price;
    @Expose
    @Positive
    private Integer quantity;

//    public PartSeedDto() {
//    }

    public String getPartName() {
        return partName;
    }

    public PartSeedDto setPartName(String partName) {
        this.partName = partName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public PartSeedDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PartSeedDto setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
