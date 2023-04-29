package es.uma.proyectotaw.dto.management;

import es.uma.proyectotaw.dto.AddressDTO;

/**
 * @author Ignacio Alba
 */
public class FullClientDTO {
    private int id;
    private String identificationNumber;
    private String phone;
    private String status;
    private FullAccountDTO account;
    private AddressDTO address;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FullAccountDTO getAccount() {
        return account;
    }

    public void setAccount(FullAccountDTO account) {
        this.account = account;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
