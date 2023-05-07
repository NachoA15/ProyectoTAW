package es.uma.proyectotaw.dto.management;

import es.uma.proyectotaw.dto.client.CurrencyChangeDTO;
import es.uma.proyectotaw.dto.management.PartialAccountDTO;

import java.sql.Date;

/**
 * @author Ignacio Alba - Gestor
 */
public class OperationDTO {
    private int id;
    private PartialAccountDTO origin;
    private PartialAccountDTO destination;
    private Date date;
    private double amount;
    private CurrencyChangeDTO currency;
    private String payment;

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

    public CurrencyChangeDTO getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyChangeDTO currency) {
        this.currency = currency;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
