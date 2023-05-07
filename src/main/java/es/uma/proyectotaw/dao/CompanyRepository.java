package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {

    /*
    =================================================================================================
        GESTOR  -- Autor: Ignacio Alba
    =================================================================================================
    */

    /**
     * @author: Ignacio Alba
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status not like 'Pending'")
    public List<CompanyEntity> getRegisteredCompanies();

    /**
     * @author: Ignacio Alba
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status like 'Pending'")
    public List<CompanyEntity> getPendingCompanies();

    /**
     * @author Ignacio Alba
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status not like 'Blocked' " +
            "and ((c.clientByCompanyClient.id in (" +
            "select o.accountByOrigin.clientByOwner.id from OperationEntity o where FUNCTION('DATEDIFF',current_date,o.date) > 30 " +
            "and ((o.accountByOrigin.id, o.date) in (select op.accountByOrigin.id, max(op.date) from OperationEntity op group by op.accountByOrigin.id)) " +
            "group by o.accountByOrigin.clientByOwner.id)) " +
            "or " +
            "(c.clientByCompanyClient.id in (" +
            "select a.clientByOwner.id from AccountEntity a where FUNCTION('DATEDIFF',current_date,a.openingDate) > 30 " +
            "and a.id not in (select o.accountByOrigin.id from OperationEntity o group by o.accountByOrigin.id))))")
    public List<CompanyEntity> getInactiveCompanies();

    /**
     * @author: Ignacio Alba
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.accountById.id in (" +
            "select distinct o.accountByOrigin.id from OperationEntity o where o.accountByOrigin.accountStatusByAccountStatus.state like 'Suspicious'" +
            "or o.accountByDestination.accountStatusByAccountStatus.state like 'Suspicious') " +
            "or c.clientByCompanyClient.accountById.id in (" +
            "select distinct o.accountByOrigin.id from OperationEntity o where o.accountByOrigin.accountStatusByAccountStatus.state like 'Suspicious'" +
            "or o.accountByDestination.accountStatusByAccountStatus.state like 'Suspicious')")
    public List<CompanyEntity> getSuspiciousCompanies();

    /**
     * @author: Ignacio Alba
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status in :clientStatuses" +
            " and c.clientByCompanyClient.accountById.accountStatusByAccountStatus.state in :accountStatuses" +
            " and c.area.area in :areas" +
            " and ((upper(c.name) like upper(concat('%',:text,'%')) or upper(c.userByCompanyUser.email)" +
            " like upper(concat('%',:text,'%'))))")
    public List<CompanyEntity> getCompaniesByTextAndStatusLists(@Param("text")String text,
                                                       @Param("clientStatuses")List<String> clientStatuses,
                                                       @Param("accountStatuses")List<String> accountStatuses,
                                                       @Param("areas") List<String> areas);

    /*
    =================================================================================================
        FIN GESTOR  -- Autor: Ignacio Alba
    =================================================================================================
    */

    /*
    =================================================================================================
        EMPRESA  -- Autor: Martin Pur
    =================================================================================================
    */

    /**
     * @author: Martin Pur
     */
    @Query("select c from CompanyEntity c where upper(c.name) like upper(concat('%',:text,'%')) or " +
            "upper(c.userByCompanyUser.email) like upper(concat('%',:text,'%'))")
    public List<CompanyEntity> getCompaniesByText(@Param("text") String text);

    /**
     * @author: Martin Pur
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus in :clientStatuses" +
            " and c.clientByCompanyClient.accountById.accountStatusByAccountStatus in :accountStatuses" +
            " and c.area in :areas")
    public List<CompanyEntity> getCompaniesByStatusListsAndArea(@Param("clientStatuses")List<ClientStatusEntity> clientStatuses,
                                                          @Param("accountStatuses")List<AccountStatusEntity> accountStatuses,
                                                          @Param("areas")List<CompanyAreaEntity> areas);

    /**
     * @author: Martin Pur
     */
    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus in :clientStatuses" +
            " and c.clientByCompanyClient.accountById.accountStatusByAccountStatus in :accountStatuses" +
            " and c.area in :areas and (upper(c.name) like upper(concat('%',:text,'%')) or" +
            " upper(c.userByCompanyUser.email) like upper(concat('%',:text,'%')))")
    public List<CompanyEntity> getCompaniesByTextAndStatusListsAndArea(@Param("text") String text,
                                                                 @Param("clientStatuses")List<ClientStatusEntity> clientStatuses,
                                                                 @Param("accountStatuses")List<AccountStatusEntity> accountStatuses,
                                                                 @Param("areas")List<CompanyAreaEntity> areas);

    /**
     * @author: Martin Pur
     */
    @Query("select c from CompanyEntity c where c.userByCompanyUser.id = :idUser")
    public CompanyEntity getCompanyByCompanyUser(@Param("idUser") Integer idUser);

    /**
     * @author: Martin Pur
     */
    @Query("select c from CompanyEntity c where c.name = :companyName")
    public CompanyEntity getCompanyByName(@Param("companyName") String companyName);

    /*
    =================================================================================================
        FIN EMPRESA  -- Autor: Martin Pur
    =================================================================================================
    */
}
