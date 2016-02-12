package ru.javawebinar.topjava.web.trip;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Trip;
import ru.javawebinar.topjava.to.TripWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = TripRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TripRestController extends AbstractTripController {
    public static final String REST_URL = "/rest/profile/trips";

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Trip get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TripWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Trip trip, @PathVariable("id") int id) {
        super.update(trip, id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trip> createWithLocation(@RequestBody Trip trip) {
        Trip created = super.create(trip);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriOfNewResource);

        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/between", method = RequestMethod.GET)
    public List<TripWithExceed> getBetween(
            @RequestParam(value = "startDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam(value = "endDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {
        return super.getBetween(startDateTime.toLocalDate(), startDateTime.toLocalTime(), endDateTime.toLocalDate(), endDateTime.toLocalTime());
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public List<TripWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(
                startDate != null ? startDate : TimeUtil.MIN_DATE, startTime != null ? startTime : LocalTime.MIN,
                endDate != null ? endDate : TimeUtil.MAX_DATE, endTime != null ? endTime : LocalTime.MAX);
    }
}
