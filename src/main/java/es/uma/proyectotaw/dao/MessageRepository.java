package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Iván Delgado
 */
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    /**
     * @author: Iván Delgado - Asistente
     */
    @Query("select m from MessageEntity m where m.chatByChat.id = :chat")
    public List<MessageEntity> getMessages(@Param("chat") int chat);
}
