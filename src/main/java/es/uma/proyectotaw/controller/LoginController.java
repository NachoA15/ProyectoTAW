package es.uma.proyectotaw.controller;


import es.uma.proyectotaw.dao.CompanyRepository;
import es.uma.proyectotaw.dao.PersonRepository;
import es.uma.proyectotaw.dao.UserRepository;
import es.uma.proyectotaw.dto.UserDTO;
import es.uma.proyectotaw.dto.client.Client_ClientDTO;
import es.uma.proyectotaw.dto.client.Client_PersonDTO;
import es.uma.proyectotaw.entity.CompanyEntity;
import es.uma.proyectotaw.entity.PersonEntity;
import es.uma.proyectotaw.entity.UserEntity;
import es.uma.proyectotaw.service.ClientService;
import es.uma.proyectotaw.service.PersonService;
import es.uma.proyectotaw.service.UserService;
import es.uma.proyectotaw.ui.SignUp;
import org.apache.catalina.User;
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
    @Autowired
    protected PersonService personService;
    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/")
    public String doShowLogin() {
        return "login";
    }

    /**
     * @author: Ignacio Alba
     */
    @PostMapping("/")
    public String doAuthenticate(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 Model model, HttpSession session) {
        String urlTo;
        UserDTO user = this.userService.authenticate(email,password);

        if (user == null) {
            model.addAttribute("error", "Incorrect credentials. Please try again.");
            urlTo = "login";
        } else if (user.getRole().equals("management")) {
            session.setAttribute("management", user);
            urlTo = "redirect:/clients";
        } else if (user.getRole().equals("assistant")) {
            session.setAttribute("assistant", user);
            urlTo = "redirect:/assistant";
        } else if (user.getRole().equals("client")) {
            if (user.getClientStatus().equals("Pending")) {
                model.addAttribute("error", "Your registration must first be approved by a manager.");
                urlTo = "login";
            } else {
                session.setAttribute("client", user);
                urlTo = "redirect:/client?id=" + user.getId();
            }
        } else if (user.getRole().equals("company-partner")) {
            UserEntity userEntity = this.userRepository.findById(user.getId()).orElse(null);
            session.setAttribute("company_person", userEntity);
            urlTo = "redirect:/company/company_person?id=" + userEntity.getId();
        } else {
            UserEntity userEntity = this.userRepository.findById(user.getId()).orElse(null);
            if (userEntity.getPersonById() == null) {
                session.setAttribute("company", userEntity.getCompanyById());
                urlTo = "redirect:/company/company?id=" + userEntity.getCompanyById().getId();
            } else {
                session.setAttribute("company_person", userEntity);
                urlTo = "redirect:/company/company_person?id=" + userEntity.getId();
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

    @PostMapping("/signUpSave")
    public String doSaveSignUp(@ModelAttribute("signUp") SignUp signUp, Model model) throws ParseException {
        String urlTo = "login";

        if(!signUp.getPassword().equals(signUp.getConfirmPassword())){
            model.addAttribute("error", "Incorrect password. Please try again.");
            urlTo = "signUp";
        }else if(signUp.getSurname().equals("") || signUp.getPassword().equals("") ||
                signUp.getBirthDay().equals("")|| signUp.getEmail().equals("") ||
                signUp.getName().equals("") || signUp.getConfirmPassword().equals("") ||
                signUp.getCity().equals("") || signUp.getCountry().equals("") ||
                signUp.getNumber().equals("") || signUp.getPhone().equals("") ||
                signUp.getRegion().equals("") || signUp.getStreet().equals("") ||
                signUp.getZipCode().equals("")){
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
