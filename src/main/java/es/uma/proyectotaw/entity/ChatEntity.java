package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.assistant.AssistantChatDTO;
import es.uma.proyectotaw.dto.assistant.AssistantMessageDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Al final del archivo están los métodos necesarios para convertir un objeto de esta clase a
 * su DTO correspondiente.
 *
 * Cada método está etiquetado con el nombre del autor/a.
 */

@Entity
@Table(name = "chat", schema = "taw24", catalog = "")
public class ChatEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id", nullable = false)
    private PersonEntity personByClient;
    @ManyToOne
    @JoinColumn(name = "assistant", referencedColumnName = "id", nullable = false)
    private UserEntity userByAssistant;
    @ManyToOne
    @JoinColumn(name = "chat_state", referencedColumnName = "id", nullable = false)
    private ChatStateEntity chatStateByChatState;
    @OneToMany(mappedBy = "chatByChat")
    private Collection<MessageEntity> messagesById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatEntity that = (ChatEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public PersonEntity getPersonByClient() {
        return personByClient;
    }

    public void setPersonByClient(PersonEntity personByClient) {
        this.personByClient = personByClient;
    }

    public UserEntity getUserByAssistant() {
        return userByAssistant;
    }

    public void setUserByAssistant(UserEntity userByAssistant) {
        this.userByAssistant = userByAssistant;
    }

    public ChatStateEntity getChatStateByChatState() {
        return chatStateByChatState;
    }

    public void setChatStateByChatState(ChatStateEntity chatStateByChatState) {
        this.chatStateByChatState = chatStateByChatState;
    }

    public Collection<MessageEntity> getMessagesById() {
        return messagesById;
    }

    public void setMessagesById(Collection<MessageEntity> messagesById) {
        this.messagesById = messagesById;
    }

    /**
     * @author: Iván Delgado
     */
    public AssistantChatDTO toDTO(){
        AssistantChatDTO dto = new AssistantChatDTO();
        dto.setId(getId());
        dto.setAssistant(getUserByAssistant().getId());
        dto.setUser(getPersonByClient().toAssistantPersonDTO());
        List<AssistantMessageDTO> messages = new ArrayList<>();
        if(getMessagesById() != null){
            for(MessageEntity m : getMessagesById()){
                messages.add(m.toDTO());
            }
        }
        dto.setMessageList(messages);
        dto.setState(getChatStateByChatState().getState());
        return dto;
    }
}
