package com.github.rin.javaauto;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * The {@code MinValue} annotation is used to specify
 * the minimum value a field can have.
 * This annotation can be applied to fields,
 * and it will be retained at runtime.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinValue {

    /**
     * The minimum value the annotated field must have.
     *
     * @return the minimum value
     */
    int value();

    /**
     * The error message to be used if the field value
     * is less than the specified minimum value.
     *
     * @return the error message
     */
    String message() default "Field value must be at least ";
}
