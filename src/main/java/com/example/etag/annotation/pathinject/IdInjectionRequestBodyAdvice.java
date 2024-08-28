package com.example.etag.annotation.pathinject;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 用于自动注入 URL 路径中的 ID 到请求体对象的 {@code RequestBodyAdvice} 实现。
 * 
 * <p>此类负责处理带有 {@link InjectId} 注解的请求参数，并将 URL 路径中的 ID 值
 * 注入到实现了 {@link PathIdAware} 接口的请求体对象中。</p>
 * 
 * <p>使用此类可以自动化 ID 注入过程，减少手动设置 ID 的代码。</p>
 * 
 * <p>示例用法：
 * <pre>
 * {@code @PutMapping("/{id}"}
 * public ResponseEntity&lt;Void&gt; update({@code @PathVariable} Long id, {@code @InjectId @RequestBody} UpdatedUser dto) {
 *     // dto 的 id 字段已经被自动设置为路径中的 id 值
 *     // 处理逻辑...
 * }
 * </pre>
 * 
 * @see InjectId
 * @see PathIdAware
 */
@ControllerAdvice
public class IdInjectionRequestBodyAdvice extends RequestBodyAdviceAdapter {

    private final HttpServletRequest request;

    /**
     * 构造一个新的 IdInjectionRequestBodyAdvice 实例。
     *
     * @param request HTTP 请求对象，用于获取路径变量
     */
    public IdInjectionRequestBodyAdvice(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 判断是否支持当前请求参数的处理。
     *
     * @param methodParameter 方法参数
     * @param targetType 目标类型
     * @param converterType 转换器类型
     * @return 如果参数有 {@link InjectId} 注解且实现了 {@link PathIdAware} 接口，则返回 true
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(InjectId.class) && PathIdAware.class.isAssignableFrom(methodParameter.getParameterType());
    }

    /**
     * 在请求体被读取后执行，用于注入 ID。
     *
     * @param body 读取的请求体对象
     * @param inputMessage HTTP 输入消息
     * @param parameter 方法参数
     * @param targetType 目标类型
     * @param converterType 转换器类型
     * @return 处理后的请求体对象
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof PathIdAware && parameter.hasParameterAnnotation(InjectId.class)) {
            @SuppressWarnings("unchecked")
            Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            if (pathVariables != null && pathVariables.containsKey("id")) {
                Long id = Long.parseLong(pathVariables.get("id"));
                ((PathIdAware) body).setId(id);
            }
        }
        return body;
    }
}