package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.AccountRepository;
import es.uma.proyectotaw.dao.AccountStatusRepository;
import es.uma.proyectotaw.dto.client.Client_AccountDTO;
import es.uma.proyectotaw.entity.AccountEntity;
import es.uma.proyectotaw.entity.AccountStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected AccountStatusRepository accountStatusRepository;

    public Client_AccountDTO getAccountByIdClient(Integer idClient){
        AccountEntity account = this.accountRepository.getAccountByIdClient(idClient);
        return account.clientToAccountDTO();
    }

    public Client_AccountDTO getAccountById(Integer id){
        AccountEntity account = this.accountRepository.findById(id).orElse(null);
        return account.clientToAccountDTO();
    }

    /*
        =================================================================================================
            CLIENTE  -- Autora: Marina Sayago
        =================================================================================================
     */

    /**
     * @author: Marina Sayago
     */
    public List<Client_AccountDTO> getAccount(){
        List<AccountEntity> listAccounts = this.accountRepository.findAll();
        return this.listaEntidadesADTO(listAccounts);
    }

    /**
     * @author: Marina Sayago
     */
    protected List<Client_AccountDTO> listaEntidadesADTO (List<AccountEntity> lista) {
        ArrayList dtos = new ArrayList<Client_AccountDTO>();

        lista.forEach((final AccountEntity account) -> dtos.add(account.clientToAccountDTO()));

        return dtos;
    }

    /**
     * @author: Marina Sayago
     */
    public List<Client_AccountDTO> getAccountWithoutMe(Integer idClient){
        List<AccountEntity> listAccounts = this.accountRepository.getAccountWithoutMe(idClient);

        return this.listaEntidadesADTO(listAccounts);
    }

    /**
     * @author: Marina Sayago
     */
    public void saveActivation(Integer idAccount){
        AccountEntity account = this.accountRepository.findById(idAccount).orElse(null);
        AccountStatusEntity accountStatus = this.accountStatusRepository.findByState("Pending");
        account.setAccountStatusByAccountStatus(accountStatus);

        this.accountRepository.save(account);
    }

    /*
        =================================================================================================
            FIN CLIENTE  -- Autora: Marina Sayago
        =================================================================================================
     */
}
