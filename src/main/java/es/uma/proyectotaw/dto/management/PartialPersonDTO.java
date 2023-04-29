package es.uma.proyectotaw.dto.management;

/**
 * @author: Nacho Alba
 */
public class PartialPersonDTO {
    private int id;
    private String name;
    private String surname;
    private String email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PartialClientDTO getClient() {
        return client;
    }

    public void setClient(PartialClientDTO client) {
        this.client = client;
    }
}
