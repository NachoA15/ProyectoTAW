package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.client.CurrencyChangeDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "currency_change", schema = "taw24", catalog = "")
public class CurrencyChangeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "origin_currency", nullable = false, length = 45)
    private String originCurrency;
    @Basic
    @Column(name = "destination_currency", nullable = false, length = 45)
    private String destinationCurrency;
    @OneToMany(mappedBy = "currencyChangeByCurrencyChange")
    private Collection<OperationEntity> operationsById;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyChangeEntity that = (CurrencyChangeEntity) o;
        return id == that.id && Objects.equals(originCurrency, that.originCurrency) && Objects.equals(destinationCurrency, that.destinationCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originCurrency, destinationCurrency);
    }

    public Collection<OperationEntity> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<OperationEntity> operationsById) {
        this.operationsById = operationsById;
    }

    public CurrencyChangeDTO toDTO() {
        CurrencyChangeDTO dto = new CurrencyChangeDTO();

        dto.setId(getId());
        dto.setDestinationCurrency(getDestinationCurrency());
        dto.setOriginCurrency(getOriginCurrency());

        return dto;
    }
}
