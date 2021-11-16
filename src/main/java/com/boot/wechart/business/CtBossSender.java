package com.boot.wechart.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boot.wechart.chartenum.ApiEum;
import com.boot.wechart.utils.OKHttp3Utils3;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.nutz.lang.util.NutMap;

import java.io.File;
import java.io.IOException;

/**
 * @Author：bjiang
 * @Date：Create in 2029/7/20 15:42
 * @Name：
 * @Function：
 * @Modified By：
 */
@Slf4j
public class CtBossSender {
    private String url;
    private OkHttpClient httpRequestClient;
    /*
     * @Author bjiang
     * @Description //token url初始化
     * @Date 10:29 2020/7/21
     * @Param [appid, secret]
     * @return {@link null}
     **/
    public CtBossSender(String appid, String secret) {
        StringBuilder urls=new StringBuilder(ApiEum.ACCESS_TOKEN_URL.getInfo()).append("&appid=").append(appid).append("&secret=").append(secret);
        this.url =urls.toString() ;
        httpRequestClient = OKHttp3Utils3.getHttpClient(60);
    }
    /*
     * @Author bjiang
     * @Description // url初始化
     * @Date 10:29 2020/7/21
     * @Param [appid, secret]
     * @return {@link null}
     **/
    public CtBossSender(String url,String appid, String secret,String code) {
        StringBuilder urls=new StringBuilder(url).append("?appid=").append(appid).append("&secret=").append(secret).append("&code=").append(code).append("&grant_type=authorization_code");
        this.url =urls.toString() ;
        httpRequestClient = OKHttp3Utils3.getHttpClient(60);
    }
    /*
     * @Author bjiang
     * @Description //请求微信公众号接口方法的初始化
     * @Date 10:31 2020/7/21
     * @Param [function]
     * @return {@link null}
     **/
    public CtBossSender(String function) {
        StringBuilder urls=new StringBuilder(ApiEum.BASE_URL.getInfo()).append(function);
        this.url = urls.toString();
        httpRequestClient = new OkHttpClient();
    }
    public CtBossSender() {
        httpRequestClient = new OkHttpClient();
    }
    /*
     * @Author bjiang
     * @Description //url+token
     * @Date 10:32 2020/7/21
     * @Param [token]
     * @return
     **/
    public void init(String token) {
        StringBuilder reqUrl = new StringBuilder(this.url).append("?");
        reqUrl.append("&access_token=").append(token);
        this.url = reqUrl.toString();
    }
    /**
     * 发送GET请求获取token
     *
     * @return
     * @throws IOException
     */
    public NutMap sendHttpGet(){
        log.info("发送请求的url：url={}",url);
        Response response = null;
        NutMap responseBody =null;
        try {
            response = OKHttp3Utils3.get(url);
            responseBody= JSON.parseObject(response.body().string(),NutMap.class);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        log.info("Send iot request end... responseBody={}", JSON.toJSONString(responseBody));
        return responseBody;
    }
    /**
     * 发送GET请求获取数据
     *
     * @return
     * @throws IOException
     */
    public NutMap sendHttpGet(String token){
        init(token);
        log.info("发送请求的url：url={}",url);
        Response response = null;
        NutMap responseBody =null;
        try {
            response = OKHttp3Utils3.get(url);
            responseBody= JSON.parseObject(response.body().string(),NutMap.class);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        log.info("Send iot request end... responseBody={}", JSON.toJSONString(responseBody));
        return responseBody;
    }
    /**
     * 发送POST请求获取数据
     *
     * @return
     * @throws IOException
     */
    public NutMap sendHttpPost(String requestBody,String token){
        init(token);
        log.info("发送请求的url：url={}",url);
        NutMap response = JSON.parseObject(OKHttp3Utils3.post(url,requestBody),NutMap.class);

        log.info("Send iot request end... responseBody={}", JSON.toJSONString(response));
        return response;
    }
    /**
     * 发送POST请求获取数据
     *
     * @return
     * @throws IOException
     */
    public NutMap sendFile(File file, String type, String token){
        init(token,type);
        log.info("发送请求的url：url={}",url);
        NutMap response = JSON.parseObject(OKHttp3Utils3.sendFormByPost(url,file),NutMap.class);

        log.info("Send iot request end... responseBody={}", JSON.toJSONString(response));
        return response;
    }

    private void init(String token, String type) {
        StringBuilder reqUrl = new StringBuilder(this.url).append("?");
        reqUrl.append("&access_token=").append(token);
        reqUrl.append("&type=").append(type);
        this.url = reqUrl.toString();
    }

    /**
     * 发送POST请求获取数据
     *
     * @return
     * @throws IOException
     */
    public NutMap sendHttpPostByRobbot(String requestBody){
        log.info("发送请求的url：url={}",url);
        NutMap response = JSON.parseObject(OKHttp3Utils3.post(url,requestBody),NutMap.class);

        log.info("Send iot request end... responseBody={}", JSON.toJSONString(response));
        return response;
    }
    /**
     * 发送GET请求获取数据
     *
     * @return
     * @throws IOException
     */
    public JSONObject sendHttpGetRobbot(String robbotUrl){
        log.info("发送请求的url：url={}",robbotUrl);
        Response response = null;
        JSONObject responseBody =null;
        try {
            response = OKHttp3Utils3.get(robbotUrl);
            responseBody= JSON.parseObject(response.body().string());
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        log.info("Send iot request end... responseBody={}", JSON.toJSONString(responseBody));
        return responseBody;
    }
}
