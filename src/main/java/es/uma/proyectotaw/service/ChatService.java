package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.assistant.AssistantChatDTO;
import es.uma.proyectotaw.dto.assistant.AssistantMessageDTO;
import es.uma.proyectotaw.dto.UserDTO;
import es.uma.proyectotaw.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Iván Delgado
 */
@Service
public class ChatService {

    @Autowired
    protected ChatRepository chatRepository;

    @Autowired
    protected MessageRepository messageRepository;

    @Autowired
    protected ChatStateRepository chatStateRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected PersonRepository personRepository;

    /*
    =================================================================================================
        ASISTENTE  -- Autor: Iván Delgado
    =================================================================================================
    */
    public List<AssistantMessageDTO> getMessages(Integer idChat){
        List<MessageEntity> messages = this.messageRepository.getMessages(idChat);

        return this.listEntitiesToDTO(messages);
    }

    public AssistantChatDTO getChat(Integer idChat){
        return this.chatRepository.findById(idChat).orElse(null).toDTO();
    }

    protected List<AssistantMessageDTO> listEntitiesToDTO(List<MessageEntity> messages){
        ArrayList<AssistantMessageDTO> dtos = new ArrayList<AssistantMessageDTO>();

        messages.forEach((final MessageEntity message) -> dtos.add(message.toDTO()));
        return dtos;
    }

    public void closeChat(Integer id){
        ChatEntity chat = this.chatRepository.findById(id).orElse(null);
        ChatStateEntity state = this.chatStateRepository.getChatState("closed");
        chat.setChatStateByChatState(state);
        this.chatRepository.save(chat);
    }

    public AssistantMessageDTO newMessage(Integer id, UserDTO assistantUserDto){
        ChatEntity chat = this.chatRepository.findById(id).orElse(null);
        UserEntity user = this.userRepository.findById(assistantUserDto.getId()).orElse(null);
        MessageEntity message = new MessageEntity();
        message.setChatByChat(chat);
        message.setUserByWriter(user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message.setDate(timestamp);
        return message.toDTO();
    }

    public void saveNewMessage(AssistantMessageDTO message, UserDTO assistantUserDto){
        MessageEntity newMessage = new MessageEntity();

        ChatEntity chat = this.chatRepository.findById(message.getChat()).orElse(null);
        newMessage.setChatByChat(chat);
        newMessage.setDate(message.getDate());
        newMessage.setText(message.getText());

        UserEntity user = (UserEntity) this.userRepository.findById(assistantUserDto.getId()).orElse(null);
        newMessage.setUserByWriter(user);
        this.messageRepository.save(newMessage);
    }

    public List<AssistantChatDTO> getChats(int assistant){

        List<ChatEntity> chats = this.chatRepository.getChats(assistant);

        return listChatsToDTO(chats);
    }

    protected List<AssistantChatDTO> listChatsToDTO(List<ChatEntity> chats){
        ArrayList dtos = new ArrayList<AssistantChatDTO>();
        chats.forEach((final ChatEntity chat) -> dtos.add(chat.toDTO()));
        return dtos;
    }

    public List<AssistantChatDTO> getChatsByText(int assistant, String text){
        List<ChatEntity> chats = this.chatRepository.getChatsByText(assistant, text);
        return listChatsToDTO(chats);
    }

    public List<AssistantChatDTO> getChatsByStatus(int assistant, String status){
        List<ChatEntity> chats = this.chatRepository.getChatsByStatus(assistant, status);
        return listChatsToDTO(chats);
    }

    public List<AssistantChatDTO> getChatsByTextAndStatus(int assistant, String text, String status){
        List<ChatEntity> chats = this.chatRepository.getChatByTextAndStatus(assistant, text, status);
        return listChatsToDTO(chats);
    }

    public AssistantChatDTO createNewChat(Integer idPerson){
        PersonEntity person = this.personRepository.findById(idPerson).orElse(null);
        List<UserEntity> assistants = this.userRepository.getAssistants();
        ChatEntity chat = new ChatEntity();

        chat.setPersonByClient(person);
        chat.setUserByAssistant(assistants.get(assistants.size() - 1));

        ChatStateEntity state = this.chatStateRepository.getChatState("open");
        chat.setChatStateByChatState(state);

        this.chatRepository.save(chat);

        return chat.toDTO();
    }

    public AssistantChatDTO getClientChat(Integer idPerson){
        PersonEntity person = this.personRepository.findById(idPerson).orElse(null);
        ChatEntity chat = this.chatRepository.getClientChat(person.getId());
        if(chat == null){
            return null;
        }else{
            return chat.toDTO();
        }

    }

    /*
    =================================================================================================
        FIN ASISTENTE  -- Autor: Iván Delgado
    =================================================================================================
     */
}
