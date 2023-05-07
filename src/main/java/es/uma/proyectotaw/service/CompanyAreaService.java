package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.CompanyAreaRepository;
import es.uma.proyectotaw.dto.CompanyAreaDTO;
import es.uma.proyectotaw.entity.CompanyAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio Alba
 */
@Service
public class CompanyAreaService {

    /*
        =================================================================================================
            GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */

    // Esta lista siempre se actualiza cuando se obtienen todas las areas del repositorio
    private List<CompanyAreaEntity> companyAreas;

    @Autowired
    protected CompanyAreaRepository companyAreaRepository;

    /**
     * @author: Ignacio Alba
     */
    public List<CompanyAreaDTO> listCompanyAreas() {
        List<CompanyAreaEntity> companyAreas = this.companyAreaRepository.findAll();
        this.companyAreas = companyAreas;
        return this.listCompanyAreasToDTO(companyAreas);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<String> listAreasOnStrings() {
        return this.listCompanyAreasToStrings(this.companyAreas);
    }

    /**
     * @author: Ignacio Alba
     */
    private List<String> listCompanyAreasToStrings(List<CompanyAreaEntity> list) {
        List<String> strings = new ArrayList<>();

        list.forEach((final CompanyAreaEntity ca) -> strings.add(ca.getArea()));

        return strings;
    }

    /**
     * @author: Ignacio Alba
     */
    private List<CompanyAreaDTO> listCompanyAreasToDTO(List<CompanyAreaEntity> list) {
        List<CompanyAreaDTO> companyAreaDTOS = new ArrayList<>();

        list.forEach((final CompanyAreaEntity ca) -> companyAreaDTOS.add(ca.toDTO()));

        return companyAreaDTOS;
    }

    /*
        =================================================================================================
            FIN GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */
}
