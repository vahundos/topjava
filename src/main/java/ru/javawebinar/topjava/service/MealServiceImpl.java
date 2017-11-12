package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public void delete(int mealId, int userId) throws NotFoundException {
        checkNotFound(repository.delete(mealId, userId), mealId + " for userId " + userId);
    }

    @Override
    public Meal get(int mealId, int userId) throws NotFoundException {
        return checkNotFound(repository.get(mealId, userId), mealId + " for userId " + userId);
    }

    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException {
        return checkNotFound(repository.save(meal, userId), meal + " for userId " + userId);
    }

    @Override
    public List<MealWithExceed> getWithExceed(int userId, int caloriesPerDay) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), caloriesPerDay);
    }

    @Override
    public List<MealWithExceed> getWithExceedFiltered(int userId, int caloriesPerDay, LocalDate startDate,
                                                      LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        return MealsUtil.getWithExceeded(repository.getAllFiltered(userId, startDate, startTime, endDate, endTime),
                caloriesPerDay);
    }
}