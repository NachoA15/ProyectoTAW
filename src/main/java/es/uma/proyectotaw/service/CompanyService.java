package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.CompanyRepository;
import es.uma.proyectotaw.dto.management.FullCompanyDTO;
import es.uma.proyectotaw.dto.management.PartialCompanyDTO;
import es.uma.proyectotaw.dto.management.PartialPersonDTO;
import es.uma.proyectotaw.entity.CompanyEntity;
import es.uma.proyectotaw.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio Alba
 */
@Service
public class CompanyService {

    @Autowired
    protected CompanyRepository companyRepository;

    /*
        =================================================================================================
            GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */

    /**
     * @author: Ignacio Alba
     */
    public List<PartialCompanyDTO> getRegisteredCompanies() {
        List<CompanyEntity> registeredCompanies = this.companyRepository.getRegisteredCompanies();
        return this.listCompaniesToPartialDTO(registeredCompanies);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialCompanyDTO> getRegisteredCompanies(String text, List<String> clientStatuses, List<String> accountStatuses, List<String> areas) {
        List<CompanyEntity> registeredCompanies = this.companyRepository.getCompaniesByTextAndStatusLists(text, clientStatuses, accountStatuses, areas);
        return this.listCompaniesToPartialDTO(registeredCompanies);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialCompanyDTO> getPendingCompanies() {
        List<CompanyEntity> pendingCompanies = this.companyRepository.getPendingCompanies();
        return this.listCompaniesToPartialDTO(pendingCompanies);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialCompanyDTO> getInactiveCompanies() {
        List<CompanyEntity> inactiveCompanies = this.companyRepository.getInactiveCompanies();
        return this.listCompaniesToPartialDTO(inactiveCompanies);
    }

    /**
     * @author: Ignacio Alba
     */
    public List<PartialCompanyDTO> getSuspiciousCompanies() {
        List<CompanyEntity> suspiciosCompanies = this.companyRepository.getSuspiciousCompanies();
        return this.listCompaniesToPartialDTO(suspiciosCompanies);
    }

    /**
     * @author: Ignacio Alba
     */
    public FullCompanyDTO getFullCompanyById(Integer id) {
        CompanyEntity company = this.companyRepository.findById(id).orElse(null);
        return company.toDTO();
    }

    /**
     * @author: Ignacio Alba
     */
    public PartialCompanyDTO getPartialCompanyById(Integer id) {
        CompanyEntity company = this.companyRepository.findById(id).orElse(null);
        return company.toPartialDTO();
    }

    /**
     * @author: Ignacio Alba
     */
    private List<PartialCompanyDTO> listCompaniesToPartialDTO(List<CompanyEntity> list) {
        List<PartialCompanyDTO> companyPartialDTOS = new ArrayList<>();

        list.forEach((final CompanyEntity c) -> companyPartialDTOS.add(c.toPartialDTO()));

        return companyPartialDTOS;
    }

    /*
        =================================================================================================
            FIN GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */
}
