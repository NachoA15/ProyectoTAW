package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.AccountStatusDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Al final del archivo están los métodos necesarios para convertir un objeto de esta clase a
 * su DTO correspondiente.
 *
 * Cada método está etiquetado con el nombre del autor/a.
 */
@Entity
@Table(name = "account_status", schema = "taw24", catalog = "")
public class AccountStatusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "state", nullable = false, length = 45)
    private String state;
    @OneToMany(mappedBy = "accountStatusByAccountStatus")
    private Collection<AccountEntity> accountsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountStatusEntity that = (AccountStatusEntity) o;
        return id == that.id && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    public Collection<AccountEntity> getAccountsById() {
        return accountsById;
    }

    public void setAccountsById(Collection<AccountEntity> accountsById) {
        this.accountsById = accountsById;
    }

    /**
     * @author: Ignacio Alba
     */
    public AccountStatusDTO toDTO() {
        AccountStatusDTO dto = new AccountStatusDTO();
        dto.setId(this.id);
        dto.setStatus(this.state);
        return dto;
    }
}
