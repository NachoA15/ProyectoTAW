package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.dto.ClientStatusDTO;
import es.uma.proyectotaw.dto.CompanyAreaDTO;
import es.uma.proyectotaw.dto.client.Client_AccountDTO;
import es.uma.proyectotaw.dto.client.Client_ClientDTO;
import es.uma.proyectotaw.dto.client.Client_OperationDTO;
import es.uma.proyectotaw.dto.client.Client_PersonDTO;
import es.uma.proyectotaw.dto.management.*;
import es.uma.proyectotaw.entity.UserEntity;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
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
    protected CompanyService companyService;

    @Autowired
    protected ClientService clientService;

    @Autowired
    protected AccountService accountService;

    @Autowired
    protected OperationService operationService;

    @Autowired
    protected CurrencyChangeService currencyChangeService;

    @Autowired
    protected PaymentService paymentService;

    /*
        =================================================================================================
            GESTOR  -- Autor: Ignacio Alba
        =================================================================================================
     */

    private String[] orderCriteria = {"Amount"};

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
        UserDTO user = (UserDTO) session.getAttribute("management");
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
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
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
       // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        FullPersonDTO person = this.personService.getFullPersonById(personId);
        model.addAttribute("person", person);

        return "management/client";
    }

    @GetMapping("clients/view/company/{id}")
    public String doViewClientCompany(@PathVariable("id") Integer companyId,
                                      Model model, HttpSession session) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        FullCompanyDTO company = this.companyService.getFullCompanyById(companyId);
        model.addAttribute("company", company);
        return "management/client";
    }

    @GetMapping("/clients/operations/person/{id}")
    public String doShowPersonOperations(@PathVariable("id") Integer personId, Model model, HttpSession session) {
        return this.processOperationFiltering(null, model, session, "person", personId);
    }

    @PostMapping("/clients/operations/person/{id}/filter")
    public String doFilterPersonOperations(@ModelAttribute("filter")FilterOperationsManagement filter,
                                     @PathVariable("id") Integer id, HttpSession session, Model model) {
        return this.processOperationFiltering(filter, model, session,"person",id);
    }

    @GetMapping("/clients/operations/company/{id}")
    public String doShowCompanyOperations(@PathVariable("id") Integer companyId, Model model, HttpSession session) {
        return this.processOperationFiltering(null, model, session, "company", companyId);
    }

    @PostMapping("/clients/operations/company/{id}/filter")
    public String doFilterCompanyOperations(@ModelAttribute("filter")FilterOperationsManagement filter,
                                     @PathVariable("id") Integer id, HttpSession session, Model model) {
        return this.processOperationFiltering(filter, model, session,"company",id);
    }

    private String processOperationFiltering(FilterOperationsManagement filter, Model model, HttpSession session, String clientType, Integer id) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        List<OperationDTO> operations;

        if (filter == null || (filter.getOrigin().isEmpty() && filter.getDestination().isEmpty())) {
            filter = new FilterOperationsManagement();
            if (clientType.equals("person")) {
                PartialPersonDTO person = this.personService.getPartialPersonById(id);
                operations = this.operationService.getOperations(person.getClient());
                model.addAttribute("person", person);
            } else {
                PartialCompanyDTO company = this.companyService.getPartialCompanyById(id);
                operations = this.operationService.getOperations(company.getClient());
                model.addAttribute("company", company);
            }
        } else {
            if (clientType.equals("person")) {
                PartialPersonDTO person = this.personService.getPartialPersonById(id);
                operations = this.operationService.getOperationsByText(person.getClient(), filter.getOrigin(), filter.getDestination(), filter.getOrder());
                model.addAttribute("person", person);
            } else {
                PartialCompanyDTO company = this.companyService.getPartialCompanyById(id);
                operations = this.operationService.getOperationsByText(company.getClient(), filter.getOrigin(), filter.getDestination(), filter.getOrder());
                model.addAttribute("company", company);
            }
        }

        model.addAttribute("filter", filter);
        model.addAttribute("operations", operations);


        model.addAttribute("orderCriteria", orderCriteria);

        return "management/operations";
    }

    @GetMapping("/clients/pending")
    public String doShowPendingClients(Model model, HttpSession session) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        List<PartialPersonDTO> pendingPersons = this.personService.getPendingPersons();
        List<PartialCompanyDTO> pendingCompanies = this.companyService.getPendingCompanies();

        model.addAttribute("pendingPersons", pendingPersons);
        model.addAttribute("pendingCompanies", pendingCompanies);

        return "management/pending_clients";
    }

    @GetMapping("clients/inactive")
    public String doShowInactiveClients(Model model, HttpSession session) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        List<PartialPersonDTO> inactivePersons = this.personService.getInactivePersons();
        List<PartialCompanyDTO> inactiveCompanies = this.companyService.getInactiveCompanies();

        model.addAttribute("inactivePersons", inactivePersons);
        model.addAttribute("inactiveCompanies", inactiveCompanies);

        return "management/inactive";
    }

    @GetMapping("clients/confirm/{id}")
    public String doConfirmRegistration(@PathVariable("id") Integer clientId,
                                        HttpSession session) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        this.clientService.registerClientByID(clientId);

        return "redirect:/clients/pending";
    }

    @GetMapping("/clients/delete/{id}")
    public String doDenyRegistration(@PathVariable("id") Integer clientId,
                                     HttpSession session) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        this.clientService.deleteClientById(clientId);

        return "redirect:/clients/pending";
    }

    @GetMapping("/clients/block/{id}")
    public String doBlockAccount(@PathVariable("id") Integer clientId, HttpSession session) {
        // Comprobar que el usuario es gestor
        UserDTO user = (UserDTO) session.getAttribute("management");
        if (user == null) {
            return "redirect:/";
        }

        this.clientService.block(clientId);

        return "redirect:/clients/inactive";
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

    @GetMapping("/updateProfile")
    public String doUpdateProfile(Model model, HttpSession session, @RequestParam("id") Integer idClient){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        ProfileAuxClient profileAuxClient = this.clientService.getProfileAux(idClient);

        model.addAttribute("profileAux", profileAuxClient);
        model.addAttribute("user", user);

        return "client/profile";
    }

    @PostMapping("/saveProfile")
    public String doSaveProfile(HttpSession session, @ModelAttribute("profileAux") ProfileAuxClient profileAuxClient) throws ParseException {
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        this.clientService.saveClient(profileAuxClient);

        return "redirect:/client?id=" + user.getId();
    }

    @GetMapping("/seeOperations")
    public String doSeeOperations(Model model, @RequestParam("id") Integer idClient, HttpSession session) throws ParseException {
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        FilterOperationsClient filter = new FilterOperationsClient();
        Client_ClientDTO clientDTO = this.clientService.getClient(idClient);
        filter.setClient(clientDTO.getId());

        return this.doShowOperations(filter, model, clientDTO.getId(), session);
    }

    public String doShowOperations(FilterOperationsClient filter, Model model, Integer client, HttpSession session) throws ParseException {
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        List<Client_OperationDTO> listOperations;

        if(filter.getOrigin() == null && filter.getDestination() == null && filter.getDate() == "" &&
                filter.getAmount() == "" && filter.getCurrency() == "" && filter.getCurrency() == ""|| filter == null){
            listOperations = this.operationService.listOperations(filter, client);
            filter = new FilterOperationsClient();
            filter.setClient(client);

        }else{
            listOperations = this.operationService.listOperations(filter, client);
        }

        List<Client_AccountDTO> listaAccounts = this.accountService.getAccount();
        List<String> listCurrency = this.paymentService.getPayment();

        model.addAttribute("accounts", listaAccounts);
        model.addAttribute("operations", listOperations);
        model.addAttribute("currencyPayment", listCurrency);
        model.addAttribute("filter", filter);
        model.addAttribute("user", user);


        return "client/operations";
    }

    @PostMapping("/client/filter")
    public String doFiltrarOperaciones(@ModelAttribute("filter") FilterOperationsClient filter,
                                       Model model, HttpSession session) throws ParseException {
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        return this.doShowOperations(filter, model, filter.getClient(),session);

    }

    @GetMapping("/transference")
    public String doMakeATransference(Model model, @RequestParam("id") Integer idClient, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("client");
        String url = "client/transference";

        if (user == null) {
            return "redirect:/";
        }

        Client_AccountDTO accountDTO = this.accountService.getAccountByIdClient(idClient);

        if(!accountDTO.getAccountStatusByAccountStatus().getStatus().equals("Active")){
            model.addAttribute("error", "To be able to make operations, your account must be active.");
            url = this.doShowClient(model,session, user.getId());
        }else{
            OperationAuxClient operation = new OperationAuxClient();
            operation.setOrigin(accountDTO.getId());

            List<Client_AccountDTO> listAccounts = this.accountService.getAccountWithoutMe(idClient);
            List<String> listCurrency = this.paymentService.getPayment();

            model.addAttribute("operation", operation);
            model.addAttribute("accounts", listAccounts);
            model.addAttribute("currency", listCurrency);
            model.addAttribute("user", user);
        }



        return url;
    }

    @PostMapping("/transference/save")
    public String doSaveTransference(@ModelAttribute("operation") OperationAuxClient operationAuxClient,
                                     HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        Client_AccountDTO accountDTO = this.accountService.getAccountById(operationAuxClient.getOrigin());
        String url = "redirect:/client?id=" + user.getId();

        if (user == null) {
            return "redirect:/";
        }

        if(operationAuxClient.getOrigin() == null || operationAuxClient.getDestination() == null ||
                operationAuxClient.getAmount().equals("") ||
                operationAuxClient.getPayment().equals("") || operationAuxClient.getAmount().equals("")){

            model.addAttribute("error", "Complete all the fields.");
            url = this.doMakeATransference(model, accountDTO.getClientByOwner().getId(), session);

        } else if(accountDTO.getBalance() - Double.parseDouble(operationAuxClient.getAmount()) < 0){
            model.addAttribute("error", "Nonpayment.");
            model.addAttribute("user", user);
            url = "client/transference";
        }else if(Double.parseDouble(operationAuxClient.getAmount()) <= 0){
            model.addAttribute("error", "The amount must be positive.");
            url = this.doMakeATransference(model, accountDTO.getClientByOwner().getId(), session);
        }else {
            this.operationService.saveTransference(operationAuxClient);
        }


        return url;
    }

    @GetMapping("/currencyChange")
    public String doMakeACurrencyChange(Model model, @RequestParam("id") Integer idClient, HttpSession session){
        String url = "client/currencyChange";
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        Client_AccountDTO accountDTO = this.accountService.getAccountByIdClient(idClient);


        if(!accountDTO.getAccountStatusByAccountStatus().getStatus().equals("Active")){
            model.addAttribute("error", "To be able to make operations, your account must be active.");
            url = this.doShowClient(model,session, user.getId());
        }else{
            OperationAuxClient operation = new OperationAuxClient();
            operation.setClient(accountDTO.getClientByOwner().getId());
            operation.setOrigin(accountDTO.getId());
            operation.setDestination(accountDTO.getId());


            List<String> listCurrency = this.paymentService.getPayment();

            model.addAttribute("operation", operation);
            model.addAttribute("currency", listCurrency);
            model.addAttribute("user", user);
        }
        return url;
    }

    @PostMapping("/currencyChange/save")
    public String doSaveCurrencyChange(@ModelAttribute("operation") OperationAuxClient operationAuxClient,
                                       HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        Client_AccountDTO accountDTO = this.accountService.getAccountById(operationAuxClient.getOrigin());
        String url = "redirect:/client?id=" + user.getId();

        if (user == null) {
            return "redirect:/";
        }

        if(operationAuxClient.getOrigin() == null || operationAuxClient.getDestination() == null ||
                operationAuxClient.getAmount().equals("") || operationAuxClient.getCurrentChangeOrigin().equals("") ||
                operationAuxClient.getDestination().equals("") || operationAuxClient.getAmount().equals("")){

            model.addAttribute("error", "Complete all the fields.");
            url = this.doMakeACurrencyChange(model, accountDTO.getClientByOwner().getId(), session);

        }else if(accountDTO.getBalance() < 0){
            model.addAttribute("error", "Nonpayment.");
            model.addAttribute("user", user);
            url = "client/currencyChange";
        }else if(Double.parseDouble(operationAuxClient.getAmount()) <= 0){
            model.addAttribute("error", "The amount must be positive.");
            url = this.doMakeACurrencyChange(model, accountDTO.getClientByOwner().getId(), session);
        }else {
            this.operationService.saveCurrencyChange(operationAuxClient);
        }

        return url;
    }

    @GetMapping("/activation")
    public String doActivation(@RequestParam("id")Integer idAccount, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if (user == null) {
            return "redirect:/";
        }

        this.accountService.saveActivation(idAccount);

        return "redirect:/client?id=" + user.getId();
    }

    /*
        =================================================================================================
            FIN CLIENTE  -- Autora: Marina Sayago
        =================================================================================================
     */
}
