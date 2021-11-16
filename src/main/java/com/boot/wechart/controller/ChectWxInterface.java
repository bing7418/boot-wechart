package com.boot.wechart.controller;

import com.alibaba.fastjson.JSON;
import com.boot.wechart.business.CtBossSender;
import com.boot.wechart.entity.req.ReqMessage;
import com.boot.wechart.service.OauthTokenService;
import com.boot.wechart.utils.CheckInterfaceUtil;
import com.boot.wechart.utils.MessageSendUtils;
import com.boot.wechart.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/17 13:26
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class ChectWxInterface {
    @Autowired
    private OauthTokenService oauthTokenService;
    @Value("${token}")
    private String token;
    @RequestMapping(value="/check")
    @ResponseBody
    public String wxChartInterface(HttpServletRequest request, HttpServletResponse response){
        log.info("验证微信消息开始。。。");
        if(request.getMethod().equals("POST")){//POST请求为请求消息
            weChartInterface2( request,  response);
            return null;
        }
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            //微信加密签名
            String signature=request.getParameter("signature");
            String timestamp=request.getParameter("timestamp");
            String echostr = request.getParameter("echostr");
            String nonce = request.getParameter("nonce");
            log.info("signature={},timestamp={},echostr={},nonce={}",signature,timestamp,echostr,nonce);
            if (CheckInterfaceUtil.me().checkSignature(signature, timestamp, nonce,token)){
                log.info("验证微信消息结束。。。");
                return echostr;
            }
        } catch (UnsupportedEncodingException e) {
            log.error("验证微信消息异常。。。",e);
        }
        log.info("验证微信消息失败。。。");
        return null;
    }
    //@RequestMapping(value="/check",method = RequestMethod.POST)
    public void weChartInterface2(HttpServletRequest request, HttpServletResponse response){
        log.info("接收到公众号消息");
        response.setCharacterEncoding("utf-8");
        //将微信请求xml转为实体对象
        ReqMessage reqMessage = MessageUtils.me().xmlToMessage(request);
        log.info("公众号消息体为={}", JSON.toJSONString(reqMessage));
        String message="";
        if(reqMessage.getContent().equals("点击")){
             message = MessageSendUtils.me().sendUrlMessage(reqMessage);
        }else{
            message = MessageSendUtils.me().sendMessage(reqMessage);
        }
        try(PrintWriter out=response.getWriter()) {
            out.write(message);
            log.error("公众号消息回复成功,message={}",message);
        } catch (IOException e) {
            log.error("公众号消息回复失败");
        }
    }
    /*
     * @Author
     * @Description //接收网页授权消息，获取token和用户信息
     * @Date 14:14 2020/7/31
     * @Param [request, response]
     * @return
     **/
    @RequestMapping(value="/getUserCode")
    public void getUserCode(HttpServletRequest request, HttpServletResponse response){
        log.info("接收到公众号消息");
        response.setCharacterEncoding("utf-8");
        String code=request.getParameter("code");
        String messageToken=oauthTokenService.getOauthAccessToken(code);
        //将微信请求xml转为实体对象
        String userMessage=oauthTokenService.getUserByOpenId(messageToken);
        log.info("获取用户信息为={}", userMessage);
        String message="https://www.baidu.com/";
        try(PrintWriter out=response.getWriter()) {
            out.write(message);
            log.error("公众号消息回复成功,message={}",message);
        } catch (IOException e) {
            log.error("公众号消息回复失败");
        }
    }
}
