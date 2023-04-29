package es.uma.proyectotaw.ui;

import es.uma.proyectotaw.entity.Account;

public class OperationAux {

    private Integer client;
    private Integer origin;
    private Integer destination;
    private String amount;
    private String currentChangeOrigin;
    private String currentChangeDestination;
    private String payment;

    public OperationAux(){
       this.amount = "";
       this.currentChangeDestination = "";
       this.currentChangeOrigin = "";
       this.payment = "";
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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
