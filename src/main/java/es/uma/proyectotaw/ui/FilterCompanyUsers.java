package es.uma.proyectotaw.ui;

import es.uma.proyectotaw.entity.RoleUserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: Martin Pur - Empresa
 */
public class FilterCompanyUsers {
    public FilterCompanyUsers() {
        this.name = "";
        this.surname = "";
        this.country = "";
        this.role = null;
    }
    private String name;
    private String surname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String email;
    private String phone;
    private String country;
    private String street;
    private String region;
    private RoleUserEntity role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {return surname;}

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public RoleUserEntity getRole() {
        return role;
    }

    public void setRole(RoleUserEntity role) {
        this.role = role;
    }
}
