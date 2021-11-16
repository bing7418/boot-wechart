package com.boot.wechart.entity;

import lombok.Data;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 13:26
 */
@Data
public class Button {
    private String type;//菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
    private String name;//菜单标题，不超过16个字节，子菜单不超过40个字节
    private String url;//网页链接，用户点击菜单可打开链接，不超过1024字节。当type为miniprogram时，不支持小程序的老版本客户端将打开本url
    private String key;//菜单KEY值，用于消息接口推送，不超过128字节

    private String appid;//小程序的appid
    private String pagepath;//小程序的页面路径
    private String media_id;//调用新增永久素材接口返回的合法media_id
    private Button[] sub_button;//二级菜单数组，个数应为1~5个

}
