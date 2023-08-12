package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class MechanicSeedDto {

    @Expose
    @Size(min=2)
    private String firstName;
    @Expose
    @Size(min=2)
    private String lastName;
    @Expose
    @Email
    private String email;
    @Expose
    @Size(min=2)
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public MechanicSeedDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public MechanicSeedDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public MechanicSeedDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public MechanicSeedDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}
