package ru.javawebinar.topjava.web.formatter.date;

import ru.javawebinar.topjava.web.formatter.AbstractDateTimeAnnotationFormatterFactory;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class LocalDateAnnotationFormatterFactory extends AbstractDateTimeAnnotationFormatterFactory<LocalDateFormatterAnnotation, LocalDateFormatter> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldSet = new HashSet<>();
        fieldSet.add(LocalDate.class);
        return fieldSet;
    }

    @Override
    public LocalDateFormatter getFormatter() {
        return new LocalDateFormatter();
    }
}
