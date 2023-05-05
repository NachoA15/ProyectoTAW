package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    /**
     * @author: Marina Sayago - Cliente
     */
    @Query("select c.identificationNumber from ClientEntity c")
    public List<String> getIdentifications();

    /**
     * @author: Martin Pur - Empresa
     */
    @Query("select cl from ClientEntity cl where cl.personById.userByPersonUser.id = :idUser")
    public ClientEntity getClientByUser(@Param("idUser") Integer idUser);

    /**
     * @author: Martin Pur - Empresa
     */
    @Query("select cl from ClientEntity cl where cl.personById.companyByRelatedCompany.id = :idCompany")
    public List<ClientEntity> getClientsByCompany(@Param("idCompany") Integer idCompany);
}
