package com.boot.wechart.chartenum;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/17 14:37
 */
public enum MessageTypeEnum {
    EVENT_TYPE_TEMPLATESENDJOBFINISH("TEMPLATESENDJOBFINISH"),//事件类型：TEMPLATESENDJOBFINISH(模板消息送达情况提醒)
    ACCESS_TOKEN("access_token"),
    EVENT_TYPE_VIEW("VIEW"),//事件类型：VIEW(自定义菜单URl视图)
    EVENT_TYPE_CLICK("CLICK"),//事件类型：CLICK(点击菜单拉取消息)
    EVENT_TYPE_LOCATION("location"),//事件类型：location(上报地理位置)
    EVENT_TYPE_SCAN("EVENT_TYPE_SCAN"),//事件类型：scan(关注用户扫描带参二维码)
    EVENT_TYPE_UNSUBSCRIBE("unsubscribe"),//事件类型：取消订阅
    EVENT_TYPE_SUBSCRIBE("subscribe"),//事件类型:订阅
    RESP_MESSAGE_TYPE_TEXT ("text"),//返回消息类型：文本
    RESP_MESSAGE_TYPE_IMAGE("image"),//消息返回类型：图片
    RESP_MESSAGE_TYPE_VOICE ("voice"),//消息返回类型:语音
    RESP_MESSAGE_TYPE_MUSIC ("music"),//消息返回类型：音乐
    RESP_MESSAGE_TYPE_NEWS ("news"),//消息返回类型：图文
    RESP_MESSAGE_TYPE_VIDEO("video"),//消息返回类型：视频
    REQ_MESSAGE_TYPE_SHORTVIDEO("shortvideo"),//请求消息类型：小视频
    REQ_MESSAGE_TYPE_EVENT("event"),//请求消息类型：事件推送
    REQ_MESSAGE_TYPE_LINK("link"),//请求消息：类型链接
    REQ_MESSAGE_TYPE_LOCATION("地理位置"),//请求消息类型:地理位置
    REQ_MESSAGE_TYPE_VIDEO("vedio"),//请求消息类型：视频
    REQ_MESSAGE_TYPE_VOICE("voice"),//请求消息类型：语音
    REQ_MESSAGE_TYPE_IMAGE("image"),//请求消息类型：图片
    REQ_MESSAGE_TYPE_TEXT("text");//请求消息类型：文本
    private String info;
    MessageTypeEnum(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
}
