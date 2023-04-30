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
        return account.toDTO();
    }

    public Client_AccountDTO getAccountById(Integer id){
        AccountEntity account = this.accountRepository.findById(id).orElse(null);
        return account.toDTO();
    }

    public List<Client_AccountDTO> getAccount(){
        List<AccountEntity> listAccounts = this.accountRepository.findAll();
        return this.listaEntidadesADTO(listAccounts);
    }


    protected List<Client_AccountDTO> listaEntidadesADTO (List<AccountEntity> lista) {
        ArrayList dtos = new ArrayList<AccountDTO>();

        lista.forEach((final Account account) -> dtos.add(account.toDTO()));

        return dtos;
    }

    public List<Client_AccountDTO> getAccountWithoutMe(Integer idClient){
        List<AccountEntity> listAccounts = this.accountRepository.getAccountWithoutMe(idClient);

        return this.listaEntidadesADTO(listAccounts);
    }

    public void saveActivation(Integer idAccount){
        AccountEntity account = this.accountRepository.findById(idAccount).orElse(null);
        AccountStatusEntity accountStatus = this.accountStatusRepository.findByState("Pending");
        account.setAccountStatusByAccountStatus(accountStatus);

        this.accountRepository.save(account);
    }
}
