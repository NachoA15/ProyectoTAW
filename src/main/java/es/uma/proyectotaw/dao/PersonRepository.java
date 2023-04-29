package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    @Query("select p from PersonEntity p where p.clientByPersonClient.clientStatusByStatus.status not like 'Pending'")
    public List<PersonEntity> getRegisteredPersons();

    @Query("select p from PersonEntity p where p.clientByPersonClient.clientStatusByStatus.status like 'Pending'")
    public List<PersonEntity> getPendingPersons();

    @Query("select p from PersonEntity p where p.clientByPersonClient.clientStatusByStatus.status in :clientStatuses" +
            " and p.clientByPersonClient.accountById.accountStatusByAccountStatus.state in :accountStatuses" +
            " and ((upper(p.name) like upper(concat('%',:text,'%')) or upper(p.surname)" +
            " like upper(concat('%',:text,'%')) or upper(p.userByPersonUser.email)" +
            " like upper(concat('%',:text,'%'))))")
    public List<PersonEntity> getPersonsByTextAndStatusLists(@Param("text")String text,
                                                       @Param("clientStatuses")List<String> clientStatuses,
                                                       @Param("accountStatuses")List<String> accountStatuses);

}
