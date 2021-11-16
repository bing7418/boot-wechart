package com.boot.wechart.service.impl;

import com.alibaba.fastjson.JSON;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.ApiEum;
import com.boot.wechart.chartenum.MessageTypeEnum;
import com.boot.wechart.service.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Author bjiang
 * @Description //TODO
 * @Date 2021/7/28 16:32
 * @Version 1.0
 */
@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public String uploadMaterial(File file,String type) {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.MEDIA_UPLOAD_URL.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        log.info("上传素材参数为={}", file.getName());
        NutMap nutMap = ctBossSender.sendFile(file,type,accessToken);
        log.info("上传素材返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }
}
