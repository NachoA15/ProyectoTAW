package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.AccountStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountStatusRepository extends JpaRepository<AccountStatusEntity, Integer> {

    /**
     * @author: Ignacio Alba - Gestor
     */
    @Query("select a from AccountStatusEntity a where a.state = :state")
    public AccountStatusEntity findByState(@Param("state") String state);
}
