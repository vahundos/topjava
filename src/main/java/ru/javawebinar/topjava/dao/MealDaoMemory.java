package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMemory implements MealDao {
    private static final Logger log = LoggerFactory.getLogger(MealDaoMemory.class);
    private static MealDaoMemory instance;
    private static Map<Integer, Meal> mealMap;
    private static AtomicInteger atomicId;

    private MealDaoMemory() {
        mealMap = new ConcurrentHashMap<>();
        atomicId = new AtomicInteger(1);

        addMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        addMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        addMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        addMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        addMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        addMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

        log.debug("MealDaoMemory created");
    }

    public static synchronized MealDaoMemory getInstance() {
        if (instance == null) {
            instance = new MealDaoMemory();
        }
        return instance;
    }

    @Override
    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        int newId = atomicId.getAndIncrement();
        Meal meal = new Meal(newId, dateTime, description, calories);
        mealMap.put(newId, meal);
        log.debug("New meal added with id={}", newId);
    }

    @Override
    public Meal getMealById(int id) {
        log.debug("Get meal with id={}", id);
        if (mealMap.containsKey(id)) {
            log.debug("Memory contains meal with id={}", id);
            return mealMap.get(id);
        }
        log.debug("Can not find meal in memory with id={}", id);
        return null;
    }

    @Override
    public void updateMeal(Meal meal) {
        int id = meal.getId();
        mealMap.put(id, meal);
        log.debug("Meal was added with id={}", id);
    }

    @Override
    public void removeMeal(int id) {
        mealMap.remove(id);
        log.debug("Meal was removed with id={}", id);
    }

    @Override
    public List<Meal> getMealsList() {
        return new ArrayList<>(mealMap.values());
    }
}
