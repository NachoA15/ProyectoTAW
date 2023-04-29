package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.management.FullPersonDTO;
import es.uma.proyectotaw.dto.management.PartialPersonDTO;
import es.uma.proyectotaw.dto.assistant.AssistantPersonDTO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "person", schema = "taw24", catalog = "")
public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "surname", nullable = false, length = 45)
    private String surname;
    @Basic
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @OneToMany(mappedBy = "personByClient")
    private Collection<ChatEntity> chatsById;
    @ManyToOne
    @JoinColumn(name = "related_company", referencedColumnName = "id")
    private CompanyEntity companyByRelatedCompany;
    @OneToOne
    @JoinColumn(name = "person_client", referencedColumnName = "id")
    private ClientEntity clientByPersonClient;
    @ManyToOne
    @JoinColumn(name = "person_user", referencedColumnName = "id", nullable = false)
    private UserEntity userByPersonUser;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate);
    }

    public Collection<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }

    public CompanyEntity getCompanyByRelatedCompany() {
        return companyByRelatedCompany;
    }

    public void setCompanyByRelatedCompany(CompanyEntity companyByRelatedCompany) {
        this.companyByRelatedCompany = companyByRelatedCompany;
    }

    public ClientEntity getClientByPersonClient() {
        return clientByPersonClient;
    }

    public void setClientByPersonClient(ClientEntity clientByPersonClient) {
        this.clientByPersonClient = clientByPersonClient;
    }

    public UserEntity getUserByPersonUser() {
        return userByPersonUser;
    }

    public void setUserByPersonUser(UserEntity userByPersonUser) {
        this.userByPersonUser = userByPersonUser;
    }

    public PartialPersonDTO toPartialDTO() {
        PartialPersonDTO dto = new PartialPersonDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setSurname(this.surname);
        dto.setEmail(this.userByPersonUser.getEmail());
        dto.setClient(this.clientByPersonClient.toPartialDTO());
        return dto;
    }

    public FullPersonDTO toDTO() {
        FullPersonDTO dto = new FullPersonDTO();
        if (this.clientByPersonClient != null) {
            dto.setClient(this.clientByPersonClient.toDTO());
        }
        dto.setName(this.name);
        dto.setId(this.id);
        dto.setEmail(this.userByPersonUser.getEmail());
        dto.setSurname(this.surname);
        dto.setBirthDate(this.birthDate);
        return dto;
    }

    public AssistantPersonDTO toAssistantPersonDTO(){
        AssistantPersonDTO dto = new AssistantPersonDTO();
        dto.setId(getId());
        if(getCompanyByRelatedCompany() != null){
            dto.setCompany(getCompanyByRelatedCompany().getName());
        }else{
            dto.setCompany("");
        }
        dto.setName(getName() + " " + getSurname());
        return dto;
    }
}
