package es.uma.proyectotaw.entity;

import es.uma.proyectotaw.dto.assistant.AssistantMessageDTO;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "message", schema = "taw24", catalog = "")
public class MessageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "text", nullable = false, length = 300)
    private String text;
    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "chat", referencedColumnName = "id", nullable = false)
    private ChatEntity chatByChat;
    @ManyToOne
    @JoinColumn(name = "writer", referencedColumnName = "id", nullable = false)
    private UserEntity userByWriter;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity that = (MessageEntity) o;
        return id == that.id && Objects.equals(text, that.text) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date);
    }

    public ChatEntity getChatByChat() {
        return chatByChat;
    }

    public void setChatByChat(ChatEntity chatByChat) {
        this.chatByChat = chatByChat;
    }

    public UserEntity getUserByWriter() {
        return userByWriter;
    }

    public void setUserByWriter(UserEntity userByWriter) {
        this.userByWriter = userByWriter;
    }

    public AssistantMessageDTO toDTO(){
        AssistantMessageDTO dto = new AssistantMessageDTO();
        dto.setId(getId());
        dto.setText(getText());
        dto.setChat(getChatByChat().getId());
        dto.setDate(getDate());
        if(getUserByWriter().getRoleUserByRole().getRole().equals("assistant")){
            dto.setWriter("Assistant");
        }else if(!getUserByWriter().getRoleUserByRole().getRole().equals("management")){
            dto.setWriter("Client");
        }

        return dto;
    }
}
