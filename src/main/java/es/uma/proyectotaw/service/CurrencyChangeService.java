package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.CurrencyChangeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyChangeService {
    @Autowired
    protected CurrencyChangeRespository currencyChangeRespository;

    /**
     * @author: Marina Sayago - Cliente
     */
    public List<String> getCurrencyChange(){
        List<String> listCurrency = this.currencyChangeRespository.getCurrencyChangeByOrigin();

        return listCurrency;
    }
}
