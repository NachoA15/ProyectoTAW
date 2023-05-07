package es.uma.proyectotaw.dto.client;

import es.uma.proyectotaw.dto.UserDTO;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author: Marina Sayago - Cliente
 */
public class Client_PersonDTO implements Serializable {
    private int id;
    private String name;
    private String surname;
    private Date birthDate;
    private UserDTO userByPersonUser;
    private Client_ClientDTO clientByPersonClient;

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

    public UserDTO getUserByPersonUser() {
        return userByPersonUser;
    }

    public void setUserByPersonUser(UserDTO userByPersonUser) {
        this.userByPersonUser = userByPersonUser;
    }

    public Client_ClientDTO getClientByPersonClient() {
        return clientByPersonClient;
    }

    public void setClientByPersonClient(Client_ClientDTO clientByPersonClient) {
        this.clientByPersonClient = clientByPersonClient;
    }

    public Client_PersonDTO() {
    }
}
