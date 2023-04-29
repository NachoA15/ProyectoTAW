package es.uma.proyectotaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "role_user", schema = "taw24", catalog = "")
public class RoleUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "role", nullable = false, length = 45)
    private String role;
    @OneToMany(mappedBy = "roleUserByRole")
    private Collection<UserEntity> usersById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUserEntity that = (RoleUserEntity) o;
        return id == that.id && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    public Collection<UserEntity> getUsersById() {
        return usersById;
    }

    public void setUsersById(Collection<UserEntity> usersById) {
        this.usersById = usersById;
    }
}
