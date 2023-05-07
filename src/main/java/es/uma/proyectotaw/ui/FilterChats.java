package es.uma.proyectotaw.ui;

/**
 * @author: Iván Delgado - Asistente
 */
public class FilterChats {

    private String text;

    private String status;

    public FilterChats() {
        text = "";
        status = "";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
