package es.uma.proyectotaw.dto.management;

import java.util.List;

/**
 * @author Ignacio Alba - Gestor
 */
public class FullCompanyDTO {
    private int id;
    private String name;
    private String email;
    private String cif;
    private String url;
    private String area;
    private FullClientDTO client;
    private List<FullPersonDTO> relatedPeople;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public FullClientDTO getClient() {
        return client;
    }

    public void setClient(FullClientDTO client) {
        this.client = client;
    }

    public List<FullPersonDTO> getRelatedPeople() {
        return relatedPeople;
    }

    public void setRelatedPeople(List<FullPersonDTO> relatedPeople) {
        this.relatedPeople = relatedPeople;
    }
}
