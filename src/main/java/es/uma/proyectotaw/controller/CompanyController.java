


package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    protected PersonRepository personRepository;

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected ClientStatusRepository clientStatusRepository;

    @Autowired
    protected AccountStatusRepository accountStatusRepository;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    protected ClientRepository clientRepository;

    @Autowired
    protected AddressRepository addressRepository;

    @Autowired
    protected CompanyAreaRepository companyAreaRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected OperationRepository operationRepository;

    @Autowired
    protected CurrencyChangeRespository currencyChangeRespository;
    @Autowired
    protected PaymentRepository paymentRepository;

    @Autowired
    protected RoleUserRepository roleUserRepository;

    /**
     *
     *
     * ----------COMPANY------
     *
     *
     */

    public UserEntity getCompanyUserBySessionUser(UserEntity sessionUser) {
        PersonEntity p = this.personRepository.getPersonByPersonUser(sessionUser.getId());
        CompanyEntity c = null;

        if(p == null) {
            c = this.companyRepository.getCompanyByCompanyUser(sessionUser.getId());
        }
        else {
            c = p.getCompanyByRelatedCompany();
        }

        UserEntity userCompany = this.userRepository.getById(c.getUserByCompanyUser().getId());
        return userCompany;
    }

    @GetMapping("/company/register")
    public String registerCompany(Model model) {
        List<CompanyAreaEntity> companyAreas = this.companyAreaRepository.findAll();
        model.addAttribute("companyAreas", companyAreas);
        return "company/register";
    }

    @PostMapping("/company/register")
    public String doRegisterCompany(Model model, HttpSession session,
                                    @RequestParam("name") String name,
                                    @RequestParam("cif") String cif,
                                    @RequestParam("url") String url,
                                    @RequestParam("area") Integer area,
                                    @RequestParam("emailPartner") String emailPartner,
                                    @RequestParam("passwordPartner") String passwordPartner,
                                    @RequestParam("confirm_passwordPartner") String confirm_passwordPartner,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("surname") String surname,
                                    @RequestParam("birthdate") Date birthdate,
                                    @RequestParam("identificationNumberPartner") String identificationNumberPartner,
                                    @RequestParam("streetPartner") String streetPartner,
                                    @RequestParam("streetNumberPartner") String streetNumberPartner,
                                    @RequestParam("cityPartner") String cityPartner,
                                    @RequestParam("regionPartner") String regionPartner,
                                    @RequestParam("zipPartner") String zipPartner,
                                    @RequestParam("countryPartner") String countryPartner,
                                    @RequestParam("phonePartner") String phonePartner,
                                    @RequestParam("emailCompany") String emailCompany,
                                    @RequestParam("passwordCompany") String passwordCompany,
                                    @RequestParam("confirm_passwordCompany") String confirm_passwordCompany,
                                    @RequestParam("identificationNumberCompany") String identificationNumberCompany,
                                    @RequestParam("streetCompany") String streetCompany,
                                    @RequestParam("streetNumberCompany") String streetNumberCompany,
                                    @RequestParam("cityCompany") String cityCompany,
                                    @RequestParam("regionCompany") String regionCompany,
                                    @RequestParam("zipCompany") String zipCompany,
                                    @RequestParam("countryCompany") String countryCompany,
                                    @RequestParam("phoneCompany") String phoneCompany) {

        List<CompanyAreaEntity> companyAreas = this.companyAreaRepository.findAll();
        model.addAttribute("companyAreas", companyAreas);

        if (!passwordPartner.equals(confirm_passwordPartner) || passwordPartner.length() < 4) {
            model.addAttribute("error", "Passwords of partner dont match or are short.");
            return "company/register";
        }

        if (!passwordCompany.equals(confirm_passwordCompany) || passwordCompany.length() < 4) {
            model.addAttribute("error", "Passwords of company dont match or are short.");
            return "company/register";
        }

        UserEntity existingPartner = this.userRepository.findByEmail(emailPartner);

        if(existingPartner != null) {
            model.addAttribute("error", "Email of partner already in use.");
            return "company/register";
        }

        UserEntity existingCompany = this.userRepository.findByEmail(emailCompany);

        if(existingCompany != null) {
            model.addAttribute("error", "Email of company already in use.");
            return "company/register";
        }

        CompanyEntity existingComp = this.companyRepository.getCompanyByName(name);

        if(existingComp != null) {
            model.addAttribute("error", "Name of company already in use.");
            return "company/register";
        }

        UserEntity uPartner = new UserEntity();
        UserEntity uCompany = new UserEntity();
        PersonEntity p = new PersonEntity();
        ClientEntity clPartner = new ClientEntity();
        ClientEntity clCompany = new ClientEntity();
        AddressEntity aPartner = new AddressEntity();
        AddressEntity aCompany = new AddressEntity();
        CompanyEntity c = new CompanyEntity();

        uPartner.setEmail(emailPartner);
        uPartner.setPassword(passwordPartner);
        uCompany.setEmail(emailCompany);
        uCompany.setPassword(passwordCompany);

        Integer roleCompanyPartnerId = 4;
        Integer roleCompanyAuthorizedId = 5;
        RoleUserEntity rPartner = this.roleUserRepository.getById(roleCompanyPartnerId);
        RoleUserEntity rCompany = this.roleUserRepository.getById(roleCompanyAuthorizedId);
        uPartner.setRoleUserByRole(rPartner);
        uCompany.setRoleUserByRole(rCompany);

        ClientStatusEntity cs = this.clientStatusRepository.getById(3);
        CompanyAreaEntity ca = this.companyAreaRepository.getById(area);

        aPartner.setStreet(streetPartner);
        aPartner.setNumber(streetNumberPartner);
        aPartner.setCity(cityPartner);
        aPartner.setRegion(regionPartner);
        aPartner.setZipCode(zipPartner);
        aPartner.setCountry(countryPartner);
        aCompany.setStreet(streetCompany);
        aCompany.setNumber(streetNumberCompany);
        aCompany.setCity(cityCompany);
        aCompany.setZipCode(zipCompany);
        aCompany.setCountry(countryCompany);

        clPartner.setIdentificationNumber(identificationNumberPartner);
        clPartner.setClientStatusByStatus(cs);
        clPartner.setAddressByAddress(aPartner);
        clPartner.setPhone(phonePartner);
        clCompany.setIdentificationNumber(identificationNumberCompany);
        clCompany.setClientStatusByStatus(cs);
        clCompany.setAddressByAddress(aCompany);
        clCompany.setPhone(phoneCompany);

        c.setName(name);
        c.setCif(cif);
        c.setUrl(url);
        c.setCompanyAreaByArea(ca);
        c.setUserByCompanyUser(uCompany);
        c.setClientByCompanyClient(clCompany);

        p.setName(firstName);
        p.setSurname(surname);
        p.setBirthDate(birthdate);
        p.setUserByPersonUser(uPartner);
        p.setCompanyByRelatedCompany(c);
        p.setClientByPersonClient(clPartner);

        this.addressRepository.save(aPartner);
        this.addressRepository.save(aCompany);
        this.userRepository.save(uPartner);
        this.userRepository.save(uCompany);
        this.clientRepository.save(clPartner);
        this.clientRepository.save(clCompany);
        this.companyRepository.save(c);
        this.personRepository.save(p);

        model.addAttribute("info", "Registration was successful.");

        return "redirect:/";
    }

    @GetMapping("/company/register_user")
    public String registerCompanyUser(Model model, HttpSession session) {
        CompanyEntity company = (CompanyEntity) session.getAttribute("company");

        if (company == null) {
            return "redirect:/";
        }

        model.addAttribute("company", company);
        return "company/register_user";
    }

    @PostMapping("/company/register_user")
    public String doRegisterCompanyUser(Model model, HttpSession session,
                                        @RequestParam("firstName") String firstName,
                                        @RequestParam("surname") String surname,
                                        @RequestParam("birthdate") Date birthdate,
                                        @RequestParam("identificationNumber") String identificationNumber,
                                        @RequestParam("street") String street,
                                        @RequestParam("streetNumber") String streetNumber,
                                        @RequestParam("city") String city,
                                        @RequestParam("region") String region,
                                        @RequestParam("zip") String zip,
                                        @RequestParam("country") String country,
                                        @RequestParam("phone") String phone,
                                        @RequestParam("role") String role,
                                        @RequestParam("email") String email,
                                        @RequestParam("password") String password,
                                        @RequestParam("confirm_password") String confirm_password) {

        if (!password.equals(confirm_password) || password.length() < 4) {
            model.addAttribute("error", "Passwords of partner dont match or are short.");
            return "company/register_user";
        }

        UserEntity existingPartner = this.userRepository.findByEmail(email);

        if(existingPartner != null) {
            model.addAttribute("error", "Email already in use.");
            return "company/register_user";
        }

        UserEntity u = new UserEntity();
        PersonEntity p = new PersonEntity();
        ClientEntity cl = new ClientEntity();
        AddressEntity a = new AddressEntity();
        RoleUserEntity ru = this.roleUserRepository.getById(Integer.parseInt(role));
        CompanyEntity c = (CompanyEntity) session.getAttribute("company");

        ClientStatusEntity cs = this.clientStatusRepository.getById(3);

        u.setEmail(email);
        u.setPassword(password);
        u.setRoleUserByRole(ru);

        a.setStreet(street);
        a.setNumber(streetNumber);
        a.setCity(city);
        a.setRegion(region);
        a.setZipCode(zip);
        a.setCountry(country);

        cl.setIdentificationNumber(identificationNumber);
        cl.setClientStatusByStatus(cs);
        cl.setAddressByAddress(a);
        cl.setPhone(phone);

        p.setName(firstName);
        p.setSurname(surname);
        p.setBirthDate(birthdate);
        p.setUserByPersonUser(u);
        p.setCompanyByRelatedCompany(c);
        p.setClientByPersonClient(cl);

        this.addressRepository.save(a);
        this.userRepository.save(u);
        this.clientRepository.save(cl);
        this.personRepository.save(p);

        model.addAttribute("info", "Registration was successful.");
        return "login";
    }

    @GetMapping("/company/edit_user")
    public String editCompanyUser(Model model, HttpSession session, @RequestParam("id") Integer id) {
        UserEntity loggedUser = (UserEntity) session.getAttribute("company_person");

        if(loggedUser == null) {
            return "redirect:/";
        }

        PersonEntity p = this.personRepository.getPersonByPersonUser(id);
        model.addAttribute("companyUser", p);
        return "company/edit_user";
    }

    @PostMapping("/company/edit_user")
    public String doEditCompanyUser(Model model, HttpSession session,
                                    @RequestParam("firstName") String firstName,
                                    @RequestParam("surname") String surname,
                                    @RequestParam("birthdate") Date birthdate,
                                    @RequestParam("identificationNumber") String identificationNumber,
                                    @RequestParam("street") String street,
                                    @RequestParam("streetNumber") String streetNumber,
                                    @RequestParam("city") String city,
                                    @RequestParam("region") String region,
                                    @RequestParam("zip") String zip,
                                    @RequestParam("country") String country,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    @RequestParam("confirm_password") String confirm_password) {

        if (!password.equals(confirm_password) || password.length() < 4) {
            model.addAttribute("error", "Passwords dont match or are short.");
            return "company/edit_user";
        }

        UserEntity loggedUser = (UserEntity) session.getAttribute("company_person");

        if(loggedUser == null) {
            return "redirect:/";
        }

        UserEntity existingPartner = this.userRepository.findByEmail(email);

        if(existingPartner != null && existingPartner.getId() != loggedUser.getId()) {
            model.addAttribute("error", "Email already in use.");
            model.addAttribute("companyUser", this.personRepository.getPersonByPersonUser(loggedUser.getId()));
            return "company/edit_user";
        }

        PersonEntity p = this.personRepository.getPersonByPersonUser(loggedUser.getId());
        ClientEntity cl = this.clientRepository.getById(p.getClientByPersonClient().getId());
        AddressEntity a = this.addressRepository.getById(cl.getAddressByAddress().getId());

        loggedUser.setEmail(email);
        loggedUser.setPassword(password);

        a.setStreet(street);
        a.setNumber(streetNumber);
        a.setCity(city);
        a.setRegion(region);
        a.setZipCode(zip);
        a.setCountry(country);

        cl.setIdentificationNumber(identificationNumber);
        cl.setAddressByAddress(a);
        cl.setPhone(phone);

        if(p != null) {
            p.setName(firstName);
            p.setSurname(surname);
            p.setBirthDate(birthdate);
            p.setUserByPersonUser(loggedUser);
            p.setClientByPersonClient(cl);
        }

        this.addressRepository.save(a);
        this.userRepository.save(loggedUser);
        this.clientRepository.save(cl);
        this.personRepository.save(p);

        model.addAttribute("info", "Edit of profile was successful.");

        return "redirect:/company/company_person?id=" + loggedUser.getId();
    }

    @GetMapping("/company/edit_company")
    public String editCompany(Model model, HttpSession session, @RequestParam("id") Integer id) {
        List<CompanyAreaEntity> companyAreas = this.companyAreaRepository.findAll();
        model.addAttribute("companyAreas", companyAreas);

        UserEntity loggedUser = (UserEntity) session.getAttribute("company_person");
        CompanyEntity loggedCompany = null;

        if(loggedUser == null) {
            loggedCompany = (CompanyEntity) session.getAttribute("company");
        }

        if(loggedCompany == null && loggedUser == null) {
            return "redirect:/";
        }

        CompanyEntity c = this.companyRepository.getById(id);
        ClientEntity clientCompany = this.clientRepository.getById(c.getClientByCompanyClient().getId());
        UserEntity userCompany = this.userRepository.getById(c.getUserByCompanyUser().getId());
        model.addAttribute("company", c);
        model.addAttribute("clientCompany", clientCompany);
        model.addAttribute("userCompany", userCompany);

        return "company/edit_company";
    }

    @PostMapping("/company/edit_company")
    public String updateCompanyUser(Model model, HttpSession session,
                                    @RequestParam("name") String name,
                                    @RequestParam("cif") String cif,
                                    @RequestParam("url") String url,
                                    @RequestParam("area") String area,
                                    @RequestParam("identificationNumber") String identificationNumber,
                                    @RequestParam("street") String street,
                                    @RequestParam("streetNumber") String streetNumber,
                                    @RequestParam("city") String city,
                                    @RequestParam("region") String region,
                                    @RequestParam("zip") String zip,
                                    @RequestParam("country") String country,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    @RequestParam("confirm_password") String confirm_password) {

        List<CompanyAreaEntity> companyAreas = this.companyAreaRepository.findAll();
        UserEntity loggedUser = (UserEntity) session.getAttribute("company_person");
        CompanyEntity loggedCompany = null;
        PersonEntity p = null;
        CompanyEntity c = null;

        if(loggedUser == null) {
            loggedCompany = (CompanyEntity) session.getAttribute("company");
            c = loggedCompany;
        }
        else {
            p = this.personRepository.getPersonByPersonUser(loggedUser.getId());
            c = p.getCompanyByRelatedCompany();
        }

        if(loggedCompany == null && loggedUser == null) {
            return "redirect:/";
        }

        UserEntity existingCompanyEmail = this.userRepository.findByEmail(email);

        ClientEntity clientCompany = this.clientRepository.getById(c.getClientByCompanyClient().getId());
        UserEntity userCompany = this.userRepository.getById(c.getUserByCompanyUser().getId());
        AddressEntity a = this.addressRepository.getById(clientCompany.getAddressByAddress().getId());

        if (!password.equals(confirm_password) || password.length() < 4) {
            model.addAttribute("error", "Passwords dont match or are short.");
            model.addAttribute("company", c);
            model.addAttribute("clientCompany", clientCompany);
            model.addAttribute("userCompany", userCompany);
            model.addAttribute("companyAreas", companyAreas);
            return "company/edit_company";
        }

        if(existingCompanyEmail != null && existingCompanyEmail.getId() != userCompany.getId()) {
            model.addAttribute("error", "Email already in use.");
            model.addAttribute("company", c);
            model.addAttribute("clientCompany", clientCompany);
            model.addAttribute("userCompany", userCompany);
            model.addAttribute("companyAreas", companyAreas);
            return "company/edit_company";
        }

        userCompany.setEmail(email);
        userCompany.setPassword(password);

        a.setStreet(street);
        a.setNumber(streetNumber);
        a.setCity(city);
        a.setRegion(region);
        a.setZipCode(zip);
        a.setCountry(country);

        clientCompany.setIdentificationNumber(identificationNumber);
        clientCompany.setAddressByAddress(a);
        clientCompany.setPhone(phone);

        CompanyAreaEntity ca = this.companyAreaRepository.getById(Integer.parseInt(area));

        c.setName(name);
        c.setCif(cif);
        c.setUrl(url);
        c.setCompanyAreaByArea(ca);
        c.setUserByCompanyUser(userCompany);
        c.setClientByCompanyClient(clientCompany);

        this.addressRepository.save(a);
        this.userRepository.save(userCompany);
        this.clientRepository.save(clientCompany);
        this.companyRepository.save(c);

        return "redirect:/company/company?id=" + c.getId();
    }

    @GetMapping("/company/users")
    public String showCompanyUsers(Model model, HttpSession httpSession, @RequestParam("id") Integer idCompany) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if(loggedUser == null) {
            return "redirect:/";
        }

        FilterCompanyUsers filter = new FilterCompanyUsers();

        return this.doShowCompanyUsers(filter, model, idCompany, loggedUser);
    }

    public String doShowCompanyUsers(FilterCompanyUsers filter, Model model, Integer idCompany, UserEntity loggedUser){
        List<PersonEntity> companyUsers = null;

        if(filter.getName() == "" && filter.getSurname() == "" && filter.getCountry() == "" &&
                filter.getRole() == null || filter == null){
            companyUsers = this.personRepository.getPeopleByCompany(idCompany);
            filter = new FilterCompanyUsers();

        }else if(filter.getName() != "" && filter.getSurname() == "" && filter.getCountry() == "" &&
                filter.getRole() == null){
            companyUsers = this.personRepository.getByName(filter.getName(), idCompany);

        }else if(filter.getName() == "" && filter.getSurname() != "" && filter.getCountry() == "" &&
                filter.getRole() == null){
            companyUsers = this.personRepository.getBySurname(filter.getSurname(), idCompany);

        }else if(filter.getName() == "" && filter.getSurname() == "" && filter.getCountry() != "" &&
                filter.getRole() == null) {
            companyUsers = this.personRepository.getByCountry(filter.getCountry(), idCompany);
        }
        else if(filter.getName() == "" && filter.getSurname() == "" && filter.getCountry() == "" &&
                filter.getRole() != null) {
            companyUsers = this.personRepository.getByRole(filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() != "" && filter.getCountry() == "" &&
                filter.getRole() == null){
            companyUsers = this.personRepository.getByNameAndSurname(filter.getName(), filter.getSurname(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() == "" && filter.getCountry() != "" &&
                filter.getRole() == null){
            companyUsers = this.personRepository.getByNameAndCountry(filter.getName(), filter.getCountry(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() == "" && filter.getCountry() == "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getByNameAndRole(filter.getName(), filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() == "" && filter.getSurname() != "" && filter.getCountry() != "" &&
                filter.getRole() == null){
            companyUsers = this.personRepository.getBySurnameAndCountry(filter.getSurname(), filter.getCountry(), idCompany);
        }
        else if(filter.getName() == "" && filter.getSurname() != "" && filter.getCountry() == "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getBySurnameAndRole(filter.getSurname(), filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() == "" && filter.getSurname() == "" && filter.getCountry() != "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getByCountryAndRole(filter.getCountry(), filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() == "" && filter.getSurname() != "" && filter.getCountry() != "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getBySurnameCountryRole(filter.getSurname(), filter.getCountry(), filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() == "" && filter.getCountry() != "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getByNameCountryRole(filter.getName(), filter.getCountry(), filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() != "" && filter.getCountry() == "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getByNameSurnameRole(filter.getName(), filter.getSurname(), filter.getRole().getId(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() != "" && filter.getCountry() != "" &&
                filter.getRole() == null){
            companyUsers = this.personRepository.getByNameSurnameCountry(filter.getName(), filter.getSurname(), filter.getCountry(), idCompany);
        }
        else if(filter.getName() != "" && filter.getSurname() != "" && filter.getCountry() != "" &&
                filter.getRole() != null){
            companyUsers = this.personRepository.getByNameSurnameCountryRole(filter.getName(), filter.getSurname(), filter.getCountry(), filter.getRole().getId(), idCompany);
        }

        List<RoleUserEntity> roles = this.roleUserRepository.getCompanyRoles();
        CompanyEntity company = this.companyRepository.getById(idCompany);
        UserEntity companyUser = this.userRepository.getById(company.getUserByCompanyUser().getId());

        model.addAttribute("roles", roles);
        model.addAttribute("companyUsers", companyUsers);
        model.addAttribute("companyUser", companyUser);
        model.addAttribute("company", company);
        model.addAttribute("filter", filter);
        model.addAttribute("loggedUser", loggedUser);

        return "company/users";
    }

    @PostMapping("/company/filterCompanyUsers")
    public String filterCompanyUsers(Model model, HttpSession httpSession, @ModelAttribute("filter") FilterCompanyUsers filter) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        UserEntity companyUser = getCompanyUserBySessionUser(loggedUser);
        CompanyEntity c = this.companyRepository.getCompanyByCompanyUser(companyUser.getId());

        return this.doShowCompanyUsers(filter, model, c.getId(), loggedUser);
    }

    @GetMapping("/company/block_partner")
    public String blockCompanyUsers(Model model, HttpSession httpSession, @RequestParam("id") Integer idUser) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        ClientEntity client = this.clientRepository.getClientByUser(idUser);
        ClientStatusEntity blocked = this.clientStatusRepository.findByState("Blocked");
        client.setClientStatusByStatus(blocked);

        this.clientRepository.save(client);

        FilterCompanyUsers filter = new FilterCompanyUsers();

        return this.doShowCompanyUsers(filter, model, client.getPersonById().getCompanyByRelatedCompany().getId(), loggedUser);
    }

    @GetMapping("/company/transfer")
    public String makeTransfer(Model model, HttpSession httpSession, @RequestParam("id") Integer idUser) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        ClientEntity client = this.clientRepository.getClientByUser(idUser);

        if(client.getAccountById().getAccountStatusByAccountStatus().getId() != 1){
            model.addAttribute("company_person", this.personRepository.getPersonByPersonUser(idUser));
            return "redirect:/company/company_person?id=" + idUser;
        }else{
            OperationAuxCompany operation = new OperationAuxCompany();

            operation.setOrigin(client.getAccountById());

            List<AccountEntity> listAccounts = this.accountRepository.getAccountWithoutMe(client.getId());
            List<String> listCurrency = this.currencyChangeRespository.getCurrencyChangeByOrigin();
            List<String> listCurrencyDestination = this.currencyChangeRespository.getCurrencyChangeByDestination();
            listCurrency.addAll(listCurrencyDestination);

            model.addAttribute("operation", operation);
            model.addAttribute("accounts", listAccounts);
            model.addAttribute("currency", listCurrency);
        }

        return "company/transfer";
    }

    @PostMapping("/company/transfer")
    public String doMakeTransfer(@ModelAttribute("operation") OperationAuxCompany operationAuxCompany, HttpSession session) {
        UserEntity loggedUser = (UserEntity) session.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        OperationEntity operation = new OperationEntity();

        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        operation.setDate(date);
        operation.setAccountByOrigin(operationAuxCompany.getOrigin());
        operation.setAccountByDestination(operationAuxCompany.getDestination());
        operation.setAmount(operationAuxCompany.getAmount());

        PaymentEntity payment = new PaymentEntity();
        payment.setCurrency(operationAuxCompany.getPayment());

        operation.setPaymentByPayment(payment);

        AccountEntity accountOrigin = operation.getAccountByOrigin();
        AccountEntity accountDestination = operation.getAccountByDestination();

        accountOrigin.setBalance(accountOrigin.getBalance() - operation.getAmount());
        accountDestination.setBalance(accountDestination.getBalance() + operation.getAmount());

        this.paymentRepository.save(payment);
        this.operationRepository.save(operation);
        this.accountRepository.save(accountOrigin);
        this.accountRepository.save(accountDestination);

        return "redirect:/company/company_person?id=" + loggedUser.getId();
    }

    @GetMapping("/company/currency_change")
    public String changeCurrency(Model model, HttpSession httpSession, @RequestParam("id") Integer idUser) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        ClientEntity client = this.clientRepository.getClientByUser(idUser);

        if(client.getAccountById().getAccountStatusByAccountStatus().getId() != 1){
            return "redirect:/company/company_person?id=" + idUser;
        }else{
            OperationAuxCompany operation = new OperationAuxCompany();
            operation.setOrigin(client.getAccountById());
            operation.setDestination(client.getAccountById());

            List<AccountEntity> listAccounts = new ArrayList<>();
            AccountEntity account = this.accountRepository.findById(client.getId()).orElse(null);
            listAccounts.add(account);

            List<String> listCurrency = this.currencyChangeRespository.getCurrencyChangeByOrigin();
            List<String> listCurrencyDestination = this.currencyChangeRespository.getCurrencyChangeByDestination();
            listCurrency.addAll(listCurrencyDestination);

            model.addAttribute("operation", operation);
            model.addAttribute("accounts", listAccounts);
            model.addAttribute("currency", listCurrency);
        }

        return "company/currency_change";
    }

    @PostMapping("/company/currency_change")
    public String doChangeCurrency(HttpSession httpSession, @ModelAttribute("operation") OperationAuxCompany operationAuxCompany) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        OperationEntity operation = new OperationEntity();

        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        operation.setDate(date);
        operation.setAccountByOrigin(operationAuxCompany.getOrigin());
        operation.setAccountByDestination(operationAuxCompany.getDestination());
        operation.setAmount(operationAuxCompany.getAmount());

        CurrencyChangeEntity currencyChange = new CurrencyChangeEntity();
        currencyChange.setOriginCurrency(operationAuxCompany.getCurrentChangeOrigin());
        currencyChange.setDestinationCurrency(operationAuxCompany.getCurrentChangeDestination());
        operation.setCurrencyChangeByCurrencyChange(currencyChange);

        this.currencyChangeRespository.save(currencyChange);
        this.operationRepository.save(operation);

        return "redirect:/company/company_person?id=" + loggedUser.getId();
    }

    @GetMapping("/company/operations")
    public String showCompanyOperations(Model model, HttpSession httpSession, @RequestParam("id") Integer idCompany) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if(loggedUser == null) {
            return "redirect:/";
        }

        FilterOperationsCompany filter = new FilterOperationsCompany();

        return this.doShowOperations(filter, model, idCompany);
    }

    public String doShowOperations(FilterOperationsCompany filter, Model model, Integer idCompany){
        List<OperationEntity> listOperations = null;

        if(filter.getOrigin() == null && filter.getDestination() == null && filter.getDate() == null &&
                filter.getAmount() == 0|| filter == null){
            listOperations = this.operationRepository.getOperationsByCompanyRelatedPeople(idCompany);

        }else if(filter.getOrigin() != null && filter.getDestination() == null && filter.getDate() == null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByAccountOrigin(filter.getOrigin().getId());

        }else if(filter.getOrigin() == null && filter.getDestination() != null && filter.getDate() == null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByAccountDestination(filter.getDestination().getId());

        }else if(filter.getOrigin() == null && filter.getDestination() == null && filter.getDate() != null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByDate(filter.getDate());

        }else if(filter.getOrigin() == null && filter.getDestination() == null && filter.getDate() == null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByAmount(filter.getAmount());

        }else if(filter.getOrigin() != null && filter.getDestination() != null && filter.getDate() == null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByAccountOriginAndDestination(filter.getOrigin().getId(), filter.getDestination().getId());

        }else if(filter.getOrigin() != null && filter.getDestination() == null && filter.getDate() != null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByAccountOriginAndDate(filter.getOrigin().getId(), filter.getDate());

        }else if(filter.getOrigin() != null && filter.getDestination() == null && filter.getDate() == null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByAccountOriginAndAmount(filter.getOrigin().getId(), filter.getAmount());

        }else if(filter.getOrigin() == null && filter.getDestination() != null && filter.getDate() != null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByAccountDestinationAndDate(filter.getDestination().getId(), filter.getDate());

        }else if(filter.getOrigin() == null && filter.getDestination() != null && filter.getDate() == null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByAccountDestinationAndAmount(filter.getDestination().getId(), filter.getAmount());
        }else if(filter.getOrigin() != null && filter.getDestination() != null && filter.getDate() != null && filter.getAmount() == 0){
            listOperations = this.operationRepository.getOperationByAccountOriginAndDestinationAndDate(filter.getOrigin().getId(), filter.getDestination().getId(), filter.getDate());

        }else if(filter.getOrigin() == null && filter.getDestination() != null && filter.getDate() != null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByDestinationAndDateAndAmount(filter.getDestination().getId(), filter.getDate(), filter.getAmount());

        }else if(filter.getOrigin() != null && filter.getDestination() != null && filter.getDate() != null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByOriginAndDestinationAndDateAndAmount(filter.getOrigin().getId(),filter.getDestination().getId(), filter.getDate(), filter.getAmount());

        }else if(filter.getOrigin() == null && filter.getDestination() == null && filter.getDate() != null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByAccountDateAndAmount(filter.getDate(), filter.getAmount());

        }else if(filter.getOrigin() != null && filter.getDestination() != null && filter.getDate() == null && filter.getAmount() > 0){
            listOperations = this.operationRepository.getOperationByAccountOriginAndDestinationAndAmount(filter.getOrigin().getId(),filter.getDestination().getId(), filter.getAmount());
        }

        if (filter.getSortBy().equals("dateAsc")) {
            listOperations.sort(Comparator.comparing(OperationEntity::getDate));
        }
        else if (filter.getSortBy().equals("dateDesc")) {
            listOperations.sort(Comparator.comparing(OperationEntity::getDate).reversed());
        }
        else if (filter.getSortBy().equals("originAsc")) {
            Collections.sort(listOperations, Comparator.comparing(operation -> operation.getAccountByOrigin().getIban()));
        }
        else if (filter.getSortBy().equals("originDesc")) {
            Collections.sort(listOperations, Comparator.comparing(operation -> operation.getAccountByOrigin().getIban()));
            Collections.reverse(listOperations);
        }
        else if (filter.getSortBy().equals("destinationAsc")) {
            Collections.sort(listOperations, Comparator.comparing(operation -> operation.getAccountByDestination().getIban()));
        }
        else if (filter.getSortBy().equals("destinationDesc")) {
            Collections.sort(listOperations, Comparator.comparing(operation -> operation.getAccountByDestination().getIban()));
            Collections.reverse(listOperations);
        }
        else if (filter.getSortBy().equals("amountAsc")) {
            Collections.sort(listOperations, Comparator.comparing(operation -> operation.getAmount()));
        }
        else if (filter.getSortBy().equals("amountDesc")) {
            Collections.sort(listOperations, Comparator.comparing(operation -> operation.getAmount()));
            Collections.reverse(listOperations);
        }

        List<AccountEntity> accounts = this.accountRepository.getAccountsByCompany(idCompany);
        CompanyEntity company = this.companyRepository.getById(idCompany);

        model.addAttribute("accounts", accounts);
        model.addAttribute("companyOperations", listOperations);
        model.addAttribute("company", company);
        model.addAttribute("filter", filter);

        return "company/operations";
    }

    @PostMapping("/company/filterCompanyOperations")
    public String filterCompanyOperations(Model model, HttpSession httpSession, @ModelAttribute("filter") FilterOperationsCompany filter) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        UserEntity companyUser = getCompanyUserBySessionUser(loggedUser);
        CompanyEntity c = this.companyRepository.getCompanyByCompanyUser(companyUser.getId());

        return this.doShowOperations(filter, model, c.getId());
    }

    @GetMapping("/company/request_activation")
    public String requestActivation(HttpSession httpSession, @RequestParam("id") Integer idUser) {
        UserEntity loggedUser = (UserEntity) httpSession.getAttribute("company_person");

        if (loggedUser == null) {
            return "redirect:/";
        }

        ClientEntity client = this.clientRepository.getClientByUser(idUser);
        ClientStatusEntity pending = this.clientStatusRepository.findByState("Pending");
        client.setClientStatusByStatus(pending);

        this.clientRepository.save(client);

        return "redirect:/company/company_person?id=" + loggedUser.getId();
    }

    @GetMapping("/company/company_person")
    public String doShowCompanyPartner(Model model, HttpSession session, @RequestParam("id") Integer idUser){
        UserEntity user = (UserEntity) session.getAttribute("company_person");

        if (user == null) {
            return "redirect:/";
        }

        PersonEntity p = this.personRepository.getPersonByPersonUser(idUser);
        AccountEntity ac = this.accountRepository.getAccountByIdClient(p.getClientByPersonClient().getId());
        model.addAttribute("company_person", p);
        model.addAttribute("account", ac);

        return "company/company_person";
    }

    @GetMapping("/company/company")
    public String doShowCompany(Model model, HttpSession session, @RequestParam("id") Integer idUser){
        CompanyEntity company = (CompanyEntity) session.getAttribute("company");
        UserEntity loggedUser = null;
        UserEntity userCompany = null;
        Boolean isCompanyUser = null;

        if(company != null) {
            loggedUser = company.getUserByCompanyUser();
            isCompanyUser = true;
        }
        else {
            loggedUser = (UserEntity) session.getAttribute("company_person");
            isCompanyUser = false;
            userCompany = getCompanyUserBySessionUser(loggedUser);
            company = this.companyRepository.getCompanyByCompanyUser(userCompany.getId());
        }

        if (loggedUser == null && company == null) {
            return "redirect:/";
        }

        List<ClientEntity> clients = this.clientRepository.getClientsByCompany(company.getId());

        model.addAttribute("company", company);
        model.addAttribute("isCompanyUser", isCompanyUser);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("clients", clients);

        return "company/company";
    }
}


