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

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected CompanyRepository companyRepository;

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
        }else {
            if (userDTO.getRole().equals("management")) {
                session.setAttribute("management", userDTO);
                urlTo = "redirect:/clients";
            }else if(userDTO.getRole().equals("client")){
                Client_PersonDTO personDTO = this.personService.getPersonByUser(userDTO.getId());
                if(personDTO.getClientByPersonClient().getClientStatusByStatus().equals("Pending")){
                    model.addAttribute("error", "Management");
                    urlTo = "login";
                }else{
                    session.setAttribute("client", userDTO);
                    urlTo = "redirect:/client?id=" + userDTO.getId();
                }

            }else if(userDTO.getRole().equals("assistant")){
                session.setAttribute("assistant", userDTO);
                urlTo ="redirect:/assistant";
            } else if (userDTO.getRole().equals("company-partner")) {
                UserEntity user = this.userRepository.findById(userDTO.getId()).orElse(null);
                session.setAttribute("company_person", user);
                urlTo = "redirect:/company/company_person?id=" + user.getId();
            } else if (userDTO.getRole().equals("company-authorised")) {
                UserEntity user = this.userRepository.findById(userDTO.getId()).orElse(null);
                PersonEntity p = this.personRepository.getPersonByPersonUser(user.getId());
                CompanyEntity c = null;

                if(p == null) {
                    c = this.companyRepository.getCompanyByCompanyUser(user.getId());
                }
                else {
                    c = p.getCompanyByRelatedCompany();
                }

                if(p != null) {
                    session.setAttribute("company_person", user);
                    urlTo = "redirect:/company/company_person?id=" + user.getId();
                }
                else {
                    session.setAttribute("company", c);
                    urlTo = "redirect:/company/company?id=" + c.getId();
                }
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
