package es.uma.proyectotaw.dto.management;

import java.sql.Date;

/**
 * @author: Ignacio Alba - Gestor
 */
public class FullPersonDTO {
    private int id;
    private String name;
    private String surname;
    private Date birthDate;
    private String email;
    private FullClientDTO client;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FullClientDTO getClient() {
        return client;
    }

    public void setClient(FullClientDTO client) {
        this.client = client;
    }
}
