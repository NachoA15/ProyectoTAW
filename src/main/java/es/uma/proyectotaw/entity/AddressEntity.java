package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.AddressDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Al final del archivo están los métodos necesarios para convertir un objeto de esta clase a
 * su DTO correspondiente.
 *
 * Cada método está etiquetado con el nombre del autor/a.
 */
@Entity
@Table(name = "address", schema = "taw24", catalog = "")
public class AddressEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "street", nullable = false, length = 45)
    private String street;
    @Basic
    @Column(name = "number", nullable = false, length = 3)
    private String number;
    @Basic
    @Column(name = "city", nullable = false, length = 45)
    private String city;
    @Basic
    @Column(name = "region", nullable = true, length = 45)
    private String region;
    @Basic
    @Column(name = "zip_code", nullable = false, length = 45)
    private String zipCode;
    @Basic
    @Column(name = "country", nullable = false, length = 45)
    private String country;
    @OneToMany(mappedBy = "addressByAddress")
    private Collection<ClientEntity> clientsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEntity that = (AddressEntity) o;
        return id == that.id && Objects.equals(street, that.street) && Objects.equals(number, that.number) && Objects.equals(city, that.city) && Objects.equals(region, that.region) && Objects.equals(zipCode, that.zipCode) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, city, region, zipCode, country);
    }

    public Collection<ClientEntity> getClientsById() {
        return clientsById;
    }

    public void setClientsById(Collection<ClientEntity> clientsById) {
        this.clientsById = clientsById;
    }

    /**
     * @author: Ignacio Alba
     */
    public AddressDTO toDTO() {
        AddressDTO dto = new AddressDTO();
        dto.setId(this.id);
        dto.setStreet(this.street);
        dto.setNumber(this.number);
        dto.setCity(this.city);
        dto.setRegion(this.region);
        dto.setCountry(this.country);
        dto.setZipCode(this.zipCode);
        return dto;
    }
}
