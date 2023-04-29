package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClientStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientStatusRepository extends JpaRepository<ClientStatusEntity, Integer> {

    /**
     * @author: Ignacio Alba
     */
    @Query("select c from ClientStatusEntity c where c.status = :state")
    public ClientStatusEntity findByState(@Param("state") String state);
}
