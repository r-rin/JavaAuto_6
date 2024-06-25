package com.github.rin.javaauto.validators;

import com.github.rin.javaauto.MinValue;
import com.github.rin.javaauto.NotEmpty;

import java.lang.reflect.Field;

/**
 * The {@code AnnotationValidator} class provides static methods
 * to validate objects based on custom annotations.
 * Currently, supports {@link NotEmpty} and
 * {@link MinValue} annotations for field validation.
 */
public class AnnotationValidator {

    /**
     * Validates an object based on the {@link NotEmpty}
     * and {@link MinValue} annotations.
     *
     * @param obj the object to validate
     * @throws IllegalAccessException if access to a field is denied
     * @throws ValidationException if validation fails
     */
    public static void validate(final Object obj)
            throws IllegalAccessException, ValidationException {
        Class<?> classObj = obj.getClass();
        Field[] fields = classObj.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(NotEmpty.class)) {
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value == null || (value instanceof String
                        && ((String) value).trim().isEmpty())) {
                    NotEmpty annotation = field.getAnnotation(NotEmpty.class);
                    throw new ValidationException(annotation.message()
                            + ": "
                            + field.getName());
                }
            }

            if (field.isAnnotationPresent(MinValue.class)) {
                if (field.getType() != int.class) {
                    throw new ValidationException(
                            "MinValue annotation can only be applied to int: "
                            + field.getName());
                }
                int value = field.getInt(obj);
                MinValue annotation = field.getAnnotation(MinValue.class);
                if (value < annotation.value()) {
                    throw new ValidationException(
                            annotation.message()
                                    + annotation.value()
                                    + ": "
                                    + field.getName());
                }
            }
        }
    }

    public static class ValidationException extends Exception {

        /**
         * Custom exception thrown when validation fails.
         *
         * @param message message to be displayed
         */
        public ValidationException(final String message) {
            super(message);
        }
    }

    /**
     * Private constructor to prevent instantiation of this utility class. ]
     */
    private AnnotationValidator() { }
}
