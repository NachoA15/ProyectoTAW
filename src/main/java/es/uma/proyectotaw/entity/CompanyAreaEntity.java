package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.CompanyAreaDTO;

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
@Table(name = "company_area", schema = "taw24", catalog = "")
public class CompanyAreaEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "area", nullable = false, length = 45)
    private String area;
    @OneToMany(mappedBy = "area")
    private Collection<CompanyEntity> companiesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyAreaEntity that = (CompanyAreaEntity) o;
        return id == that.id && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area);
    }

    public Collection<CompanyEntity> getCompaniesById() {
        return companiesById;
    }

    public void setCompaniesById(Collection<CompanyEntity> companiesById) {
        this.companiesById = companiesById;
    }

    /**
     * @author: Ignacio alba
     */
    public CompanyAreaDTO toDTO() {
        CompanyAreaDTO dto = new CompanyAreaDTO();
        dto.setId(this.id);
        dto.setArea(this.area);
        return dto;
    }
}
