package com.github.rin.javaauto;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

/**
 * The {@code NotEmpty} annotation is used to
 * indicate that a field cannot be empty.
 * This annotation can be applied to fields,
 * and it will be retained at runtime.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotEmpty {

    /**
     * The error message to be used if the field is empty.
     *
     * @return the error message
     */
    String message() default "Field cannot be empty";
}
