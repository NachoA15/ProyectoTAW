package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * @author Ignacio Alba - Gestor
     */
    @Query("select u from UserEntity u where u.email = :email and u.password = :password")
    public UserEntity authenticate(@Param("email") String email,@Param("password") String password);

    /**
     * @author Iván Delgado - Asistente
     */
    @Query("select u from UserEntity u where u.roleUserByRole.role = 'assistant' order by u.chatsById.size")
    public List<UserEntity> getAssistants();

    /**
     * @author Iván Delgado - Asistente
     */
    @Query("select u from UserEntity u where u.email = :email")
    public UserEntity findByEmail(@Param("email") String email);

}
