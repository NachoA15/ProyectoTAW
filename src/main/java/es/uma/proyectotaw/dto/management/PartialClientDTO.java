package es.uma.proyectotaw.dto.management;

/**
 * @author Ignacio Alba - Gestor
 */
public class PartialClientDTO {
    private int id;
    private String identificationNumber;
    private String status;
    private String phone;
    private String city;
    private String country;
    private PartialAccountDTO account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PartialAccountDTO getAccount() {
        return account;
    }

    public void setAccount(PartialAccountDTO account) {
        this.account = account;
    }
}
