package es.uma.proyectotaw.ui;

import es.uma.proyectotaw.entity.AccountEntity;

/**
 * @author: Martin Pur - Empresa
 */
public class OperationAuxCompany {
    private AccountEntity origin;
    private AccountEntity destination;
    private double amount;
    private String currentChangeOrigin;
    private String currentChangeDestination;
    private String payment;

    public OperationAuxCompany(){
        this.origin = null;
        this.destination = null;
        this.amount = 0.0;
        this.currentChangeOrigin = "";
        this.currentChangeDestination = "";
        this.payment = "";
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrentChangeOrigin() {
        return currentChangeOrigin;
    }

    public void setCurrentChangeOrigin(String currentChangeOrigin) {
        this.currentChangeOrigin = currentChangeOrigin;
    }

    public String getCurrentChangeDestination() {
        return currentChangeDestination;
    }

    public void setCurrentChangeDestination(String currentChangeDestination) {
        this.currentChangeDestination = currentChangeDestination;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
