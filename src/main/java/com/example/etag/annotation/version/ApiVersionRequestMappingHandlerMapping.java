package com.example.etag.annotation.version;
// package com.example.etag.annotation;

// import java.lang.reflect.Method;

// import org.springframework.core.annotation.AnnotationUtils;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;
// import org.springframework.web.servlet.mvc.condition.RequestCondition;
// import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
// import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

// @Component
// public class ApiVersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

//     private static final String MEDIA_TYPE_PREFIX = "application/vnd.example.etag-v";

//     private static final String MEDIA_TYPE_SUFFIX = "+json";

//     @Override
//     protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
//         ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
//         return createCondition(apiVersion);
//     }

//     @Override
//     protected RequestCondition<?> getCustomMethodCondition(Method method) {
//         ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
//         return createCondition(apiVersion);
//     }

//     private RequestCondition<?> createCondition(ApiVersion apiVersion) {
//         if (apiVersion == null) {
//             return null;
//         }
//         String[] produces = new String[] {MEDIA_TYPE_PREFIX + apiVersion.value() + MEDIA_TYPE_SUFFIX};
//         return new ProducesRequestCondition(produces);
//     }

//     @Override
//     protected RequestMappingInfo createRequestMappingInfo(RequestMapping requestMapping,
//             RequestCondition<?> customCondition) {
//         RequestMappingInfo info = super.createRequestMappingInfo(requestMapping, customCondition);
//         if (customCondition instanceof ProducesRequestCondition) {
//             ProducesRequestCondition producesCondition = (ProducesRequestCondition) customCondition;
//             info = info.combine(RequestMappingInfo
//                                     .paths(info.getPatternsCondition().getPatterns().toArray(new String[0]))
//                                     .produces(producesCondition.getProducibleMediaTypes().toArray(new String[0]))
//                                     .build());
//         }
//         return info;
//     }

// }
