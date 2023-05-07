package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.RoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author: Marina Sayago - Cliente
 */
public interface RoleUserRepository extends JpaRepository<RoleUserEntity, Integer> {

    /**
     * @author: Marina Sayago - Cliente
     */
    @Query("select r from RoleUserEntity r where r.role = :role")
    public RoleUserEntity getRoleUserByRole(@Param("role") String role);

    /**
     * @author: Martin Pur - Empresa
     */
    @Query("select ru from RoleUserEntity ru where ru.id = 4 or ru.id = 5")
    public List<RoleUserEntity> getCompanyRoles();
}
