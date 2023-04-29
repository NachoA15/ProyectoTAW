package es.uma.proyectotaw.dto.assistant;

import java.sql.Timestamp;

/**
 * @author: Iván Delgado
 */
public class AssistantMessageDTO {

    private int id;

    private String text;

    private int chat;

    private Timestamp date;

    private String writer;

    public AssistantMessageDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getChat() {
        return chat;
    }

    public void setChat(int chat) {
        this.chat = chat;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
