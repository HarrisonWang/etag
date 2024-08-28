package com.example.etag.annotation.pathinject;

/**
 * 标识可以接收 URL 路径中 ID 参数的 DTO 类的接口。
 * 实现此接口的类将能够自动接收并设置从 URL 路径中提取的 ID 值。
 * 
 * <p>使用方法：
 * <ol>
 *   <li>让您的 DTO 类实现此接口。</li>
 *   <li>在控制器方法中，使用 {@code @InjectId} 注解标记相应的参数。</li>
 *   <li>确保控制器方法的 URL 映射中包含 {@code id} 路径变量。</li>
 * </ol>
 * 
 * <p>示例：
 * <pre>
 * public class UpdatedUser implements PathIdAware {
 *     private Long id;
 *     // 其他字段...
 *     
 *     {@code @Override}
 *     public void setId(Long id) {
 *         this.id = id;
 *     }
 *     // 其他方法...
 * }
 * 
 * {@code @RestController}
 * {@code @RequestMapping("/users")}
 * public class UserController {
 *     {@code @PutMapping("/{id}"}
 *     public ResponseEntity&lt;Void&gt; update({@code @PathVariable} Long id, {@code @InjectId @RequestBody} UpdatedUser dto) {
 *         // 此时 dto 的 id 字段已经被自动设置为路径中的 id 值
 *         // 处理逻辑...
 *     }
 * }
 * </pre>
 */
public interface PathIdAware {

    /**
     * 设置从 URL 路径中提取的 ID 值。
     * 此方法将被框架自动调用，用于注入路径中的 ID。
     *
     * @param id 从 URL 路径中提取的 ID 值
     */
    void setId(Long id);
}