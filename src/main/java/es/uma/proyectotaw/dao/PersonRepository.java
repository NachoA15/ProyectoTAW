package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    /*
    =================================================================================================
        GESTOR  -- Autor: Ignacio Alba
    =================================================================================================
    */

    /**
     * @author Ignacio Alba
     */
    @Query("select p from PersonEntity p where p.clientByPersonClient.clientStatusByStatus.status not like 'Pending'")
    public List<PersonEntity> getRegisteredPersons();

    /**
     * @author Ignacio Alba
     */
    @Query("select p from PersonEntity p where p.clientByPersonClient.clientStatusByStatus.status like 'Pending'")
    public List<PersonEntity> getPendingPersons();

    /**
     * @author Ignacio Alba
     */
    @Query("select p from PersonEntity p")
    public List<PersonEntity> getInactivePersons();

    /**
     * @author Ignacio Alba
     */
    @Query("select p from PersonEntity p where p.clientByPersonClient.clientStatusByStatus.status in :clientStatuses" +
            " and p.clientByPersonClient.accountById.accountStatusByAccountStatus.state in :accountStatuses" +
            " and ((upper(p.name) like upper(concat('%',:text,'%')) or upper(p.surname)" +
            " like upper(concat('%',:text,'%')) or upper(p.userByPersonUser.email)" +
            " like upper(concat('%',:text,'%'))))")
    public List<PersonEntity> getPersonsByTextAndStatusLists(@Param("text")String text,
                                                       @Param("clientStatuses")List<String> clientStatuses,
                                                       @Param("accountStatuses")List<String> accountStatuses);

    /*
    =================================================================================================
        FIN GESTOR  -- Autor: Ignacio Alba
    =================================================================================================
    */

    /*
    =================================================================================================
        CLIENTE  -- Autora: Marina Sayago
    =================================================================================================
    */

    /**
     * @author Marina Sayago
     */
    @Query("select p from PersonEntity p where p.userByPersonUser.id = :idUser")
    public PersonEntity getPersonByPersonUser(@Param("idUser") Integer idUser);

    /**
     * @author Marina Sayago
     */
    @Query("select p from PersonEntity p where p.clientByPersonClient.id = :idClient")
    public PersonEntity getPersonByPersonClient(@Param("idClient") Integer idClient);

    /*
    =================================================================================================
        FIN CLIENTE  -- Autora: Marina Sayago
    =================================================================================================
    */

    /*
    =================================================================================================
        EMPRESA  -- Autor: Martin Pur
    =================================================================================================
    */

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.companyByRelatedCompany.id = :idCompany " +
            "and (p.userByPersonUser.roleUserByRole.id = 4 " +
            "or p.userByPersonUser.roleUserByRole.id = 5)")
    public List<PersonEntity> getPeopleByCompany(@Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.userByPersonUser.email = :email")
    public List<PersonEntity> getByEmail(@Param("email") String email);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.userByPersonUser.email = :email and p.userByPersonUser.roleUserByRole.id = :idRole")
    public List<PersonEntity> getByEmailAndRole(@Param("email") String email, @Param("idRole") Integer idRole);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByName(@Param("name") String name, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.surname = :surname and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getBySurname(@Param("surname") String surname, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.clientByPersonClient.addressByAddress.country = :country and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByCountry(@Param("country") String country, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByRole(@Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.surname = :surname and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameAndSurname(@Param("name") String name, @Param("surname") String surname, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.clientByPersonClient.addressByAddress.country = :country and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameAndCountry(@Param("name") String name, @Param("country") String country, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameAndRole(@Param("name") String name, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.surname = :surname and p.clientByPersonClient.addressByAddress.country = :country and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getBySurnameAndCountry(@Param("surname") String surname, @Param("country") String country, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.surname = :surname and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getBySurnameAndRole(@Param("surname") String surname, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.clientByPersonClient.addressByAddress.country = :country and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByCountryAndRole(@Param("country") String country, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.surname = :surname and p.clientByPersonClient.addressByAddress.country = :country and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getBySurnameCountryRole(@Param("surname") String surname, @Param("country") String country, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.clientByPersonClient.addressByAddress.country = :country and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameCountryRole(@Param("name") String name, @Param("country") String country, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.surname = :surname and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameSurnameRole(@Param("name") String name, @Param("surname") String surname, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.surname = :surname and p.clientByPersonClient.addressByAddress.country = :country and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameSurnameCountry(@Param("name") String name, @Param("surname") String surname, @Param("country") String country, @Param("idCompany") Integer idCompany);

    /**
     * @author Martin Pur
     */
    @Query("select p from PersonEntity p where p.name = :name and p.surname = :surname and p.clientByPersonClient.addressByAddress.country = :country and p.userByPersonUser.roleUserByRole.id = :idRole and p.companyByRelatedCompany.id = :idCompany")
    public List<PersonEntity> getByNameSurnameCountryRole(@Param("name") String name, @Param("surname") String surname, @Param("country") String country, @Param("idRole") Integer idRole, @Param("idCompany") Integer idCompany);

    /*
    =================================================================================================
        FIN EMPRESA  -- Autor: Martin Pur
    =================================================================================================
    */
}
