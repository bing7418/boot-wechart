package com.boot.wechart.entity.template;

import lombok.Data;

/**
 * 退款结果通知实体类
 * @author
 * @version 1.0
 * @date 2020/7/29 17:03
 */
@Data
public class RefundMessage {
    private BaseTemplateEntity first;
    private BaseTemplateEntity storeName;
    private BaseTemplateEntity orderId;
    private BaseTemplateEntity orderType;
    private BaseTemplateEntity remark;
}
