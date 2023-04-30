package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.dto.ClientStatusDTO;
import es.uma.proyectotaw.dto.CompanyAreaDTO;
import es.uma.proyectotaw.dto.client.Client_AccountDTO;
import es.uma.proyectotaw.dto.client.Client_PersonDTO;
import es.uma.proyectotaw.dto.management.*;
import es.uma.proyectotaw.entity.UserEntity;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    protected ClientStatusService clientStatusService;

    @Autowired
    protected AccountStatusService accountStatusService;

    @Autowired
    protected CompanyAreaService companyAreaService;

    @Autowired
    protected PersonService personService;

    @Autowired
    protected  CompanyService companyService;

    @Autowired
    protected ClientService clientService;

    @Autowired AccountService accountService;

    /*
        =================================================================================================
            GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */

    @GetMapping("/clients")
    public String doShowClients(Model model, HttpSession session) {
        return this.processFiltering(null, model, session);
    }

    @PostMapping("/clients/filter")
    public String doFilterClients(@ModelAttribute("filter")Filter filter, HttpSession session, Model model) {
        return this.processFiltering(filter, model, session);
    }

    private String processFiltering(Filter filter, Model model, HttpSession session) {

        // Comprobar que el usuario es gestor
        UserEntity user = (UserEntity) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        List<PartialPersonDTO> registeredPersons;
        List<PartialCompanyDTO> registeredCompanies = new ArrayList<>();

        List<ClientStatusDTO> clientStatuses = this.clientStatusService.listClientStatuses();
        List<AccountStatusDTO> accountStatuses = this.accountStatusService.listAccountStatuses();
        List<CompanyAreaDTO> companyAreas = this.companyAreaService.listCompanyAreas();

        if (filter == null || (filter.getText().isEmpty() && filter.getListClientStatus().isEmpty() && filter.getListAccountStatus().isEmpty() && filter.getListAreas().isEmpty())) {
            filter = new Filter();
            registeredPersons = this.personService.getRegisteredPersons();
            registeredCompanies = this.companyService.getRegisteredCompanies();
        } else {
            List<String> clientStatusesToFilter = filter.getListClientStatus().isEmpty()? this.clientStatusService.listStatusesOnStrings() : filter.getListClientStatus();
            List<String> accountStatusesToFilter = filter.getListAccountStatus().isEmpty()? this.accountStatusService.listStatusesOnStrings() : filter.getListAccountStatus();
            List<String> areasToFilter = filter.getListAreas().isEmpty()? this.companyAreaService.listAreasOnStrings() : filter.getListAreas();

            registeredPersons = this.personService.getRegisteredPersons(filter.getText(), clientStatusesToFilter, accountStatusesToFilter);
            registeredCompanies = this.companyService.getRegisteredCompanies(filter.getText(), clientStatusesToFilter, accountStatusesToFilter, areasToFilter);
        }

        model.addAttribute("filter", filter);
        model.addAttribute("clientStatuses", clientStatuses);
        model.addAttribute("accountStatuses", accountStatuses);
        model.addAttribute("companyAreas", companyAreas);

        model.addAttribute("persons", registeredPersons);
        model.addAttribute("companies", registeredCompanies);

        return "management/clients";
    }

    @GetMapping("/clients/view/{id}")
    public String doViewClient(@PathVariable("id") Integer clientId, Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        FullClientDTO client = this.clientService.getClientById(clientId);
        model.addAttribute("client", client);

        return "management/client";
    }

   @GetMapping("/clients/view/person/{id}")
    public String doViewClientPerson(@PathVariable("id") Integer personId,
                                    Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        FullPersonDTO person = this.personService.getPersonById(personId);
        model.addAttribute("person", person);

        return "management/client";
    }

    @GetMapping("clients/view/company/{id}")
    public String doViewClientCompany(@PathVariable("id") Integer companyId,
                                      Model model, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        FullCompanyDTO company = this.companyService.getCompanyById(companyId);
        model.addAttribute("company", company);
        return "management/client";
    }

    @GetMapping("/clients/view/operations/{id}")
    public String doShowOperations(@PathVariable("id") Integer clientId, Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        FullClientDTO client = this.clientService.getClientById(clientId);
        model.addAttribute("client", client);

        return "management/operations";
    }

    @GetMapping("/clients/pending")
    public String doShowPendingClients(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        List<PartialPersonDTO> pendingPersons = this.personService.getPendingPersons();
        List<PartialCompanyDTO> pendingCompanies = this.companyService.getPendingCompanies();

        model.addAttribute("pendingPersons", pendingPersons);
        model.addAttribute("pendingCompanies", pendingCompanies);

        return "management/pending_clients";
    }

    @GetMapping("clients/confirm/{id}")
    public String doConfirmRegistration(@PathVariable("id") Integer clientId,
                                        HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        this.clientService.registerClientByID(clientId);

        return "redirect:/clients/pending";
    }

    @GetMapping("/clients/delete/{id}")
    public String doDenyRegistration(@PathVariable("id") Integer clientId,
                                     HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("management");

        if (user == null) {
            return "redirect:/";
        }

        this.clientService.deleteClientById(clientId);

        return "redirect:/clients/pending";
    }

    /*
        =================================================================================================
            FIN GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */


    /*
        =================================================================================================
            CLIENTE  -- Autora: Marina Sayago
        =================================================================================================
     */

    @GetMapping("/client")
    public String doShowClient(Model model, HttpSession session, @RequestParam("id") Integer idUser){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        Client_PersonDTO person = this.personService.getPersonByUser(idUser);
        Client_AccountDTO account = this.accountService.getAccountByIdClient(person.getClientByPersonClient().getId());

        model.addAttribute("client", person);
        model.addAttribute("account", account);

        return "client/client";
    }

    /*
        =================================================================================================
            FIN CLIENTE  -- Autora: Marina Sayago
        =================================================================================================
     */
}
