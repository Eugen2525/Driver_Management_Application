package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * GKislin
 * 15.06.2015.
 */
public interface TripService {
    Trip get(int id, int userId);

    void delete(int id, int userId);

    default Collection<Trip> getBetweenDates(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }

    Collection<Trip> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    Collection<Trip> getAll(int userId);

    Trip update(Trip meal, int userId);

    Trip save(Trip meal, int userId);

    Trip getWithUser(Integer id, Integer userId);
}
