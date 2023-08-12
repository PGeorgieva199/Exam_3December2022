package softuni.exam.models.dto;

import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class TaskSeedDto {

    @XmlElement(name ="date")
    private String date;
    @XmlElement(name ="price")
    @Positive
    private BigDecimal price;
    @XmlElement(name ="car")
    @NotNull
    private CarId car;
    @XmlElement(name ="mechanic")
    @NotNull
    private MechanicDtoName mechanic;
    @XmlElement(name ="part")
    @NotNull
    private PartId part;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CarId getCar() {
        return car;
    }

    public void setCar(CarId car) {
        this.car = car;
    }

    public MechanicDtoName getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicDtoName mechanic) {
        this.mechanic = mechanic;
    }

    public PartId getPart() {
        return part;
    }

    public void setPart(PartId part) {
        this.part = part;
    }
}
