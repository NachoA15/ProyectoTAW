package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.management.FullClientDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * @author Ignacio Alba
 */
@Service
public class ClientService {
    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected AccountStatusRepository accountStatusRepository;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected ClientStatusRepository clientStatusRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    public FullClientDTO getClientById(Integer id) {
        ClientEntity client = this.clientRepository.findById(id).orElse(null);
        return client.toDTO();
    }

    public void registerClientByID(Integer id) {
        ClientEntity client = this.clientRepository.findById(id).orElse(null);

        if (client != null) {
            ClientStatusEntity newclientStatus = this.clientStatusRepository.findByState("Active");
            client.setClientStatusByStatus(newclientStatus);

            AccountEntity account = new AccountEntity();
            AccountStatusEntity accountStatus = this.accountStatusRepository.findByState("Active");

            String email = client.getPersonById() == null? client.getCompanyById().getUserByCompanyUser().getEmail()
                    : client.getPersonById().getUserByPersonUser().getEmail();

            account.setClientByOwner(client);
            account.setCurrency("Euro");
            account.setBalance(0.0);
            account.setOpeningDate(new Date(System.currentTimeMillis()));
            account.setAccountStatusByAccountStatus(accountStatus);
            account.setIban(this.generateIBAN(email, client.getPhone()));
            this.accountRepository.save(account);
        }
    }

    public void deleteClientById(Integer id) {
        ClientEntity client = this.clientRepository.findById(id).orElse(null);

        if (client != null) {
            if (client.getPersonById() != null) {
                this.personRepository.deleteById(client.getPersonById().getId());
                this.userRepository.deleteById(client.getPersonById().getUserByPersonUser().getId());
            } else {
                List<PersonEntity> relatedPersons = (List<PersonEntity>) client.getCompanyById().getPeopleById();
                for (PersonEntity p : relatedPersons) {
                    this.personRepository.deleteById(p.getId());
                    this.userRepository.deleteById(p.getUserByPersonUser().getId());
                }

                this.companyRepository.deleteById(client.getCompanyById().getId());
                this.userRepository.deleteById(client.getCompanyById().getUserByCompanyUser().getId());
            }
            this.clientRepository.deleteById(id);
            this.addressRepository.deleteById(client.getAddressByAddress().getId());
        }
    }

    private String generateIBAN(String s1, String s2) {
        String IBAN = "ES" + s1.hashCode() + s2.hashCode();
        if (IBAN.length() >= 24) {
            IBAN = IBAN.substring(0,25);
        }
        return IBAN;
    }

}
