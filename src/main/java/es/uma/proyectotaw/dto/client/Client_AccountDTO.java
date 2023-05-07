package es.uma.proyectotaw.dto.client;

import es.uma.proyectotaw.dto.AccountStatusDTO;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author: Marina Sayago - Cliente
 */
public class Client_AccountDTO implements Serializable {
    private int id;
    private String iban;
    private double balance;
    private String currency;
    private Date openingDate;
    private AccountStatusDTO accountStatusByAccountStatus;
    private Client_ClientDTO clientByOwner;


    public Client_AccountDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public AccountStatusDTO getAccountStatusByAccountStatus() {
        return accountStatusByAccountStatus;
    }

    public void setAccountStatusByAccountStatus(AccountStatusDTO accountStatusByAccountStatus) {
        this.accountStatusByAccountStatus = accountStatusByAccountStatus;
    }

    public Client_ClientDTO getClientByOwner() {
        return clientByOwner;
    }

    public void setClientByOwner(Client_ClientDTO clientByOwner) {
        this.clientByOwner = clientByOwner;
    }



}
