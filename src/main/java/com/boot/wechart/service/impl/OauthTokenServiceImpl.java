package com.boot.wechart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.ApiEum;
import com.boot.wechart.chartenum.MessageTypeEnum;
import com.boot.wechart.entity.template.BaseTemplateMessage;
import com.boot.wechart.entity.template.RefundMessage;
import com.boot.wechart.service.OauthTokenService;
import com.boot.wechart.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 13:43
 */
@Service
@Slf4j
public class OauthTokenServiceImpl implements OauthTokenService {
    @Value("${appid}")
    private String appId;
    @Value("${appsecret}")
    private String appSecret;
    /*
     * @Author
     * @Description //获取token
     * @Date 14:13 2020/7/31
     * @Param [code]
     * @return {@link java.lang.String}
     **/
    @Override
    public String getOauthAccessToken(String code) {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.OAUTH2_ACCESS_TOKEN.getInfo(),appId,appSecret,code);
        NutMap nutMap=ctBossSender.sendHttpGet();
        return nutMap.getString("access_token");
    }

    /*
     * @Author
     * @Description //获取用户信息
     * @Date 14:12 2020/7/31
     * @Param [token]
     * @return {@link java.lang.String}
     **/
    @Override
    public String getUserByOpenId(String token) {
        CtBossSender ctBossSender=new CtBossSender();
        StringBuilder url=new StringBuilder(ApiEum.SMS_USER_INFO.getInfo()).append("?access_token=")
                .append(token).append("&openid=").append("oNinWtxbUKhseQjGPFr5NcX9W_-Q&&lang=zh_CN");
        JSONObject jsonObject=ctBossSender.sendHttpGetRobbot(url.toString());
        return jsonObject.toJSONString();
    }
}
