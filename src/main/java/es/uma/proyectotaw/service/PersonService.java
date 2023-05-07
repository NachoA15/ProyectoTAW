package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.PersonRepository;
import es.uma.proyectotaw.dto.client.Client_PersonDTO;
import es.uma.proyectotaw.dto.management.FullPersonDTO;
import es.uma.proyectotaw.dto.management.PartialPersonDTO;
import es.uma.proyectotaw.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    protected PersonRepository personRepository;

    /*
        =================================================================================================
            GESTOR -- Autor: Ignacio Alba
        =================================================================================================
     */

    /**
     * @author: Ignacio Alba
     */
    public List<PartialPersonDTO> getRegisteredPersons() {
        List<PersonEntity> registeredPersons = this.personRepository.getRegisteredPersons();
        return this.listPersonsToPartialDTO(registeredPersons);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialPersonDTO> getRegisteredPersons(String text, List<String> clientStatuses, List<String> accountStatuses) {
        List<PersonEntity> registeredPersons = this.personRepository.getPersonsByTextAndStatusLists(text, clientStatuses, accountStatuses);
        return this.listPersonsToPartialDTO(registeredPersons);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialPersonDTO> getPendingPersons() {
        List<PersonEntity> pendingPersons = this.personRepository.getPendingPersons();
        return this.listPersonsToPartialDTO(pendingPersons);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialPersonDTO> getInactivePersons() {
        List<PersonEntity> inactivePersons = this.personRepository.getInactivePersons();
        return this.listPersonsToPartialDTO(inactivePersons);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialPersonDTO> getSuspiciousPersons() {
        List<PersonEntity> suspiciousPersons = this.personRepository.getSuspiciousPersons();
        return this.listPersonsToPartialDTO(suspiciousPersons);
    }

    /**
     * @author: Ignacio Alba
     */
    public FullPersonDTO getFullPersonById(Integer id) {
        PersonEntity personById = this.personRepository.findById(id).orElse(null);
        return personById.toDTO();
    }

    /**
     * @author: Ignacio Alba
     */
    public PartialPersonDTO getPartialPersonById(Integer id) {
        PersonEntity personById = this.personRepository.findById(id).orElse(null);
        return personById.toPartialDTO();
    }

    /**
     * @author: Ignacio Alba
     */
    private List<PartialPersonDTO> listPersonsToPartialDTO(List<PersonEntity> list) {
        List<PartialPersonDTO> personPartialDTOS = new ArrayList<>();

        list.forEach((final PersonEntity p) -> personPartialDTOS.add(p.toPartialDTO()));

        return personPartialDTOS;
    }

    /*
        =================================================================================================
            FIN GESTOR -- Autor: Ignacio Alba
        =================================================================================================
     */

    /*
        =================================================================================================
            CLIENTE -- Autora: Marina Sayago
        =================================================================================================
     */

    /**
     * @author Marina Sayago
     */
    public Client_PersonDTO getPersonByUser(Integer idUser){
        PersonEntity person = this.personRepository.getPersonByPersonUser(idUser);
        return person.toClientPersonDTO();
    }

    /*
        =================================================================================================
            FIN CLIENTE -- Autora: Marina Sayago
        =================================================================================================
     */
}
