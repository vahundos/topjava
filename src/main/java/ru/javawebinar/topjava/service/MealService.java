package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {

    Meal create(Meal meal, int userId);

    void delete(int mealId, int userId) throws NotFoundException;

    Meal get(int mealId, int userId) throws NotFoundException;

    Meal update(Meal meal, int userId) throws NotFoundException;

    List<MealWithExceed> getWithExceed(int userId, int caloriesPerDay);

    List<MealWithExceed> getWithExceedFiltered(int userId, int caloriesPerDay, LocalDate startDate, LocalTime startTime,
                                               LocalDate endDate, LocalTime endTime);
}