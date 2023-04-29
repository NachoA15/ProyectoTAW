package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    @Query("select c.identificationNumber from ClientEntity c")
    public List<String> getIdentifications();
}
