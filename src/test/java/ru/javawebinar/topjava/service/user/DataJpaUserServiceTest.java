package ru.javawebinar.topjava.service.user;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles(profiles = Profiles.DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    public void testGetWithMeal() {
        User userWithMeals = service.getWithMeals(UserTestData.USER_ID);
        List<Meal> meals = new ArrayList<>();
        meals.add(MealTestData.MEAL1);
        meals.add(MealTestData.MEAL2);
        meals.add(MealTestData.MEAL3);
        meals.add(MealTestData.MEAL4);
        meals.add(MealTestData.MEAL5);
        meals.add(MealTestData.MEAL6);

        Assertions.assertThat(userWithMeals.getMeals()).usingElementComparatorIgnoringFields("user").isEqualTo(meals);
    }
}
