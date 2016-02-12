package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Trip;
import ru.javawebinar.topjava.to.TripWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class TripUtil {
    public static final List<Trip> MEAL_LIST = Arrays.asList(
            new Trip(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Trip(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Trip(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Trip(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Trip(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Trip(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static void main(String[] args) {
        List<TripWithExceed> tripWithExceeded = getFilteredWithExceeded(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        tripWithExceeded.forEach(System.out::println);

        System.out.println(getFilteredWithExceededByCycle(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<TripWithExceed> getWithExceeded(Collection<Trip> tripList, int caloriesPerDay) {
        return getFilteredWithExceeded(tripList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<TripWithExceed> getFilteredWithExceeded(Collection<Trip> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(Trip::getCalories)));

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> createWithExceed(um, caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static TripWithExceed createWithExceed(Trip um, boolean exceeded) {
        return new TripWithExceed(um.getId(), um.getDateTime(), um.getDescription(), um.getCalories(), exceeded, um.getUser().getName(), um.getDriver());
    }

    public static List<TripWithExceed> getFilteredWithExceededByCycle(List<Trip> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();
        for (Trip meal : mealList) {
            caloriesSumPerDate.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
        }

        List<TripWithExceed> tripExceeded = new ArrayList<>();
        for (Trip meal : mealList) {
            LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime)) {
                tripExceeded.add(createWithExceed(meal, caloriesSumPerDate.get(dateTime.toLocalDate()) > caloriesPerDay));
            }
        }
        return tripExceeded;
    }

}

