package com.example.etag.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping
public @interface ApiVersion {

    @AliasFor(annotation = RequestMapping.class, attribute = "produces")
    String[] produces() default "application/vnd.example.etag-v1+json";

    int[] value();
    
}
