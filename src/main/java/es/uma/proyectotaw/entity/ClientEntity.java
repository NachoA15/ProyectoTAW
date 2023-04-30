package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.client.Client_ClientDTO;
import es.uma.proyectotaw.dto.management.FullClientDTO;
import es.uma.proyectotaw.dto.management.PartialClientDTO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "taw24", catalog = "")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "identification_number", nullable = false, length = 12)
    private String identificationNumber;
    @Basic
    @Column(name = "phone", nullable = false, length = 12)
    private String phone;
    @OneToOne(mappedBy = "clientByOwner")
    private AccountEntity accountById;
    @ManyToOne
    @JoinColumn(name = "status", referencedColumnName = "id", nullable = false)
    private ClientStatusEntity clientStatusByStatus;
    @ManyToOne
    @JoinColumn(name = "address", referencedColumnName = "id", nullable = false)
    private AddressEntity addressByAddress;
    @OneToOne(mappedBy = "clientByCompanyClient")
    private CompanyEntity companyById;
    @OneToOne(mappedBy = "clientByPersonClient")
    private PersonEntity personById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(identificationNumber, that.identificationNumber) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationNumber, phone);
    }

    public AccountEntity getAccountsById() {
        return accountById;
    }

    public void setAccountsById(AccountEntity accountsById) {
        this.accountById = accountsById;
    }

    public ClientStatusEntity getClientStatusByStatus() {
        return clientStatusByStatus;
    }

    public void setClientStatusByStatus(ClientStatusEntity clientStatusByStatus) {
        this.clientStatusByStatus = clientStatusByStatus;
    }

    public AddressEntity getAddressByAddress() {
        return addressByAddress;
    }

    public void setAddressByAddress(AddressEntity addressByAddress) {
        this.addressByAddress = addressByAddress;
    }

    public AccountEntity getAccountById() {
        return accountById;
    }

    public void setAccountById(AccountEntity accountById) {
        this.accountById = accountById;
    }

    public CompanyEntity getCompanyById() {
        return companyById;
    }

    public void setCompanyById(CompanyEntity companyById) {
        this.companyById = companyById;
    }

    public PersonEntity getPersonById() {
        return personById;
    }

    public void setPersonById(PersonEntity personById) {
        this.personById = personById;
    }

    public PartialClientDTO toPartialDTO() {
        PartialClientDTO dto = new PartialClientDTO();
        dto.setId(this.id);
        dto.setIdentificationNumber(this.identificationNumber);
        dto.setStatus(this.clientStatusByStatus.getStatus());
        dto.setPhone(this.phone);
        dto.setCity(this.addressByAddress.getCity());
        dto.setCountry(this.addressByAddress.getCountry());
        if (this.accountById != null) {
            dto.setAccount(this.accountById.toPartialDTO());
        }
        return dto;
    }

    public FullClientDTO toFullDTO() {
        FullClientDTO dto = new FullClientDTO();
        dto.setId(this.id);
        dto.setIdentificationNumber(this.identificationNumber);
        dto.setStatus(this.clientStatusByStatus.getStatus());
        dto.setPhone(this.phone);
        dto.setAddress(this.addressByAddress.toDTO());
        if (this.accountById != null) {
            dto.setAccount(this.accountById.toFullDTO());
        }
        return dto;
    }

    public Client_ClientDTO toClientDTO(){
        Client_ClientDTO dto = new Client_ClientDTO();

        dto.setId(getId());
        dto.setPhone(getPhone());
        dto.setIdentificationNumber(getIdentificationNumber());
        dto.setAddressByAddress(getAddressByAddress().toDTO());
        dto.setClientStatusByStatus(getClientStatusByStatus().getStatus());

        return dto;
    }
}
