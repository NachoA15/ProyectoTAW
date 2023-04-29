package es.uma.proyectotaw.dto.client;

import java.io.Serializable;

public class CurrencyChangeDTO implements Serializable{
    private int id;
    private String originCurrency;
    private String destinationCurrency;

    public CurrencyChangeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }
}
