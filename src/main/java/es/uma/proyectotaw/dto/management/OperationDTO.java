package es.uma.proyectotaw.dto.management;

import es.uma.proyectotaw.dto.management.PartialAccountDTO;

import java.sql.Date;

/**
 * @author Ignacio Alba
 */
public class OperationDTO {
    private int id;
    private PartialAccountDTO origin;
    private PartialAccountDTO destination;
    private Date date;
    private double amount;
    private String currency;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PartialAccountDTO getOrigin() {
        return origin;
    }

    public void setOrigin(PartialAccountDTO origin) {
        this.origin = origin;
    }

    public PartialAccountDTO getDestination() {
        return destination;
    }

    public void setDestination(PartialAccountDTO destination) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
