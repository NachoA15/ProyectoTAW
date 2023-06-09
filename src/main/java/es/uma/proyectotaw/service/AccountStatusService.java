package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.AccountStatusRepository;
import es.uma.proyectotaw.dto.AccountStatusDTO;
import es.uma.proyectotaw.entity.AccountStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio Alba
 */
@Service
public class AccountStatusService {

    /*
        =================================================================================================
            GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */

    // Esta lista siempre se actualiza cuando se obtienen todos los status del repositorio
    private List<AccountStatusEntity> accountStatuses;

    @Autowired
    protected AccountStatusRepository accountStatusRepository;

    /**
     * @author: Ignacio Alba
     */
    public List<AccountStatusDTO> listAccountStatuses() {
        List<AccountStatusEntity> accountStatuses = this.accountStatusRepository.findAll();
        this.accountStatuses = accountStatuses;
        return this.listAccountStatusesToDTO(accountStatuses);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<String> listStatusesOnStrings() {
        return this.listAccountStatusesToStrings(this.accountStatuses);
    }

    /**
     * @author: Ignacio Alba
     */
    private List<String> listAccountStatusesToStrings(List<AccountStatusEntity> list) {
        List<String> strings = new ArrayList<>();

        list.forEach((final AccountStatusEntity a) -> strings.add(a.getState()));

        return strings;
    }

    /**
     * @author: Ignacio Alba
     */
    private List<AccountStatusDTO> listAccountStatusesToDTO(List<AccountStatusEntity> list) {
        List<AccountStatusDTO> accountStatusDTOS = new ArrayList<>();

        list.forEach((final AccountStatusEntity as) -> accountStatusDTOS.add(as.toDTO()));

        return accountStatusDTOS;
    }

    /*
        =================================================================================================
            FIN GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */
}
