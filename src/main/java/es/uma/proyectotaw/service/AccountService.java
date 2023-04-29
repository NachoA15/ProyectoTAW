package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.AccountRepository;
import es.uma.proyectotaw.dao.AccountStatusRepository;
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

    public AccountDTO getAccountByIdClient(Integer idClient){
       Account account = this.accountRepository.getAccountByIdClient(idClient);
        return account.toDTO();
    }

    public AccountDTO getAccountById(Integer id){
        Account account = this.accountRepository.findById(id).orElse(null);
        return account.toDTO();
    }

    public List<AccountDTO> getAccount(){
        List<Account> listAccounts = this.accountRepository.findAll();
        return this.listaEntidadesADTO(listAccounts);
    }


    protected List<AccountDTO> listaEntidadesADTO (List<Account> lista) {
        ArrayList dtos = new ArrayList<AccountDTO>();

        lista.forEach((final Account account) -> dtos.add(account.toDTO()));

        return dtos;
    }

    public List<AccountDTO> getAccountWithoutMe(Integer idClient){
        List<Account> listAccounts = this.accountRepository.getAccountWithoutMe(idClient);

        return this.listaEntidadesADTO(listAccounts);
    }

    public void saveActivation(Integer idAccount){
        Account account = this.accountRepository.findById(idAccount).orElse(null);
        AccountStatus accountStatus = this.accountStatusRepository.findByState("Pending");
        account.setAccountStatusByAccountStatus(accountStatus);

        this.accountRepository.save(account);
    }
}
