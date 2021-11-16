package com.boot.wechart.init;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.MessageTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.util.NutMap;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 定时更新获取token
 * @Author：bjiang
 * @Date 10:49 2020/7/21
 * @Function：
 * @Modified By：
 */

@Component
@Slf4j
public class TokenJob {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${appid}")
    private String appId;
    @Value("${appsecret}")
    private String appSecret;
    /*
     * @Author bjiang
     * @Description //定时任务更新token放入redis
     * @Date 10:49 2020/7/21
     * @Param []
     * @return
     **/
    @Scheduled(cron = "0 0/60 * * * ?")
    public void execute() {
        log.info("定时更新token，每60分钟跑一次,appId={},appSecret={}",appId,appSecret);
        CtBossSender ctBossSender=new CtBossSender(appId,appSecret);
        NutMap nutMap=ctBossSender.sendHttpGet();
        log.info("获取到的Token为={}",JSON.toJSONString(nutMap));
        try {
            redisTemplate.opsForValue().set(MessageTypeEnum.ACCESS_TOKEN.getInfo(),nutMap.getString(MessageTypeEnum.ACCESS_TOKEN.getInfo()),60, TimeUnit.MINUTES);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            log.info("token更新异常....={}",JSON.toJSONString(nutMap));
            return;
        }
        log.info("token更新成功....");
    }
}
