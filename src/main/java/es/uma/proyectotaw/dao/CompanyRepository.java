package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {

    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status not like 'Pending'")
    public List<CompanyEntity> getRegisteredCompanies();

    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status like 'Pending'")
    public List<CompanyEntity> getPendingCompanies();

    @Query("select c from CompanyEntity c where c.clientByCompanyClient.clientStatusByStatus.status in :clientStatuses" +
            " and c.clientByCompanyClient.accountById.accountStatusByAccountStatus.state in :accountStatuses" +
            " and c.area.area in :areas" +
            " and ((upper(c.name) like upper(concat('%',:text,'%')) or upper(c.userByCompanyUser.email)" +
            " like upper(concat('%',:text,'%'))))")
    public List<CompanyEntity> getCompaniesByTextAndStatusLists(@Param("text")String text,
                                                       @Param("clientStatuses")List<String> clientStatuses,
                                                       @Param("accountStatuses")List<String> accountStatuses,
                                                       @Param("areas") List<String> areas);


}
