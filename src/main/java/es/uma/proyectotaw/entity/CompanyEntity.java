package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.management.FullCompanyDTO;
import es.uma.proyectotaw.dto.management.FullPersonDTO;
import es.uma.proyectotaw.dto.management.PartialCompanyDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company", schema = "taw24", catalog = "")
public class CompanyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "cif", nullable = false, length = 45)
    private String cif;
    @Basic
    @Column(name = "url", nullable = true, length = 200)
    private String url;
    @ManyToOne
    @JoinColumn(name = "area", referencedColumnName = "id", nullable = false)
    private CompanyAreaEntity area;
    @ManyToOne
    @JoinColumn(name = "company_user", referencedColumnName = "id", nullable = false)
    private UserEntity userByCompanyUser;
    @OneToOne
    @JoinColumn(name = "company_client", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByCompanyClient;
    @OneToMany(mappedBy = "companyByRelatedCompany")
    private Collection<PersonEntity> peopleById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyEntity that = (CompanyEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(cif, that.cif) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cif, url);
    }

    public CompanyAreaEntity getCompanyAreaByArea() {
        return area;
    }

    public void setCompanyAreaByArea(CompanyAreaEntity companyAreaByArea) {
        this.area = companyAreaByArea;
    }

    public UserEntity getUserByCompanyUser() {
        return userByCompanyUser;
    }

    public void setUserByCompanyUser(UserEntity userByCompanyUser) {
        this.userByCompanyUser = userByCompanyUser;
    }

    public ClientEntity getClientByCompanyClient() {
        return clientByCompanyClient;
    }

    public void setClientByCompanyClient(ClientEntity clientByCompanyClient) {
        this.clientByCompanyClient = clientByCompanyClient;
    }

    public Collection<PersonEntity> getPeopleById() {
        return peopleById;
    }

    public void setPeopleById(Collection<PersonEntity> peopleById) {
        this.peopleById = peopleById;
    }

    public PartialCompanyDTO toPartialDTO() {
        PartialCompanyDTO dto = new PartialCompanyDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setCif(this.cif);
        dto.setArea(this.area.getArea());
        dto.setEmail(this.userByCompanyUser.getEmail());
        dto.setClient(this.clientByCompanyClient.toPartialDTO());
        return dto;
    }

    private List<FullPersonDTO> relatedPersonListToDTO(List<PersonEntity> list) {
        List<FullPersonDTO> relatedPersonsDTO = new ArrayList<>();
        list.forEach((final PersonEntity p) -> relatedPersonsDTO.add(p.toDTO()));
        return relatedPersonsDTO;
    }

    public FullCompanyDTO toDTO() {
        FullCompanyDTO dto = new FullCompanyDTO();
        dto.setClient(this.clientByCompanyClient.toDTO());
        dto.setName(this.name);
        dto.setId(this.id);
        dto.setEmail(this.userByCompanyUser.getEmail());
        dto.setArea(this.area.getArea());
        dto.setCif(this.cif);
        dto.setUrl(this.url);
        dto.setRelatedPeople(this.relatedPersonListToDTO((List<PersonEntity>) this.getPeopleById()));
        return dto;
    }
}
