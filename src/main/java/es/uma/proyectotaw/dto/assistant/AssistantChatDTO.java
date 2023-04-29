package es.uma.proyectotaw.dto.assistant;

import java.util.List;

/**
 * @author: Iván Delgado
 */
public class AssistantChatDTO {

    private int id;

    private int Assistant;

    private AssistantPersonDTO User;

    private List<AssistantMessageDTO> messageList;

    private String state;

    public AssistantChatDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAssistant() {
        return Assistant;
    }

    public void setAssistant(int assistant) {
        Assistant = assistant;
    }

    public AssistantPersonDTO getUser() {
        return User;
    }

    public void setUser(AssistantPersonDTO user) {
        User = user;
    }

    public List<AssistantMessageDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<AssistantMessageDTO> messageList) {
        this.messageList = messageList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
