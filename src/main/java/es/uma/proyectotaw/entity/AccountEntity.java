package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.client.Client_AccountDTO;
import es.uma.proyectotaw.dto.client.Client_OperationDTO;
import es.uma.proyectotaw.dto.management.FullAccountDTO;
import es.uma.proyectotaw.dto.management.PartialAccountDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Al final del archivo están los métodos necesarios para convertir un objeto de esta clase a
 * su DTO correspondiente.
 *
 * Cada método está etiquetado con el nombre del autor/a.
 */

@Entity
@Table(name = "account", schema = "taw24", catalog = "")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "iban", nullable = false, length = 45)
    private String iban;
    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    private double balance;
    @Basic
    @Column(name = "currency", nullable = false, length = 45)
    private String currency;
    @Basic
    @Column(name = "opening_date", nullable = false)
    private Date openingDate;
    @ManyToOne
    @JoinColumn(name = "account_status", referencedColumnName = "id", nullable = false)
    private AccountStatusEntity accountStatusByAccountStatus;
    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByOwner;
    @OneToMany(mappedBy = "accountByOrigin")
    private Collection<OperationEntity> operationsById;
    @OneToMany(mappedBy = "accountByDestination")
    private Collection<OperationEntity> operationsById_0;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id && Double.compare(that.balance, balance) == 0 && Objects.equals(iban, that.iban) && Objects.equals(currency, that.currency) && Objects.equals(openingDate, that.openingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, balance, currency, openingDate);
    }

    public AccountStatusEntity getAccountStatusByAccountStatus() {
        return accountStatusByAccountStatus;
    }

    public void setAccountStatusByAccountStatus(AccountStatusEntity accountStatusByAccountStatus) {
        this.accountStatusByAccountStatus = accountStatusByAccountStatus;
    }

    public ClientEntity getClientByOwner() {
        return clientByOwner;
    }

    public void setClientByOwner(ClientEntity clientByOwner) {
        this.clientByOwner = clientByOwner;
    }

    public Collection<OperationEntity> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<OperationEntity> operationsById) {
        this.operationsById = operationsById;
    }

    public Collection<OperationEntity> getOperationsById_0() {
        return operationsById_0;
    }

    public void setOperationsById_0(Collection<OperationEntity> operationsById_0) {
        this.operationsById_0 = operationsById_0;
    }

    /**
     * @author: Ignacio Alba
     */
    public PartialAccountDTO toPartialDTO() {
        PartialAccountDTO dto = new PartialAccountDTO();
        dto.setIban(this.iban);
        dto.setStatus(this.accountStatusByAccountStatus.getState());
        return dto;
    }

    /**
     * @author: Ignacio Alba
     */
    public FullAccountDTO toFullDTO() {
        FullAccountDTO dto = new FullAccountDTO();
        dto.setId(this.id);
        dto.setIban(this.iban);
        dto.setBalance(this.balance);
        dto.setCurrency(this.currency);
        dto.setOpeningDate(this.openingDate);
        dto.setStatus(this.accountStatusByAccountStatus.getState());
        return dto;
    }

    /**
     * @author: Marina Sayago
     */
    public Client_AccountDTO clientToAccountDTO(){
        Client_AccountDTO dto = new Client_AccountDTO();

        dto.setId(getId());
        dto.setIban(getIban());
        dto.setBalance(getBalance());
        dto.setCurrency(getCurrency());
        dto.setOpeningDate(getOpeningDate());
        dto.setAccountStatusByAccountStatus(getAccountStatusByAccountStatus().toDTO());
        dto.setClientByOwner(getClientByOwner().toClientDTO());

        return dto;
    }

    /**
     * @author: Marina Sayago
     */
    protected Collection<Client_OperationDTO> listaEntidadesADTO (Collection<OperationEntity> lista) {
        ArrayList dtos = new ArrayList<Client_OperationDTO>();

        lista.forEach((final OperationEntity operation) -> dtos.add(operation.ClientToOperationDTO()));

        return dtos;
    }
}
