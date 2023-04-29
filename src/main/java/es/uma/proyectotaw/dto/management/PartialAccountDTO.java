package es.uma.proyectotaw.dto.management;

/**
 * @author Ignacio Alba
 */
public class PartialAccountDTO {
    private String iban;
    private String status;

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
