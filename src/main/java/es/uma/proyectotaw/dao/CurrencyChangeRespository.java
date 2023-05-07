package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.CurrencyChangeEntity;
import es.uma.proyectotaw.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Marina Sayago - Cliente
 */
public interface CurrencyChangeRespository extends JpaRepository<CurrencyChangeEntity, Integer> {

    /**
     * @author: Marina Sayago - Cliente
     */
    @Query("select distinct c.originCurrency from CurrencyChangeEntity c")
    public List<String> getCurrencyChangeByOrigin();

    /**
     * @author: Marina Sayago - Cliente
     */
    @Query("select distinct c.destinationCurrency from CurrencyChangeEntity c")
    public List<String> getCurrencyChangeByDestination();

    /**
     * @author: Marina Sayago - Cliente
     */
    @Query("select c from CurrencyChangeEntity c where c.originCurrency = :originCurrency and " +
            "c.destinationCurrency = :destinationCurrency")
    public CurrencyChangeEntity getCurrencyChangeEntitiesByOriginAndDestination(@Param("originCurrency") String originCurrency,
                                                           @Param("destinationCurrency") String destinationCurrency);
}
