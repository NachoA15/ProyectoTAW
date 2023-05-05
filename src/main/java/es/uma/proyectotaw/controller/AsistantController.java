package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.assistant.AssistantChatDTO;
import es.uma.proyectotaw.dto.assistant.AssistantMessageDTO;
import es.uma.proyectotaw.dto.UserDTO;
import es.uma.proyectotaw.entity.UserEntity;
import es.uma.proyectotaw.service.ChatService;
import es.uma.proyectotaw.ui.FilterChats;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Iván Delgado
 */

@Controller
public class AsistantController {

    @Autowired
    protected ChatService chatService;

    /*
    =================================================================================================
        ASISTENTE  -- Autor: Iván Delgado
    =================================================================================================
    */
    @GetMapping("/assistant")
    public String doList(Model model, HttpSession session){
        return doProcesarFiltrado(null, model, session);
    }

    @GetMapping("/assistant/messages/{id}")
    public String doListMessages(@PathVariable("id") Integer idChat, Model model, HttpSession session){
        List<AssistantMessageDTO> messages = this.chatService.getMessages(idChat);
        AssistantChatDTO assistantChatDTO = this.chatService.getChat(idChat);
        model.addAttribute("messages", messages);
        model.addAttribute("chat", assistantChatDTO);
        UserDTO user = (UserDTO) session.getAttribute("assistant");
        model.addAttribute("user", user);
        return "assistant/messages";
    }

    @PostMapping("/assistant/chats/filtrar")
    public String doFiltrar(@ModelAttribute("filter") FilterChats filter, Model model, HttpSession session){

        return this.doProcesarFiltrado(filter, model, session);
    }

    protected String doProcesarFiltrado(FilterChats filter,
                                        Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("assistant");
        String urlTo = "assistant/chats";
        if(user == null){
            urlTo = "redirect:/";
            return urlTo;
        }else{
            List<AssistantChatDTO> chats;
            if(filter == null || (filter.getText().isEmpty() && filter.getStatus().isEmpty())){
                chats = this.chatService.getChats(user.getId());
                filter = new FilterChats();
            }else if(!filter.getText().isEmpty() && filter.getStatus().isEmpty()){
                chats = this.chatService.getChatsByText(user.getId(), filter.getText());
            }else if(filter.getText().isEmpty() && !filter.getStatus().isEmpty()){
                chats = this.chatService.getChatsByStatus(user.getId(), filter.getStatus());
            }else{
                chats = this.chatService.getChatsByTextAndStatus(user.getId(), filter.getText(), filter.getStatus());
            }

            model.addAttribute("chats", chats);
            model.addAttribute("filter", filter);
        }
        return urlTo;
    }

    @GetMapping("/returnChat")
    public String returnFromChat(HttpSession session){
        String urlTo = "login";
        UserDTO user = (UserDTO) session.getAttribute("assistant");
        if(user != null){
            urlTo = "redirect:/assistant";
        }else{
            user = (UserDTO) session.getAttribute("client");

            if(user != null){
                urlTo = "redirect:/client?id=" + user.getId();
            }else{
                user = ((UserEntity) session.getAttribute("company_person")).toDTO();
                if(user != null){
                    urlTo = "redirect:/company/company_person?id=" + user.getId();
                }
            }
        }

        return urlTo;
    }

    @GetMapping("/assistant/close/{id}")
    public String closeChat(@PathVariable("id") Integer idChat, HttpSession session){
        this.chatService.closeChat(idChat);
        return returnFromChat(session);
    }

    @GetMapping("/assistant/newMessage/{id}")
    public String newMessage(@PathVariable("id") Integer idChat, Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("assistant");
        //The user isn't an assistant
        if(user == null){
            user = (UserDTO) session.getAttribute("client");
        //The user isn't a particular client
        }
        if(user == null){
            //The company part uses entities instead of dtos so this casting is necessary
            user = ((UserEntity) session.getAttribute("company_person")).toDTO();
        }
        AssistantMessageDTO newMessage = this.chatService.newMessage(idChat, user);
        model.addAttribute("newMessage", newMessage);
        return "/assistant/newMessage";
    }

    @PostMapping("/assistant/newMessage")
    public String saveNewMessage(@ModelAttribute("newMessage") AssistantMessageDTO assistantMessageDTO, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("assistant");
        if(user == null){
            user = (UserDTO) session.getAttribute("client");
        }
        if(user == null){
            //The company part uses entities instead of dtos so this casting is necessary
            user = ((UserEntity) session.getAttribute("company_person")).toDTO();
        }
        this.chatService.saveNewMessage(assistantMessageDTO, user);
        String urlTo = "redirect:/assistant/messages/" + assistantMessageDTO.getChat() + "";
        return urlTo;
    }

    private String createNewChat(Integer idPerson, Model model, HttpSession session){
        AssistantChatDTO chat = this.chatService.createNewChat(idPerson);
        model.addAttribute("chat", chat);
        List<AssistantMessageDTO> messages = this.chatService.getMessages(chat.getId());
        model.addAttribute("messages", messages);
        return "assistant/messages";
    }

    @GetMapping("/client/chat/{id}")
    public String getClientChat(@PathVariable("id") Integer idPerson, Model model, HttpSession session){
        AssistantChatDTO chat = this.chatService.getClientChat(idPerson);
        if(chat == null || chat.getState().equals("closed")){
            return createNewChat(idPerson, model, session);
        }else{
            model.addAttribute("chat", chat);
            List<AssistantMessageDTO> messages = this.chatService.getMessages(chat.getId());
            model.addAttribute("messages", messages);
            UserDTO user = (UserDTO) session.getAttribute("assistant");
            model.addAttribute("user", user);
            return "assistant/messages";
        }

    }

    /*
    =================================================================================================
        FIN ASISTENTE  -- Autor: Iván Delgado
    =================================================================================================
     */
}
