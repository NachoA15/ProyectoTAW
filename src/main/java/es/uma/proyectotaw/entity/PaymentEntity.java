package es.uma.proyectotaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "payment", schema = "taw24", catalog = "")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "currency", nullable = false, length = 45)
    private String currency;
    @OneToMany(mappedBy = "paymentByPayment")
    private Collection<OperationEntity> operationsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return id == that.id && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, currency);
    }

    public Collection<OperationEntity> getOperationsById() {
        return operationsById;
    }

    public void setOperationsById(Collection<OperationEntity> operationsById) {
        this.operationsById = operationsById;
    }
}
