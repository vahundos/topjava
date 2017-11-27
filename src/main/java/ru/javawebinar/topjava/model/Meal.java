package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.GET, query = "SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.description=:description, m.calories=:calories," +
                " m.dateTime=:date_time WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY" +
                " m.dateTime DESC"),
        @NamedQuery(name = Meal.ALL_BETWEEN_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:user_id AND" +
                " m.dateTime BETWEEN :start_date AND :end_date ORDER BY m.dateTime DESC")
})
@Entity
@Table(name = "meals", uniqueConstraints =
        {@UniqueConstraint(columnNames = {"user_id", "date_time"},
                name = "meals_unique_datetime_id")})
public class Meal extends AbstractBaseEntity {

    public static final String GET = "Meal.get";
    public static final String UPDATE = "Meal.update";
    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED = "Meal.all";
    public static final String ALL_BETWEEN_SORTED = "Meal.allBetween";

    @NotNull
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @PositiveOrZero
    @Column(name = "calories")
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}

