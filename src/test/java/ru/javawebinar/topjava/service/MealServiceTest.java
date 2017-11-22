package ru.javawebinar.topjava.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:/db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() throws Exception {
        Meal m = mealService.get(100007, USER_ID);
        assertThat(m).isEqualTo(MEAL_100007);
    }

    @Test(expected = NotFoundException.class)
    public void getNotOwn() throws Exception {
        mealService.get(100007, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(100004, USER_ID);
        assertThat(mealService.getAll(USER_ID)).isEqualTo(MEALS_AFTER_DELETE_100004);
    }

    @Test(expected = NotFoundException.class)
    public void deleteWhatNotExsit() throws Exception {
        mealService.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotOwnMeal() {
        mealService.delete(100004, ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> betweenDateTimes = mealService.getBetweenDateTimes(DATE_TIME_START,
                DATE_TIME_END, USER_ID);
        assertThat(betweenDateTimes).isEqualTo(MEALS_BETWEEN);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = mealService.getAll(USER_ID);
        assertThat(all).isEqualTo(MEALS);
    }

    @Test
    public void update() throws Exception {
        Meal meal = new Meal(MEAL_100007.getId(), MEAL_100007.getDateTime(), "new description", 12345);
        mealService.update(meal, USER_ID);
        assertThat(mealService.get(100007, USER_ID)).isEqualTo(meal);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotOwnMeal() throws Exception {
        Meal meal = new Meal(MEAL_100007.getId(), MEAL_100007.getDateTime(), "new description", 12345);
        mealService.update(meal, ADMIN_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "inserted", 777);
        Meal created = mealService.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertThat(mealService.getAll(USER_ID)).contains(newMeal);
    }

}