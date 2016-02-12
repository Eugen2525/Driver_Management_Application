package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "trips")
public class Trip extends BaseEntity {

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    protected String description;
    //// TODO: 12/17/2015 5_1_question.mp4 watch once again
//TODO turn calories into internal phone number and create custom validation
//    http://stackoverflow.com/questions/8994864/how-would-i-specify-a-hibernate-pattern-annotation-using-a-regular-expression
    @Column(name = "calories")
    @Range(min = 1, max = 5)
    @NotNull
    protected Integer calories;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private Driver driver;

    public Trip() {
    }

    public Trip(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories, Driver.DRIVER_1);
    }

    public Trip(Integer id, LocalDateTime dateTime, String description, int calories, Driver driver) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.driver = driver;
    }
    //empty constructor
    public Trip(int meal1Id, LocalDateTime of, String завтрак, int i) {
    }
    //empty constructor
    public Trip(Object o, LocalDateTime of, String завтрак, int i) {
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}