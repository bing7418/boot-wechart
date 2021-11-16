package com.boot.wechart.entity.template;

import lombok.Data;

/**模板消息实体类
 * @author
 * @version 1.0
 * @date 2020/7/29 17:07
 */
@Data
public class BaseTemplateMessage<T> {
    private String touser;
    private String template_id;
    private String url;
    private T data;
}
