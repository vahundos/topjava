package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface MealRepository {
    // null is meal.userId != userId
    Meal save(Meal meal, int userId);

    // false is meal.userId != userId
    // or mealId does't exist
    boolean delete(int id, int userId);

    // null if meal.userId != userId
    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllFiltered(int userId, LocalDate startDate, LocalDate endDate);
}
