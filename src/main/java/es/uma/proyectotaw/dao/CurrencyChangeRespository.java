package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.CurrencyChangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyChangeRespository extends JpaRepository<CurrencyChangeEntity, Integer> {
    @Query("select distinct c.originCurrency from CurrencyChangeEntity c")
    public List<String> getCurrencyChangeByOrigin();

    @Query("select distinct c.destinationCurrency from CurrencyChangeEntity c")
    public List<String> getCurrencyChangeByDestination();
}
