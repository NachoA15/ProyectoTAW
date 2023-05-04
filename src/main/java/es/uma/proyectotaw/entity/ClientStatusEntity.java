package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.ClientStatusDTO;

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
@Table(name = "client_status", schema = "taw24", catalog = "")
public class ClientStatusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "status", nullable = false, length = 45)
    private String status;
    @OneToMany(mappedBy = "clientStatusByStatus")
    private Collection<ClientEntity> clientsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientStatusEntity that = (ClientStatusEntity) o;
        return id == that.id && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    public Collection<ClientEntity> getClientsById() {
        return clientsById;
    }

    public void setClientsById(Collection<ClientEntity> clientsById) {
        this.clientsById = clientsById;
    }

    /**
     * @author: Ignacio Alba
     */
    public ClientStatusDTO toDTO() {
        ClientStatusDTO dto = new ClientStatusDTO();
        dto.setId(this.id);
        dto.setStatus(this.status);
        return dto;
    }
}
