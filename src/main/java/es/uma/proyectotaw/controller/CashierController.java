package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dto.UserDTO;
import es.uma.proyectotaw.dto.client.Client_AccountDTO;
import es.uma.proyectotaw.dto.client.Client_ClientDTO;
import es.uma.proyectotaw.dto.client.Client_OperationDTO;
import es.uma.proyectotaw.service.*;
import es.uma.proyectotaw.ui.FilterOperationsClient;
import es.uma.proyectotaw.ui.OperationAuxClient;
import es.uma.proyectotaw.ui.ProfileAuxClient;
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
 * @author: Manuel Jesús Jerez
 */

@Controller
public class CashierController {

    @Autowired
    protected AccountService accountService;
    @Autowired
    protected OperationService operationService;
    @Autowired
    protected PaymentService paymentService;
    @Autowired
    protected ClientService clientService;

    @GetMapping("/atm/{id}")
    public String doGoATM(@PathVariable("id") Integer idClient, Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_ClientDTO client = this.clientService.getClient(idClient);
        Client_AccountDTO account = this.accountService.getAccountByIdClient(idClient);

        model.addAttribute("client", client);
        model.addAttribute("user", user);
        model.addAttribute("account", account);

        return "cashier/cashier";
    }

    @GetMapping("/currencyChangeATM/{id}")
    public String doCurrencyChangeATM(@PathVariable("id") Integer idClient, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_AccountDTO account = this.accountService.getAccountByIdClient(idClient);
        Client_ClientDTO client = this.clientService.getClient(idClient);

        if(!account.getAccountStatusByAccountStatus().getStatus().equals("Active")){
            model.addAttribute("error", "Your account need to be active to make operations");
            model.addAttribute("client", client);
            model.addAttribute("user", user);
            model.addAttribute("account", account);
            return "cashier/cashier";
        }

        OperationAuxClient operation = new OperationAuxClient();
        operation.setClient(idClient);
        operation.setOrigin(account.getId());
        operation.setDestination(account.getId());

        List<String> Currencys = this.paymentService.getPayment();

        model.addAttribute("operation", operation);
        model.addAttribute("currencys", Currencys);
        model.addAttribute("client", client);
        model.addAttribute("user", user);

        return "cashier/currencyChange";
    }

    @PostMapping("/currencyChangeATM/save")
    public String doSaveCurrencyChangeATM(@ModelAttribute("operation") OperationAuxClient operation, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");
        List<String> Currencys = this.paymentService.getPayment();

        if(user == null){
            return "redirect:/";
        }

        if(Double.parseDouble(operation.getAmount()) < 0){
            model.addAttribute("error", "Wrong value");
            model.addAttribute("currencys", Currencys);
            return "cashier/currencyChange";
        }

        this.operationService.saveCurrencyChange(operation);

        return "redirect:/client?id=" + user.getId();
    }

    @GetMapping("/transferenceATM/{id}")
    public String doTransferenceATM(@PathVariable("id") Integer idClient, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_AccountDTO account = this.accountService.getAccountByIdClient(idClient);
        Client_ClientDTO client = this.clientService.getClient(idClient);

        if(!account.getAccountStatusByAccountStatus().getStatus().equals("Active")){
            model.addAttribute("error", "Your account need to be active to make operations");
            model.addAttribute("client", client);
            model.addAttribute("user", user);
            model.addAttribute("account", account);
            return "cashier/cashier";
        }

        OperationAuxClient operation = new OperationAuxClient();

        operation.setOrigin(account.getId());

        List<Client_AccountDTO> accounts = this.accountService.getAccountWithoutMe(idClient);
        List<String> currencys = this.paymentService.getPayment();

        model.addAttribute("operation", operation);
        model.addAttribute("accounts", accounts);
        model.addAttribute("currencys", currencys);
        model.addAttribute("client", client);
        return "/cashier/transference";
    }

    @PostMapping("/transferenceATM/save")
    public String doSaveTransferenceATM(@ModelAttribute("operation") OperationAuxClient operation, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_AccountDTO account = this.accountService.getAccountByIdClient(operation.getOrigin());
        Client_ClientDTO client = this.clientService.getClient(operation.getOrigin());

        List<Client_AccountDTO> accounts = this.accountService.getAccountWithoutMe(client.getId());
        List<String> currencys = this.paymentService.getPayment();

        if(account.getBalance() < Double.parseDouble(operation.getAmount()) || Double.parseDouble(operation.getAmount()) < 0){
            model.addAttribute("error", "Wrong value");
            model.addAttribute("client", client);
            model.addAttribute("currencys", currencys);
            model.addAttribute("accounts", accounts);
            return "/cashier/transference";
        }else{
            this.operationService.saveTransference(operation);
        }

        return "redirect:/atm/" + client.getId();
    }

