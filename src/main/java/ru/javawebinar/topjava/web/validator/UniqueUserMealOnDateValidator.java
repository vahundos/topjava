package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.util.List;

public class UniqueUserMealOnDateValidator implements ConstraintValidator<UniqueUserMealOnDate, Object> {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        /*
        first invoke spring validation, so after that data is valid
        if userRepository null then hibernate validation is invoke
         */
        if (mealRepository == null) {
            return true;
        }
        LocalDateTime dateTime = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue("dateTime");
        Integer mealId = (Integer) new BeanWrapperImpl(value).getPropertyValue("id");
        Integer userId = AuthorizedUser.id();

        List<Meal> between = mealRepository.getBetween(dateTime, dateTime, userId);
        boolean isValid = between.isEmpty() || between.get(0).getId().equals(mealId);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{meal.dateExist}")
                    .addPropertyNode("dateTime")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
