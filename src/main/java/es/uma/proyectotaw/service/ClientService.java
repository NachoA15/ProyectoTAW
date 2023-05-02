package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.client.Client_ClientDTO;
import es.uma.proyectotaw.dto.management.FullClientDTO;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.ProfileAuxClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return client.toFullDTO();
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

    public Client_ClientDTO getClient(Integer idClient){
        ClientEntity client = this.clientRepository.findById(idClient).orElse(null);

        return client.toClientDTO();
    }

    public ProfileAuxClient getProfileAux(Integer idClient){
        ProfileAuxClient profileAuxClient = new ProfileAuxClient();
        ClientEntity client = this.clientRepository.findById(idClient).orElse(null);

        profileAuxClient.setIdClient(client.getId());
        profileAuxClient.setName(client.getPersonById().getName());
        profileAuxClient.setSurname(client.getPersonById().getSurname());
        profileAuxClient.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").format(client.getPersonById().getBirthDate()));
        profileAuxClient.setPhone(client.getPhone());
        profileAuxClient.setIdentificationNumber(client.getIdentificationNumber());
        profileAuxClient.setStreet(client.getAddressByAddress().getStreet());
        profileAuxClient.setNumber(client.getAddressByAddress().getNumber());
        profileAuxClient.setCity(client.getAddressByAddress().getCity());
        profileAuxClient.setRegion(client.getAddressByAddress().getRegion());
        profileAuxClient.setZip_code(client.getAddressByAddress().getZipCode());
        profileAuxClient.setCountry(client.getAddressByAddress().getCountry());

        return profileAuxClient;
    }

    public void saveClient(ProfileAuxClient profileAuxClient) throws ParseException {
        ClientEntity client = this.clientRepository.findById(profileAuxClient.getIdClient()).orElse(null);
        PersonEntity person = this.personRepository.getPersonByPersonClient(profileAuxClient.getIdClient());
        AddressEntity address = this.addressRepository.findById(client.getAddressByAddress().getId()).orElse(null);

        person.setName(profileAuxClient.getName());
        person.setSurname(profileAuxClient.getSurname());
        person.setBirthDate(Date.valueOf(profileAuxClient.getBirthDate()));

        this.personRepository.save(person);

        address.setStreet(profileAuxClient.getStreet());
        address.setRegion(profileAuxClient.getRegion());
        address.setNumber(profileAuxClient.getNumber());
        address.setCity(profileAuxClient.getCity());
        address.setZipCode(address.getZipCode());
        address.setCountry(profileAuxClient.getCountry());

        this.addressRepository.save(address);

        client.setPersonById(person);
        client.setPhone(profileAuxClient.getPhone());
        client.setIdentificationNumber(profileAuxClient.getIdentificationNumber());
        client.setAddressByAddress(address);


        this.clientRepository.save(client);

    }

}
