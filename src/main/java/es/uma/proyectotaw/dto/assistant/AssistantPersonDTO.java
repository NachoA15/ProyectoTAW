package es.uma.proyectotaw.dto.assistant;

/**
 * @author: Iván Delgado
 */
public class AssistantPersonDTO {

    private int id;

    private String name;

    private String company;

    public AssistantPersonDTO() {
    }

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
