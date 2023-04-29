package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
