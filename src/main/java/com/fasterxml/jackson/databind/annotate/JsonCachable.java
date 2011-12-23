package com.fasterxml.jackson.databind.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotation;

/**
 * Marker annotation used to denote whether given instance
 * (currently only used with {@link com.fasterxml.jackson.databind.JsonDeserializer})
 * can be cached.
 *<p>
 * Default action to take in absence of annotation depends
 * on object using annotation; with deserializers default is
 * to assume instances are not cachable.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonCachable
{
    /**
     * Default value is true, giving semantics for parameterless tag instance
     * such that empty instance indicates that instances of annotated class
     * are indeed cachable.
     */
    boolean value() default true;
}
