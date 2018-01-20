package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        /*
        first invoke spring validation, so after that data is valid
        if userRepository null then hibernate validation is invoke
         */
        if (userRepository == null) {
            return true;
        }
        String email = (String) new BeanWrapperImpl(value).getPropertyValue("email");
        Integer id = ((Integer) new BeanWrapperImpl(value).getPropertyValue("id"));
        if (id == null) {
            id = AuthorizedUser.id();
        }

        User userByEmail = userRepository.getByEmail(email);
        boolean isValid = userByEmail == null || userByEmail.getId().equals(id);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{user.emailExist}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
