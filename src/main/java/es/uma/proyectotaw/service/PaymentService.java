package es.uma.proyectotaw.service;

import es.uma.proyectotaw.dao.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    protected PaymentRepository paymentRepository;

    /**
     * @author: Marina Sayago - Cliente
     */
    public List<String> getPayment(){
        List<String> listPayment = this.paymentRepository.getPayment();

        return listPayment;
    }
}
