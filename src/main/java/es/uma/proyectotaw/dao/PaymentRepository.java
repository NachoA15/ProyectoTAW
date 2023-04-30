package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    @Query("select p.currency from PaymentEntity p")
    public List<String> getPayment();

    @Query("select p from PaymentEntity p where p.currency = :currency")
    public PaymentEntity getPaymentEntityByCurrency(@Param("currency") String currency);
}
