package com.boot.wechart.init;

import com.alibaba.fastjson.JSON;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.MessageTypeEnum;
import com.boot.wechart.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @Date 10:49 2020/7/21
 * 项目启动时更新token信息  放入内存中
 */
@Component
@Slf4j
public class InitTokenUtil implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${appid}")
    private String appId;
    @Value("${appsecret}")
    private String appSecret;
    /*
     * @Author bjiang
     * @Description //项目启动时获取token，放入Redis
     * @Date 15:06 2020/7/23
     * @Param [args]
     * @return
     **/
    @Override
    public void run(String... args) throws Exception {
        log.info("项目启动时更新token,appId={},appSecret={}",appId,appSecret);
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
