package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.AccountRepository;
import es.uma.proyectotaw.dao.CurrencyChangeRespository;
import es.uma.proyectotaw.dao.OperationRepository;
import es.uma.proyectotaw.dao.PaymentRepository;
import es.uma.proyectotaw.dto.client.Client_OperationDTO;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.ui.FilterOperationsClient;
import es.uma.proyectotaw.ui.OperationAuxClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService{
    @Autowired
    protected OperationRepository operationRepository;
    @Autowired
    protected AccountRepository accountRepository;
    @Autowired
    protected PaymentRepository paymentRepository;
    @Autowired
    protected CurrencyChangeRespository currencyChangeRespository;


    public List<Client_OperationDTO> listOperations (FilterOperationsClient filter, Integer idClient) throws ParseException {
        AccountEntity account = this.accountRepository.getAccountByIdClient(idClient);
        List<OperationEntity> listOperations = this.operationRepository.getOperationByMyAccount(account.getId());

        if(filter.getOrigin() != null) listOperations = listOperations.stream().filter(operation -> operation.getAccountByOrigin().getId() == filter.getOrigin()).collect(Collectors.toList());


        if(filter.getDestination() != null) listOperations = listOperations.stream().filter(operation -> operation.getAccountByDestination().getId() == filter.getDestination()).collect(Collectors.toList());

        if(filter.getDate() != "") listOperations = listOperations.stream().filter(operation -> operation.getDate().equals(Date.valueOf(filter.getDate()))).collect(Collectors.toList());

        if(filter.getPayment() != "") listOperations = listOperations.stream().filter(operation -> operation.getPaymentByPayment() != null &&
                operation.getPaymentByPayment().getCurrency().equals(filter.getPayment())).collect(Collectors.toList());

        if(filter.getAmount() != "") listOperations = listOperations.stream().filter(operation -> operation.getAmount() == Double.parseDouble(filter.getAmount())).collect(Collectors.toList());

        if(filter.getCurrency() != "") listOperations = listOperations.stream().filter(operation -> operation.getCurrencyChangeByCurrencyChange() != null && (operation.getCurrencyChangeByCurrencyChange().getOriginCurrency()
                .equals(filter.getCurrency()) || operation.getCurrencyChangeByCurrencyChange().getDestinationCurrency().equals(filter.getCurrency()) ||
                operation.getCurrencyChangeByCurrencyChange().getOriginCurrency().equals(filter.getCurrency()) &&
                operation.getCurrencyChangeByCurrencyChange().getDestinationCurrency().equals(filter.getCurrency()))).collect(Collectors.toList());



        return this.listaEntidadesADTO(listOperations);
    }


    protected List<Client_OperationDTO> listaEntidadesADTO (List<OperationEntity> lista) {
        ArrayList dtos = new ArrayList<Client_OperationDTO>();

        lista.forEach((final OperationEntity operation) -> dtos.add(operation.ClientToOperationDTO()));

        return dtos;
    }

    public void saveTransference(OperationAuxClient operationAuxClient){
        OperationEntity operation = new OperationEntity();
        AccountEntity accountOrigin = this.accountRepository.findById(operationAuxClient.getOrigin()).orElse(null);
        AccountEntity accountDestination = this.accountRepository.findById(operationAuxClient.getDestination()).orElse(null);

        java.sql.Date fecha = new java.sql.Date(new java.util.Date().getTime());
        operation.setDate(fecha);
        operation.setAccountByOrigin(accountOrigin);
        operation.setAccountByDestination(accountDestination);
        operation.setAmount(Double.parseDouble(operationAuxClient.getAmount()));

        PaymentEntity payment = this.paymentRepository.getPaymentEntityByCurrency(operationAuxClient.getPayment());

        operation.setPaymentByPayment(payment);

        accountOrigin.setBalance(accountOrigin.getBalance() - operation.getAmount());
        accountDestination.setBalance(accountDestination.getBalance() + operation.getAmount());

        this.operationRepository.save(operation);
        this.accountRepository.save(accountOrigin);
        this.accountRepository.save(accountDestination);
    }

    public void saveCurrencyChange(OperationAuxClient operationAuxClient){
        OperationEntity operation = new OperationEntity();
        AccountEntity account = this.accountRepository.findById(operationAuxClient.getOrigin()).orElse(null);

        java.sql.Date fecha = new java.sql.Date(new java.util.Date().getTime());
        operation.setDate(fecha);
        operation.setAccountByOrigin(account);
        operation.setAccountByDestination(account);
        operation.setAmount(Double.parseDouble(operationAuxClient.getAmount()));

        CurrencyChangeEntity currencyChange = this.currencyChangeRespository.getCurrencyChangeEntitiesByOriginAndDestination(
                operationAuxClient.getCurrentChangeOrigin(), operationAuxClient.getCurrentChangeDestination());

        if(currencyChange == null){
            currencyChange = new CurrencyChangeEntity();
            currencyChange.setOriginCurrency(operationAuxClient.getCurrentChangeOrigin());
            currencyChange.setDestinationCurrency(operationAuxClient.getCurrentChangeDestination());
            this.currencyChangeRespository.save(currencyChange);
        }

        operation.setCurrencyChangeByCurrencyChange(currencyChange);


        this.operationRepository.save(operation);
    }
}
