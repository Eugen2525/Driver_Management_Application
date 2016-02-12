package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.Trip;
import ru.javawebinar.topjava.repository.TripRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripRepository repository;

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public Trip get(int id, int userId) {
        return ExceptionUtil.check(repository.get(id, userId), id);
    }

    @Override
    public void delete(int id, int userId) {
        ExceptionUtil.check(repository.delete(id, userId), id);
    }

    @Override
    public Collection<Trip> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }

    @Override
    public Collection<Trip> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public Trip update(Trip meal, int userId) {
        return ExceptionUtil.check(repository.save(meal, userId), meal.getId());
    }

    @Override
    public Trip save(Trip meal, int userId) {
        //TODO use loggeduser email credentials
        String toAddr = LoggedUser.safeGet().getEmail();
        String fromAddr = "somegmailaccount@gmail.com";

        // email subject
        String subject = "Hey.. We have booked you your trip";

        // email body
        String body = "Your trip details are below:....";
        sendEmailService.sendEmail(toAddr, fromAddr, subject, body);

        return repository.save(meal, userId);
    }

//    @Autowired
//    private UserRepository userRepository;

    @Transactional
    public Trip getWithUser(Integer id, Integer userId) {
//        UserMeal trip = get(id, userId);
//        trip.setUser(userRepository.get(userId));
//        return trip;
        return repository.getWithUser(id, userId);
    }
}
