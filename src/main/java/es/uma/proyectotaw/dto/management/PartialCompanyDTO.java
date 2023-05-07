package es.uma.proyectotaw.dto.management;

/**
 * @author Ignacio Alba - Gestor
 */
public class PartialCompanyDTO {
    private int id;
    private String name;
    private String cif;
    private String email;
    private String area;
    private PartialClientDTO client;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public PartialClientDTO getClient() {
        return client;
    }

    public void setClient(PartialClientDTO client) {
        this.client = client;
    }
}
