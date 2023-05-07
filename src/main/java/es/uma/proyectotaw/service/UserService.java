package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.UserDTO;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Iván Delgado - Marina Sayago
 */
@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleUserRepository roleUserRepository;

    @Autowired
    protected ClientStatusRepository clientStatusRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected PersonRepository personRepository;

    /**
     * @author: Iván Delgado - Asistente
     */
    public UserDTO authenticate(String email, String password){
        UserEntity user = this.userRepository.authenticate(email, password);
        return user == null? null : user.toDTO();
    }

    /**
     * @author: Marina Sayago - Cliente
     */
    public void save(SignUp signUp) throws ParseException {
        UserEntity user = new UserEntity();
        PersonEntity person = new PersonEntity();
        ClientEntity client = new ClientEntity();
        AddressEntity address = new AddressEntity();
        RoleUserEntity roleUser = this.roleUserRepository.getRoleUserByRole("client");
        ClientStatusEntity clientStatus = this.clientStatusRepository.findByState("Pending");

        int id = (int) (Math.random()*100+1);
        List<String> ids = this.clientRepository.getIdentifications();
        while(ids.contains(id)){
            id++;
        }

        user.setRoleUserByRole(roleUser);
        user.setPassword(signUp.getPassword());
        user.setEmail(signUp.getEmail());


        address.setCity(signUp.getCity());
        address.setCountry(signUp.getCountry());
        address.setNumber(signUp.getNumber());
        address.setRegion(signUp.getRegion());
        address.setStreet(signUp.getStreet());
        address.setZipCode(signUp.getZipCode());

        this.userRepository.save(user);

        client.setAddressByAddress(address);
        client.setClientStatusByStatus(clientStatus);
        client.setIdentificationNumber(id+"");
        client.setPhone(signUp.getPhone());

        this.addressRepository.save(address);
        this.clientRepository.save(client);

        person.setName(signUp.getName());
        person.setSurname(signUp.getSurname());
        person.setBirthDate(Date.valueOf(signUp.getBirthDay()));
        person.setUserByPersonUser(user);
        person.setClientByPersonClient(client);

        this.personRepository.save(person);
    }
}
