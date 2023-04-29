package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.PersonRepository;
import es.uma.proyectotaw.dto.management.FullPersonDTO;
import es.uma.proyectotaw.dto.management.PartialPersonDTO;
import es.uma.proyectotaw.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio Alba
 */
@Service
public class PersonService {

    @Autowired
    protected PersonRepository personRepository;

    public List<PartialPersonDTO> getRegisteredPersons() {
        List<PersonEntity> registeredPersons = this.personRepository.getRegisteredPersons();
        return this.listPersonsToPartialDTO(registeredPersons);
    }

    public List<PartialPersonDTO> getRegisteredPersons(String text, List<String> clientStatuses, List<String> accountStatuses) {
        List<PersonEntity> registeredPersons = this.personRepository.getPersonsByTextAndStatusLists(text, clientStatuses, accountStatuses);
        return this.listPersonsToPartialDTO(registeredPersons);
    }

    public List<PartialPersonDTO> getPendingPersons() {
        List<PersonEntity> pendingPersons = this.personRepository.getPendingPersons();
        return this.listPersonsToPartialDTO(pendingPersons);
    }

    public FullPersonDTO getPersonById(Integer id) {
        PersonEntity personById = this.personRepository.findById(id).orElse(null);
        return personById.toDTO();
    }

    private List<PartialPersonDTO> listPersonsToPartialDTO(List<PersonEntity> list) {
        List<PartialPersonDTO> personPartialDTOS = new ArrayList<>();

        list.forEach((final PersonEntity p) -> personPartialDTOS.add(p.toPartialDTO()));

        return personPartialDTOS;
    }
}
