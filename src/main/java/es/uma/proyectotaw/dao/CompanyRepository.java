package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.AccountStatusEntity;
import es.uma.proyectotaw.entity.ClientStatusEntity;
import es.uma.proyectotaw.entity.CompanyAreaEntity;
import es.uma.proyectotaw.entity.CompanyEntity;
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
