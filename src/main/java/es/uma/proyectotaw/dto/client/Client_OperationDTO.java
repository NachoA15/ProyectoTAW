package es.uma.proyectotaw.dto.client;


import java.io.Serializable;
import java.sql.Date;

/**
 * @author: Marina Sayago - Cliente
 */
public class Client_OperationDTO implements Serializable {
    private int id;
    private Client_AccountDTO accountOriginByOperation;
    private Client_AccountDTO accountDestinationByOperation;
    private Date date;
    private double amount;
    private CurrencyChangeDTO currencyChangeByCurrencyChange;
    private String paymentByPayment;

    public Client_OperationDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client_AccountDTO getAccountOriginByOperation() {
        return accountOriginByOperation;
    }

    public void setAccountOriginByOperation(Client_AccountDTO accountOriginByOperation) {
        this.accountOriginByOperation = accountOriginByOperation;
    }

    public Client_AccountDTO getAccountDestinationByOperation() {
        return accountDestinationByOperation;
    }

    public void setAccountDestinationByOperation(Client_AccountDTO accountDestinationByOperation) {
        this.accountDestinationByOperation = accountDestinationByOperation;
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

    public CurrencyChangeDTO getCurrencyChangeByCurrencyChange() {
        return currencyChangeByCurrencyChange;
    }

    public void setCurrencyChangeByCurrencyChange(CurrencyChangeDTO currencyChangeByCurrencyChange) {
        this.currencyChangeByCurrencyChange = currencyChangeByCurrencyChange;
    }

    public String getPaymentByPayment() {
        return paymentByPayment;
    }

    public void setPaymentByPayment(String paymentByPayment) {
        this.paymentByPayment = paymentByPayment;
    }
}
