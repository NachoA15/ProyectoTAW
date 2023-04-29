package es.uma.proyectotaw.controller;


import es.uma.proyectotaw.dto.UserDTO;
import es.uma.proyectotaw.service.UserService;
import es.uma.proyectotaw.ui.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;

@Controller
public class LoginController {

    @Autowired
    protected UserService userService;

    @GetMapping("/")
    public String doShowLogin() {
        return "login";
    }

    @PostMapping("/")
    public String doAuthenticate(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 Model model, HttpSession session) {
        String urlTo = "redirect:/";
        UserDTO userDTO = this.userService.authenticate(email, password);

        if (userDTO == null) {
            model.addAttribute("error", "Incorrect credentials. Please try again.");
            urlTo = "login";
        } else {
            if (userDTO.getRole().equals("management")) {
                session.setAttribute("management", userDTO);
                urlTo = "redirect:/clients";
            }else if(userDTO.getRole().equals("client")){
                session.setAttribute("client", userDTO);
                urlTo = "redirect:/client?id=" + userDTO.getId();
            }else if(userDTO.getRole().equals("assistant")){
                session.setAttribute("assistant", userDTO);
                urlTo ="redirect:/assistant";
            }
        }

        return urlTo;
    }

    /**
     * @author: Marina Sayago
     */
    @GetMapping("/signUp")
    public String doShowSignUp(Model model) {
        SignUp signUp = new SignUp();
        model.addAttribute("signUp", signUp);

        return "signUp";
    }

    /**
     * @author: Marina Sayago
     */
    @PostMapping("/signUpSave")
    public String doSaveSignUp(@ModelAttribute("signUp") SignUp signUp, Model model) throws ParseException {
        String urlTo = "login";

        if(!signUp.getPassword().equals(signUp.getConfirmPassword())){
            model.addAttribute("error", "Incorrect password. Please try again.");
            urlTo = "signUp";
        }else if(signUp.getSurname().equals("") || signUp.getPassword().equals("") ||
                signUp.getBirthDay().equals("")|| signUp.getEmail().equals("") ||
                signUp.getName().equals("") || signUp.getConfirmPassword().equals("")){
            model.addAttribute("error", "Complete all the fields. Please try again.");
            urlTo = "signUp";
        }else{
            this.userService.save(signUp);
        }

        return urlTo;
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
