package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.ClientStatusRepository;
import es.uma.proyectotaw.dto.ClientStatusDTO;
import es.uma.proyectotaw.entity.ClientStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio Alba
 */
@Service
public class ClientStatusService {

    // Esta lista siempre se actualiza cuando se obtienen todos los status del repositorio
    private List<ClientStatusEntity> clientStatuses;

    @Autowired
    protected ClientStatusRepository clientStatusRepository;

    public List<ClientStatusDTO> listClientStatuses() {
        List<ClientStatusEntity> clientStatuses = this.clientStatusRepository.findAll();
        this.clientStatuses = clientStatuses;
        return this.listClientStatusesToDTO(clientStatuses);
    }

    public List<String> listStatusesOnStrings() {
        return this.listClientStatusesToStrings(this.clientStatuses);
    }

    private List<String> listClientStatusesToStrings(List<ClientStatusEntity> list) {
        List<String> strings = new ArrayList<>();

        list.forEach((final ClientStatusEntity c) -> strings.add(c.getStatus()));

        return strings;
    }

    private List<ClientStatusDTO> listClientStatusesToDTO(List<ClientStatusEntity> list) {
        List<ClientStatusDTO> clientStatusDTOS = new ArrayList<>();

        list.forEach((final ClientStatusEntity cs) -> clientStatusDTOS.add(cs.toDTO()));

        return clientStatusDTOS;
    }
}
