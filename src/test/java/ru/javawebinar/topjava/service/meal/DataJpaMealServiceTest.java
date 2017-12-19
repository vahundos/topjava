package ru.javawebinar.topjava.service.meal;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

@ActiveProfiles(profiles = Profiles.DATAJPA)
public class DataJpaMealServiceTest extends AbstractMealServiceTest {

    @Test
    public void testGetWithUser() {
        Meal withUser = service.getWithUser(MealTestData.MEAL1_ID);
        Assertions.assertThat(withUser.getUser()).isEqualTo(UserTestData.USER);
    }
}
