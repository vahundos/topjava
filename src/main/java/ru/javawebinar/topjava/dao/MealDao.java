package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface MealDao {
    void addMeal(LocalDateTime dateTime, String description, int calories);
    Meal getMealById(int id);
    void updateMeal(Meal meal);
    void removeMeal(int id);
    List<Meal> getMealsList();
}
