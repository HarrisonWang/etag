package com.example.etag.annotation.actionmapping;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class ActionMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        if (info != null) {
            ActionMapping actionMapping = AnnotationUtils.findAnnotation(method, ActionMapping.class);
            if (actionMapping != null) {
                info = RequestMappingInfo.paths(info.getPatternsCondition().getPatterns().toArray(new String[0])[0] + ":" + actionMapping.value())
                        .build()
                        .combine(info);
            }
        }
        return info;
    }
}