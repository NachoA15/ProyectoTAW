package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Ignacio Alba - Gestor
 */
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
}
