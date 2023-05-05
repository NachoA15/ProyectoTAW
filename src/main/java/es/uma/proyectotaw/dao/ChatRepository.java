package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {

    /*
    =================================================================================================
        ASISTENTE  -- Autor: Iván Delgado
    =================================================================================================
    */
    @Query("select c from ChatEntity c where c.userByAssistant.id = :assistant order by c.personByClient.name, c.chatStateByChatState.id")
    public List<ChatEntity> getChats(@Param("assistant") int assistant);

    @Query("select c from ChatEntity c where c.personByClient.id = :client and c.chatStateByChatState.state = 'open'")
    public ChatEntity getClientChat(@Param("client") int client);

    @Query("select c from ChatEntity c where c.userByAssistant.id = :assistant and c.chatStateByChatState.state = :status order by c.personByClient.name")
    public List<ChatEntity> getChatsByStatus(@Param("assistant") int assistant, @Param("status") String status);

    @Query("select c from ChatEntity c where (upper(c.personByClient.name) like upper(concat('%', :text, '%')) " +
            "or upper(c.personByClient.surname) like upper(concat('%', :text, '%')))" +
            " and c.userByAssistant.id = :assistant")
    public List<ChatEntity> getChatsByText(@Param("assistant") int assistant, @Param("text") String text);

    @Query("select c from ChatEntity c where c.userByAssistant.id = :assistant and" +
            "(upper(c.personByClient.name) like upper(concat('%', :text, '%'))" +
            "or upper(c.personByClient.surname) like upper(concat('%', :text, '%')))" +
            "and c.chatStateByChatState.state = :status")
    public List<ChatEntity> getChatByTextAndStatus(@Param("assistant") int assistant, @Param("text") String text, @Param("status") String status);

    /*
    =================================================================================================
        FIN ASISTENTE  -- Autor: Iván Delgado
    =================================================================================================
     */
}
