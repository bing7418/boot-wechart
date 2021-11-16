package com.boot.wechart.controller.template;

import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.entity.template.BaseTemplateMessage;
import com.boot.wechart.entity.template.RefundMessage;
import com.boot.wechart.service.TemplateService;
import com.boot.wechart.utils.MenuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/29 13:59
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;
    @RequestMapping("/apiSetIndustry")
    public String apiSetIndustry(@RequestBody JSONObject requestBody){
        return templateService.apiSetIndustry(requestBody);
    }
    @RequestMapping("/getIndustry")
    public String getIndustry(){
        return templateService.getIndustry();
    }
    @RequestMapping("/getAllPrivateTemplate")
    public String getAllPrivateTemplate(){
        return templateService.getAllPrivateTemplate();
    }
    @RequestMapping("/sendTemplateMessage")
    public String sendTemplateMessage(){
        BaseTemplateMessage<RefundMessage> response=MenuUtil.me().getRefundMessage();
        return templateService.sendTemplateMessage(response);
    }
}
