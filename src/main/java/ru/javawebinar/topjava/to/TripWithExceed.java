package ru.javawebinar.topjava.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.javawebinar.topjava.model.Driver;

import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class TripWithExceed {
    protected final Integer id;

    protected final LocalDateTime dateTime;

    protected final String description;

    protected final int calories;

    protected final boolean exceed;

    protected final String userName;

    protected final Driver driver;

    public TripWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, String userName, Driver driver) {
        this(null, dateTime, description, calories, exceed, userName, driver);
    }

    public TripWithExceed(@JsonProperty("id") Integer id,
                          @JsonProperty("dateTime") LocalDateTime dateTime,
                          @JsonProperty("description") String description,
                          @JsonProperty("calories") int calories,
                          @JsonProperty("exceed") boolean exceed,
                          @JsonProperty("userName") String userName,
                          @JsonProperty("driver") Driver driver) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.userName = userName;
        this.driver=driver;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    @Override
    public String toString() {
        return "TripWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                ", userName='" + userName + '\'' +
                ", driver=" + driver +
                '}';
    }
}
