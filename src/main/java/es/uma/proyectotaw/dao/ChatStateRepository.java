package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ChatStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatStateRepository extends JpaRepository<ChatStateEntity, Integer> {

    /**
     * @author: Iván Delgado - Asistente
     */
    @Query("select c from ChatStateEntity c where c.state = :state")
    public ChatStateEntity getChatState(@Param("state") String state);
}
