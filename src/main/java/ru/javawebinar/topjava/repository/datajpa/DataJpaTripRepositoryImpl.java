package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Trip;
import ru.javawebinar.topjava.repository.TripRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaTripRepositoryImpl implements TripRepository {
    @Autowired
    private ProxyTripRepository proxy;

    @Autowired
    private ProxyUserRepository userProxy;

    @Override
    @Transactional
    public Trip save(Trip trip, int userId) {
        if (!trip.isNew() && get(trip.getId(), userId) == null) {
            return null;
        }
        trip.setUser(userProxy.getOne(userId));
        return proxy.save(trip);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public Trip get(int id, int userId) {
        return proxy.get(id, userId);
    }


    public List<Trip> getAll(int userId)
    {
        return proxy.getAll(new PageRequest(0,20));
    }


    @Override
    public List<Trip> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate);
    }

    @Override
    public Trip getWithUser(Integer id, Integer userId) {
        return proxy.getWithUser(id, userId);
    }
}
