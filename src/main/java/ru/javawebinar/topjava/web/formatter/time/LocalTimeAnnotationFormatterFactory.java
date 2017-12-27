package ru.javawebinar.topjava.web.formatter.time;

import ru.javawebinar.topjava.web.formatter.AbstractDateTimeAnnotationFormatterFactory;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class LocalTimeAnnotationFormatterFactory extends AbstractDateTimeAnnotationFormatterFactory<LocalTimeFormatterAnnotation, LocalTimeFormatter> {

    @Override
    public Set<Class<?>> getFieldTypes() {
        Set<Class<?>> fieldSet = new HashSet<>();
        fieldSet.add(LocalTime.class);
        return fieldSet;
    }

    @Override
    public LocalTimeFormatter getFormatter() {
        return new LocalTimeFormatter();
    }
}
