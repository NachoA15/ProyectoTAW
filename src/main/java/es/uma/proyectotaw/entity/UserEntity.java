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
    @OneToMany(mappedBy = "userByCompanyUser")
    private Collection<CompanyEntity> companiesById;
    @OneToMany(mappedBy = "userByWriter")
    private Collection<MessageEntity> messagesById;
    @OneToMany(mappedBy = "userByPersonUser")
    private Collection<PersonEntity> peopleById;
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

    public Collection<CompanyEntity> getCompaniesById() {
        return companiesById;
    }

    public void setCompaniesById(Collection<CompanyEntity> companiesById) {
        this.companiesById = companiesById;
    }

    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
    }

    public Collection<PersonEntity> getPeopleById() {
        return peopleById;
    }

    public void setPeopleById(Collection<PersonEntity> peopleById) {
        this.peopleById = peopleById;
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
        return dto;
    }
}
