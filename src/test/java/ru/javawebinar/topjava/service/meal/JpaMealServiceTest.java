package ru.javawebinar.topjava.service.meal;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

@ActiveProfiles(profiles = Profiles.JPA)
public class JpaMealServiceTest extends AbstractMealServiceTest {
}
