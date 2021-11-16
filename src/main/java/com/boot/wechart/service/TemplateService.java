package com.boot.wechart.service;

import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.entity.template.BaseTemplateMessage;
import com.boot.wechart.entity.template.RefundMessage;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 11:50
 */
public interface TemplateService {
    /*
     * @Author bjiang
     * @Description //设置行业
     * @Date 13:43 2020/7/21
     * @Param [menu]
     * @return
     **/
    String apiSetIndustry(JSONObject jsonObject);
    /*
     * @Author bjiang
     * @Description //获取已设置的行业
     * @Date 13:43 2020/7/21
     * @return
     **/
    String getIndustry();
    String getAllPrivateTemplate();
    String sendTemplateMessage(BaseTemplateMessage<RefundMessage> baseTemplateMessage);
}
