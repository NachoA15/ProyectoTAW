package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    /**
     * @author: Marina Sayago
     */

    @Query("select a from AccountEntity a where a.clientByOwner.id = :idClient")
    public AccountEntity getAccountByIdClient(@Param("idClient") Integer idClient);

    @Query("select a from AccountEntity a where not a.clientByOwner.id = :idClient")
    public List<AccountEntity> getAccountWithoutMe(@Param("idClient")Integer idClient);

    /**
     * @author: Martin Pur
     */

    @Query("select a from AccountEntity a where a.clientByOwner.personById.companyByRelatedCompany.id = :idCompany")
    public List<AccountEntity> getAccountsByCompany(@Param("idCompany") Integer idCompany);
}
