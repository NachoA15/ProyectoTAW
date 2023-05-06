package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.UserDTO;

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
@Table(name = "user", schema = "taw24", catalog = "")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "email", nullable = false, length = 45)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 45)
    private String password;
    @OneToMany(mappedBy = "userByAssistant")
    private Collection<ChatEntity> chatsById;
    @OneToOne(mappedBy = "userByCompanyUser")
    private CompanyEntity companyById;
    @OneToMany(mappedBy = "userByWriter")
    private Collection<MessageEntity> messagesById;
    @OneToOne(mappedBy = "userByPersonUser")
    private PersonEntity personById;
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    private RoleUserEntity roleUserByRole;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password);
    }

    public Collection<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public CompanyEntity getCompanyById() {
        return companyById;
    }

    public void setCompaniesById(CompanyEntity companyById) {
        this.companyById = companyById;
    }

    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
    }

    public PersonEntity getPersonById() {
        return personById;
    }

    public void setPersonById(PersonEntity personById) {
        this.personById = personById;
    }

    public RoleUserEntity getRoleUserByRole() {
        return roleUserByRole;
    }

    public void setRoleUserByRole(RoleUserEntity roleUserByRole) {
        this.roleUserByRole = roleUserByRole;
    }

    /**
     * @author: Iván Delgado
     */
    public UserDTO toDTO(){
        UserDTO dto = new UserDTO();
        dto.setId(getId());
        dto.setEmail(getEmail());
        dto.setPassword(getPassword());
        dto.setRole(getRoleUserByRole().getRole());

        if (this.personById != null) {
            String clientStatus = this.personById.getClientByPersonClient() != null? this.personById.getClientByPersonClient().getClientStatusByStatus().getStatus() : null;
            dto.setClientStatus(clientStatus);
        } else if (this.companyById != null){
            dto.setClientStatus(this.companyById.getClientByCompanyClient().getClientStatusByStatus().getStatus());
        }

        return dto;
    }
}
