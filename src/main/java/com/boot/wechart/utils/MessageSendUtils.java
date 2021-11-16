package com.boot.wechart.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.chartenum.ApiEum;
import com.boot.wechart.entity.req.ReqMessage;
import com.thoughtworks.xstream.XStream;
import org.nutz.lang.util.NutMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/23 15:41
 */
public class MessageSendUtils {
    private static MessageSendUtils instance;
    public static MessageSendUtils me() {
        if (instance == null) {
            instance = new MessageSendUtils();
        }
        return instance;
    }
    /*
     * @Author
     * @Description //拼写回复消息体
     * @Date 15:54 2020/7/23
     * @Param [reqMessage]
     * @return {@link java.lang.String}
     **/
    public  String sendMessage(ReqMessage reqMessage) {
        ReqMessage resMessage = new ReqMessage();
        resMessage.setToUserName(reqMessage.getFromUserName());//回复消息时交换发送人和收件人
        resMessage.setFromUserName(reqMessage.getToUserName());
        resMessage.setMsgType("text");//回复文本格式
        resMessage.setContent(getRespMessage(reqMessage.getContent()));
        resMessage.setCreateTime(System.currentTimeMillis());
        return messageToXml(resMessage);
    }
    public  String sendClickMessage(ReqMessage reqMessage) {
        ReqMessage resMessage = new ReqMessage();
        resMessage.setToUserName(reqMessage.getFromUserName());//回复消息时交换发送人和收件人
        resMessage.setFromUserName(reqMessage.getToUserName());
        resMessage.setMsgType("text");//回复文本格式
        resMessage.setContent("点击跳转成功");
        resMessage.setCreateTime(System.currentTimeMillis());
        return messageToXml(resMessage);
    }
    /*
     * @Author
     * @Description //回复消息，带上网页授权跳转链接
     * @Date 15:54 2020/7/23
     * @Param [reqMessage]
     * @return {@link java.lang.String}
     **/
    public  String sendUrlMessage(ReqMessage reqMessage) {
        ReqMessage resMessage = new ReqMessage();
        resMessage.setToUserName(reqMessage.getFromUserName());//回复消息时交换发送人和收件人
        resMessage.setFromUserName(reqMessage.getToUserName());
        resMessage.setMsgType("text");//回复文本格式
        String message="点击<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxc8e6343ddc936b41&redirect_uri=http://xwxeih.natappfree.cc/wx/getUserCode&response_type=code&scope=snsapi_userinfo#wechat_redirect\">授权</a>登录";
        resMessage.setContent(message);
        resMessage.setCreateTime(System.currentTimeMillis());
        return messageToXml(resMessage);
    }
    public String messageToXml(ReqMessage resMessage){
        XStream xstream  = new XStream();
        xstream.processAnnotations(resMessage.getClass());
        return xstream.toXML(resMessage);
    }
    public String getRespMessage(String message){
        Map<String,String> map=new HashMap<>();
        map.put("key","0b96821f992fade2720ef2a922ca7f6e");
        map.put("question",message);
        String url=OKHttp3Utils3.getUrl(map,ApiEum.ROBBOT_URL.getInfo(),"UTF-8");
        CtBossSender ctBossSender=new CtBossSender();
        JSONObject nutMap=ctBossSender.sendHttpGetRobbot(url);
        return nutMap.getJSONArray("newslist").getJSONObject(0).getString("reply");
    }
}
