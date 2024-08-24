package com.example.etag.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.etag.annotation.ActionMapping;

import java.lang.reflect.Method;

public abstract class BaseController<T> {

    @PostMapping("/{id}:{action}")
    public T handleAction(@PathVariable Long id, @PathVariable String action) throws Exception {
        Method method = findActionMethod(action);
        if (method != null) {
            return (T) method.invoke(this, id);
        }
        throw new IllegalArgumentException("Unknown action: " + action);
    }

    private Method findActionMethod(String action) {
        Class<?> currentClass = this.getClass();
        while (currentClass != null && currentClass != BaseController.class) {
            for (Method method : currentClass.getDeclaredMethods()) {
                ActionMapping actionMapping = method.getAnnotation(ActionMapping.class);
                if (actionMapping != null && actionMapping.value().equals(action)) {
                    return method;
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return null;
    }
}
