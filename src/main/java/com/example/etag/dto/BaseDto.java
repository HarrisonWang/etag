package com.example.etag.dto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.example.etag.annotation.pathinject.PathIdAware;

public abstract class BaseDto<T> implements PathIdAware {

    private final Class<T> clazz;

    protected BaseDto(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T toEntity() {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            T entity = constructor.newInstance();
            BeanUtils.copyProperties(this, entity);
            return entity;
        } catch (NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error creating entity instance", e);
        }
    }

    public static <D extends BaseDto<T>, T> D fromEntity(T entity, Class<D> dtoClass) {
        try {
            D dto = dtoClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Error creating DTO", e);
        }
    }
}
