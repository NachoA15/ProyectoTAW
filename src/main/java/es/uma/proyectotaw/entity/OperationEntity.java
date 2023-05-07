package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.client.Client_OperationDTO;
import es.uma.proyectotaw.dto.management.OperationDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * Al final del archivo están los métodos necesarios para convertir un objeto de esta clase a
 * su DTO correspondiente.
 *
 * Cada método está etiquetado con el nombre del autor/a.
 */
@Entity
@Table(name = "operation", schema = "taw24", catalog = "")
public class OperationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "date", nullable = false)
    private Date date;
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    private double amount;
    @ManyToOne
    @JoinColumn(name = "origin", referencedColumnName = "id", nullable = false)
    private AccountEntity accountByOrigin;
    @ManyToOne
    @JoinColumn(name = "destination", referencedColumnName = "id")
    private AccountEntity accountByDestination;
    @ManyToOne
    @JoinColumn(name = "currency_change", referencedColumnName = "id")
    private CurrencyChangeEntity currencyChangeByCurrencyChange;
    @ManyToOne
    @JoinColumn(name = "payment", referencedColumnName = "id")
    private PaymentEntity paymentByPayment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationEntity that = (OperationEntity) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount);
    }

    public AccountEntity getAccountByOrigin() {
        return accountByOrigin;
    }

    public void setAccountByOrigin(AccountEntity accountByOrigin) {
        this.accountByOrigin = accountByOrigin;
    }

    public AccountEntity getAccountByDestination() {
        return accountByDestination;
    }

    public void setAccountByDestination(AccountEntity accountByDestination) {
        this.accountByDestination = accountByDestination;
    }

    public CurrencyChangeEntity getCurrencyChangeByCurrencyChange() {
        return currencyChangeByCurrencyChange;
    }

    public void setCurrencyChangeByCurrencyChange(CurrencyChangeEntity currencyChangeByCurrencyChange) {
        this.currencyChangeByCurrencyChange = currencyChangeByCurrencyChange;
    }

    public PaymentEntity getPaymentByPayment() {
        return paymentByPayment;
    }

    public void setPaymentByPayment(PaymentEntity paymentByPayment) {
        this.paymentByPayment = paymentByPayment;
    }

    /**
     * @author: Ignacio Alba
     */
    public OperationDTO toManagementDTO() {
        OperationDTO dto = new OperationDTO();
        dto.setId(this.id);
        dto.setOrigin(this.accountByOrigin.toPartialDTO());
        dto.setDestination(this.accountByDestination.toPartialDTO());
        dto.setDate(this.date);
        dto.setAmount(this.amount);
        if (this.currencyChangeByCurrencyChange != null) dto.setCurrency(this.currencyChangeByCurrencyChange.toDTO());
        if (this.paymentByPayment != null) dto.setPayment(this.paymentByPayment.getCurrency());
        return dto;
    }

    /**
     * @author: Marina Sayago
     */
    public Client_OperationDTO ClientToOperationDTO() {
        Client_OperationDTO operationDTO = new Client_OperationDTO();

        operationDTO.setAccountOriginByOperation(getAccountByOrigin().clientToAccountDTO());
        operationDTO.setAmount(getAmount());
        operationDTO.setId(getId());
        operationDTO.setDate(getDate());
        operationDTO.setAccountDestinationByOperation(getAccountByDestination().clientToAccountDTO());
        if(getPaymentByPayment() != null) operationDTO.setPaymentByPayment(getPaymentByPayment().getCurrency());
        if(getCurrencyChangeByCurrencyChange() != null) operationDTO.setCurrencyChangeByCurrencyChange(getCurrencyChangeByCurrencyChange().toDTO());

        return operationDTO;
    }
}
