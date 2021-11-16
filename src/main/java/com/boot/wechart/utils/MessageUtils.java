package com.boot.wechart.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.entity.req.ReqMessage;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/20 15:02
 */
@Slf4j
public class MessageUtils {
    private static MessageUtils instance;
    public static MessageUtils me() {
        if (instance == null) {
            instance = new MessageUtils();
        }
        return instance;
    }
    /*
     * @Author bjiang
     * @Description //
     * @Date 15:23 2020/7/23
     * @Param [request]
     * @return {@link java.util.Map<java.lang.String,java.lang.String>}
     **/
    public ReqMessage xmlToMessage(HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        SAXReader reader = new SAXReader();
        try(InputStream in  = request.getInputStream()) {
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                jsonObject.put(element.getName(), element.getText());
            }
        } catch (Exception e) {
            log.info(e.getMessage(),e);
        }
        ReqMessage reqMessage= JSON.parseObject(jsonObject.toJSONString(),ReqMessage.class);
        return reqMessage;
    }
}
