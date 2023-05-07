package es.uma.proyectotaw.dto.management;

/**
 * @author Ignacio Alba - Gestor
 */
public class PartialAccountDTO {
    private Integer id;
    private String iban;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
