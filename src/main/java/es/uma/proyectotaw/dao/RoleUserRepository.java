package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.RoleUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: Marina Sayago
 */
public interface RoleUserRepository extends JpaRepository<RoleUserEntity, Integer> {
    @Query("select r from RoleUserEntity r where r.role = :role")
    public RoleUserEntity getRoleUserByRole(@Param("role") String role);
}
