package es.uma.proyectotaw.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "chat_state", schema = "taw24", catalog = "")
public class ChatStateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "state", nullable = false, length = 45)
    private String state;
    @OneToMany(mappedBy = "chatStateByChatState")
    private Collection<ChatEntity> chatsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatStateEntity that = (ChatStateEntity) o;
        return id == that.id && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, state);
    }

    public Collection<ChatEntity> getChatsById() {
        return chatsById;
    }

    public void setChatsById(Collection<ChatEntity> chatsById) {
        this.chatsById = chatsById;
    }
}
