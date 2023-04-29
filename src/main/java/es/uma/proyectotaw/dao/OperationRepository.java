package es.uma.proyectotaw.dao;

import es.uma.proyectotaw.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {
    @Query("select o from OperationEntity o where o.accountByOrigin.id = :myAccount or o.accountByDestination.id  = :myAccount or (o.accountByOrigin.id  = :myAccount and o.accountByDestination.id  = :myAccount)")
    public List<OperationEntity> getOperationByMyAccount(@Param("myAccount") Integer myAccount);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin")
    public List<OperationEntity> getOperationByAccountOrigin(@Param("accountOrigin") Integer accountOrigin);

    @Query("select o from OperationEntity o where o.accountByDestination.id  = :accountDestination")
    public List<OperationEntity> getOperationByAccountDestination(@Param("accountDestination") Integer accountDestination);

    @Query("select o from OperationEntity o where o.date = :datee")
    public List<OperationEntity> getOperationByDate(@Param("date") Date date);

    @Query("select o from OperationEntity o where o.amount = :amount")
    public List<OperationEntity> getOperationByAmount(@Param("amount") Double accountAmount);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin and o.accountByDestination.id  = :accountDestination")
    public List<OperationEntity> getOperationByAccountOriginAndDestination(@Param("accountOrigin") Integer accountOrigin,
                                                                     @Param("accountDestination") Integer accountDestination);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin and o.date = :date")
    public List<OperationEntity> getOperationByAccountOriginAndDate(@Param("accountOrigin") Integer accountOrigin,
                                                                     @Param("date") Date date);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin and o.amount = :amount")
    public List<OperationEntity> getOperationByAccountOriginAndAmount(@Param("accountOrigin") Integer accountOrigin,
                                                                     @Param("amount") Double amount);

    @Query("select o from OperationEntity o where o.accountByDestination.id  = :accountDestination and o.date = :date")
    public List<OperationEntity> getOperationByAccountDestinationAndDate(@Param("accountDestination") Integer accountDestination,
                                                                   @Param("date") Date date);

    @Query("select o from OperationEntity o where o.accountByDestination.id  = :accountDestination and o.amount = :amount")
    public List<OperationEntity> getOperationByAccountDestinationAndAmount(@Param("accountDestination") Integer accountDestination,
                                                                   @Param("amount") Double amount);

    @Query("select o from OperationEntity o where o.date = :date and o.amount = :amount")
    public List<OperationEntity> getOperationByAccountDateAndAmount(@Param("date") Date date,
                                                                     @Param("amount") Double amount);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin and o.accountByDestination.id  = :accountDestination and o.amount = :amount")
    public List<OperationEntity> getOperationByAccountOriginAndDestinationAndAmount(@Param("accountOrigin") Integer accountOrigin,
                                                                     @Param("accountDestination") Integer accountDestination,
                                                                     @Param("amount") Double amount);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin and o.accountByDestination.id  = :accountDestination and o.date = :date")
    public List<OperationEntity> getOperationByAccountOriginAndDestinationAndDate(@Param("accountOrigin") Integer accountOrigin,
                                                                     @Param("accountDestination") Integer accountDestination,
                                                                     @Param("date") Date date);

    @Query("select o from OperationEntity o where o.accountByDestination.id  = :accountDestination and o.date = :date and o.amount = :amount")
    public List<OperationEntity> getOperationByDestinationAndDateAndAmount(@Param("accountDestination") Integer accountDestination,
                                                                            @Param("date") Date date,@Param("amount") Double amount);

    @Query("select o from OperationEntity o where o.accountByOrigin.id  = :accountOrigin and o.accountByDestination.id  = :accountDestination and o.date = :date and o.amount = :amount")
    public List<OperationEntity> getOperationByOriginAndDestinationAndDateAndAmount(@Param("accountOrigin") Integer accountOrigin,
                                                                     @Param("accountDestination") Integer accountDestination,
                                                                     @Param("date") Date date,@Param("amount") Double amount);

}
