package es.uma.proyectotaw.ui;

import es.uma.proyectotaw.entity.AccountEntity;
import es.uma.proyectotaw.entity.ClientEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: Martin Pur - Empresa
 */
public class FilterOperationsCompany {
    public FilterOperationsCompany() {
        this.origin = null;
        this.destination = null;
        this.date = null;
        this.sortBy = "";
    }


    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    private String sortBy;

    private ClientEntity client;

    private AccountEntity origin;
    private AccountEntity destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private double amount;

    public AccountEntity getOrigin() {
        return origin;
    }

    public void setOrigin(AccountEntity origin) {
        this.origin = origin;
    }

    public AccountEntity getDestination() {
        return destination;
    }

    public void setDestination(AccountEntity destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {this.sortBy = sortBy;}
}
