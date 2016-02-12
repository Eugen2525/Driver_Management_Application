
package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Trip;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface ProxyTripRepository extends JpaRepository<Trip, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Trip t WHERE t.id=:id AND t.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Trip save(Trip item);

    @Query("SELECT t FROM Trip t ORDER BY t.dateTime DESC")
    List<Trip> getAll(Pageable pageRequest);

    @Query("SELECT t FROM Trip t WHERE t.id=:id AND t.user.id=:userId")
    Trip get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT t FROM Trip t ORDER BY t.dateTime DESC")
    List<Trip> getAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM Trip t WHERE t.user.id=:userId")
    void deleteAll(@Param("userId") int userId);

    @Query("SELECT t from Trip t WHERE t.dateTime BETWEEN :startDate AND :endDate ORDER BY t.dateTime DESC")
    List<Trip> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT um FROM Trip um JOIN FETCH um.user WHERE um.id = ?1 and um.user.id = ?2 ORDER BY um.dateTime DESC")
    Trip getWithUser(Integer id, Integer userId);
}
