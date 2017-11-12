package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(MealRestController.class);
    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal, AuthorizedUser.id());
    }

    public void delete(int mealId) {
        int userId = AuthorizedUser.id();
        log.info("delete mealid={}, userId={}", mealId, userId);
        service.delete(mealId, userId);
    }

    public Meal get(int mealId) {
        int userId = AuthorizedUser.id();
        log.info("get mealId={}, userId={}", mealId, userId);
        return service.get(mealId, userId);
    }

    public Meal update(Meal meal, int mealId) {
        int userId = AuthorizedUser.id();
        log.info("update meal={}, mealId={}, userId={}", meal, meal, userId);
        assureIdConsistent(meal, mealId);
        return service.update(meal, userId);
    }

    public List<MealWithExceed> getWithExceed() {
        int userId = AuthorizedUser.id();
        log.info("get with exceed userId={}", userId);
        return service.getWithExceed(userId, AuthorizedUser.getCaloriesPerDay());
    }
}
