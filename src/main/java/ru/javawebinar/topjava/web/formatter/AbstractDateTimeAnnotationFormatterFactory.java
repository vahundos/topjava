package ru.javawebinar.topjava.web.formatter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.lang.annotation.Annotation;
import java.util.Set;

public abstract class AbstractDateTimeAnnotationFormatterFactory <T extends Annotation, K extends Formatter>
        implements AnnotationFormatterFactory<T> {

    @Override
    public Printer<?> getPrinter(T annotation, Class<?> fieldType) {
        return getFormatter();
    }

    @Override
    public Parser<?> getParser(T annotation, Class<?> fieldType) {
        return getFormatter();
    }

    public abstract K getFormatter();
}
