package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Trip;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface TripRepository {
    // null if updated trip do not belong to userId
    Trip save(Trip trip, int userId);

    // false if trip do not belong to userId
    boolean delete(int id, int userId);

    // null if trip do not belong to userId
    Trip get(int id, int userId);

    // ORDERED DATE, TIME
    Collection<Trip> getAll(int userId);

    // ORDERED DATE, TIME
    Collection<Trip> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);

    default Trip getWithUser(Integer id, Integer userId) {
        throw new UnsupportedOperationException();
    }
}
