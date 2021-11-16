package com.boot.wechart.service.impl;

import com.alibaba.fastjson.JSON;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.ApiEum;
import com.boot.wechart.chartenum.MessageTypeEnum;
import com.boot.wechart.entity.Button;
import com.boot.wechart.entity.Menu;
import com.boot.wechart.service.MenuService;
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
public class MenuServiceImpl implements MenuService {
    @Autowired
    private StringRedisTemplate redisTemplate;
    /*
     * @Author
     * @Description //创建菜单
     * @Date 13:44 2020/7/21
     * @Param
     * @return {@link null}
     **/
    @Override
    public String creatMenu(Menu menu) {
        //发起POST请求创建菜单
        CtBossSender ctBossSender=new CtBossSender(ApiEum.MENU_CREATE_URL.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        log.info("创建菜单请求参数为={}",JSON.toJSONString(menu));
        NutMap nutMap = ctBossSender.sendHttpPost(JSON.toJSONString(menu),accessToken);
        log.info("创建菜单返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }

    @Override
    public String deleteMenu() {
        //发起GET请求删除菜单
        CtBossSender ctBossSender=new CtBossSender(ApiEum.MENU_DELETE_URL.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        NutMap nutMap = ctBossSender.sendHttpGet(accessToken);
        log.info("删除菜单返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }

    @Override
    public String getMenu() {
        CtBossSender ctBossSender=new CtBossSender(ApiEum.MENU_GET_URL.getInfo());        //获取菜单结构
        String accessToken=redisTemplate.opsForValue().get(MessageTypeEnum.ACCESS_TOKEN.getInfo());
        NutMap nutMap = ctBossSender.sendHttpGet(accessToken);
        log.info("获取菜单返回参数为={}",JSON.toJSONString(nutMap));
        return JSON.toJSONString(nutMap);
    }
}
