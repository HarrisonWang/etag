package com.example.etag.annotation.pathinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记需要注入 URL 路径中 ID 的请求体参数的注解。
 * 使用此注解的参数必须实现 {@link com.example.etag.annotation.pathinject.PathIdAware} 接口。
 * 
 * <p>使用方法：
 * <ol>
 *   <li>在控制器方法中，将此注解与 {@code @RequestBody} 一起使用。</li>
 *   <li>确保被注解的参数类型实现了 {@code PathIdAware} 接口。</li>
 *   <li>确保控制器方法的 URL 映射中包含 {@code id} 路径变量。</li>
 * </ol>
 * 
 * <p>示例：
 * <pre>
 * {@code @PutMapping("/{id}"}
 * public ResponseEntity&lt;Void&gt; update({@code @PathVariable} Long id, {@code @InjectId @RequestBody} UpdatedUser dto) {
 *     // 处理逻辑...
 * }
 * </pre>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectId {
}
