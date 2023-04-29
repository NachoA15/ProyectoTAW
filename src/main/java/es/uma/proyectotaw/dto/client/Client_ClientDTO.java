package es.uma.proyectotaw.dto.client;

import es.uma.proyectotaw.dto.AddressDTO;

import java.io.Serializable;

public class Client_ClientDTO implements Serializable {

    private int id;

    private String identificationNumber;

    private String phone;

    private Client_AccountDTO account;
    private String clientStatusByStatus;
    private AddressDTO addressByAddress;
    private Integer personById;

    public String getClientStatusByStatus() {
        return clientStatusByStatus;
    }

    public void setClientStatusByStatus(String clientStatusByStatus) {
        this.clientStatusByStatus = clientStatusByStatus;
    }

    public AddressDTO getAddressByAddress() {
        return addressByAddress;
    }

    public void setAddressByAddress(AddressDTO addressByAddress) {
        this.addressByAddress = addressByAddress;
    }


    public Integer getPersonById() {
        return personById;
    }

    public void setPersonById(Integer personById) {
        this.personById = personById;
    }

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

    public Client_AccountDTO getAccount() {
        return account;
    }

    public void setAccount(Client_AccountDTO account) {
        this.account = account;
    }

    public Client_ClientDTO() {
    }
}
