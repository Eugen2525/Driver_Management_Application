package ru.javawebinar.topjava.web.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.Trip;
import ru.javawebinar.topjava.service.TripService;
import ru.javawebinar.topjava.to.TripWithExceed;
import ru.javawebinar.topjava.util.TripUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class AbstractTripController {
    protected static final LoggerWrapper LOG = LoggerWrapper.get(AbstractTripController.class);

    @Autowired
    protected TripService service;

    public Trip get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get trip {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = LoggedUser.id();
        LOG.info("delete trip {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public List<TripWithExceed> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll for User {}", userId);
        return TripUtil.getWithExceeded(service.getAll(userId), LoggedUser.getCaloriesPerDay());
    }

    public void update(Trip trip, int id) {
        trip.setId(id);
        int userId = LoggedUser.id();
        LOG.info("update {} for User {}", trip, userId);
        service.update(trip, userId);
    }

    public Trip create(Trip trip) {
        trip.setId(null);
        int userId = LoggedUser.id();
        LOG.info("create {} for User {}", trip, userId);
        return service.save(trip, userId);
    }

    public List<TripWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = LoggedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User {}", startDate, endDate, startTime, endTime, userId);
        return TripUtil.getFilteredWithExceeded(
                service.getBetweenDates(startDate, endDate, userId), startTime, endTime, LoggedUser.getCaloriesPerDay()
        );
    }
}