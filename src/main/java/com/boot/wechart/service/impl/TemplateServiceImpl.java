package com.boot.wechart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.ApiEum;
import com.boot.wechart.chartenum.MessageTypeEnum;
import com.boot.wechart.entity.Menu;
import com.boot.wechart.entity.template.BaseTemplateMessage;
import com.boot.wechart.entity.template.RefundMessage;
import com.boot.wechart.service.MenuService;
import com.boot.wechart.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 13:43
 */
@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String apiSetIndustry(JSONObject jsonObject) {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.API_SET_INDUSTRY.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        log.info("设置行业请求参数为={}",JSON.toJSONString(jsonObject));
        NutMap nutMap = ctBossSender.sendHttpPost(JSON.toJSONString(jsonObject),accessToken);
        log.info("设置行业返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }

    @Override
    public String getIndustry() {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.GET_INDUSTRY.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        NutMap nutMap = ctBossSender.sendHttpGet(accessToken);
        log.info("获取行业返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }

    @Override
    public String getAllPrivateTemplate() {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.GET_ALL_PRIVATE_TEMPLATE.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        NutMap nutMap = ctBossSender.sendHttpGet(accessToken);
        log.info("获取模板ID返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }

    @Override
    public String sendTemplateMessage(BaseTemplateMessage<RefundMessage> baseTemplateMessage) {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.TEMPLATE_SEND.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        log.info("发送模板消息请求参数为={}",JSON.toJSONString(baseTemplateMessage));
        NutMap nutMap = ctBossSender.sendHttpPost(JSON.toJSONString(baseTemplateMessage),accessToken);
        log.info("发送模板消息返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }
}