    @GetMapping("/showOperationsATM/{id}")
    public String doShowOperationsATM(@PathVariable("id") Integer idClient, Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_ClientDTO client = this.clientService.getClient(idClient);

        FilterOperationsClient filter = new FilterOperationsClient();
        filter.setClient(client.getId());

        List<Client_OperationDTO> listOperations = this.operationService.listOperations(filter, idClient);
        List<Client_AccountDTO> listAccounts = this.accountService.getAccount();
        List<String> listCurrencys = this.paymentService.getPayment();

        model.addAttribute("operations", listOperations);
        model.addAttribute("client", client);
        model.addAttribute("accounts", listAccounts);
        model.addAttribute("currencys", listCurrencys);
        model.addAttribute("filter", filter);

        return "cashier/operations";
    }

    @PostMapping("/filterOperationsATM")
    public String doFilterOperations(@ModelAttribute("filter") FilterOperationsClient filter, Model model, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_ClientDTO client = this.clientService.getClient(filter.getClient());

        List<Client_OperationDTO> listOperations;

        if(filter == null){
            listOperations = this.operationService.listOperations(filter, filter.getClient());
            filter = new FilterOperationsClient();
            filter.setClient(filter.getClient());
        }else {
            listOperations = this.operationService.listOperations(filter, filter.getClient());
        }

        List<Client_AccountDTO> listAccounts = this.accountService.getAccount();
        List<String> listCurrencys = this.paymentService.getPayment();

        model.addAttribute("operations", listOperations);
        model.addAttribute("client", client);
        model.addAttribute("accounts", listAccounts);
        model.addAttribute("currencys", listCurrencys);


        return "cashier/operations";
    }

    @GetMapping("/editProfileATM/{id}")
    public String doEditProfile(@PathVariable("id") Integer idClient, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        ProfileAuxClient profile = this.clientService.getProfileAux(idClient);

        model.addAttribute("user", user);
        model.addAttribute("profileAux", profile);

        return "client/profile";
    }

    @GetMapping("/activationATM/{id}")
    public String doActivationATM(@PathVariable("id") Integer idAccount, HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        this.accountService.saveActivation(idAccount);

        return "redirect:/client?id=" + user.getId();
    }

    @GetMapping("/takeMoney/{id}")
    public String doTakeMoney(@PathVariable("id") Integer idClient, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_AccountDTO account = this.accountService.getAccountByIdClient(idClient);
        Client_ClientDTO client = this.clientService.getClient(idClient);

        if(!account.getAccountStatusByAccountStatus().getStatus().equals("Active")){
            model.addAttribute("error", "Your account need to be active to make operations");
            model.addAttribute("client", client);
            model.addAttribute("user", user);
            model.addAttribute("account", account);
            return "cashier/cashier";
        }

        OperationAuxClient operation = new OperationAuxClient();

        operation.setOrigin(account.getId());
        operation.setDestination(account.getId());
        model.addAttribute("operation", operation);
        model.addAttribute("client", client);
        return "/cashier/takeMoney";
    }

    @PostMapping("/takeMoneyATM/save")
    public String doSaveTakeMoney(@ModelAttribute("operation") OperationAuxClient operation, HttpSession session, Model model){
        UserDTO user = (UserDTO) session.getAttribute("client");

        if(user == null){
            return "redirect:/";
        }

        Client_AccountDTO account = this.accountService.getAccountById(operation.getOrigin());
        Client_ClientDTO client = this.clientService.getClient(operation.getOrigin());

        String urlTo = "redirect:/atm/" + client.getId();

        if(account.getBalance() < Double.parseDouble(operation.getAmount()) || Double.parseDouble(operation.getAmount()) < 0){
            model.addAttribute("error", "Wrong value");
            if(operation.getCurrentChangeOrigin().equals("")){ //En caso de que sea solo una extracción de dinero
                urlTo = "/cashier/takeMoney";
            }else{ //En caso de que sea tambien un cambio de divisa
                List<String> currencys = this.paymentService.getPayment();
                model.addAttribute("currencys", currencys);
                urlTo = "/cashier/currencyChange";
            }
            model.addAttribute("client", client);
        }else{
            if(!operation.getCurrentChangeDestination().equals("")){
                operation.setPayment(operation.getCurrentChangeDestination());
            }else{
                operation.setPayment(account.getCurrency());
            }

            this.operationService.saveTakeMoney(operation);
        }
        return urlTo;
    }
}



