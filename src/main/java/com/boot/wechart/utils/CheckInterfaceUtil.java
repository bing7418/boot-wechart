package com.boot.wechart.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/17 13:32
 */
@Slf4j
public class CheckInterfaceUtil {
    private static CheckInterfaceUtil instance;
    public static CheckInterfaceUtil me() {
        if (instance == null) {
            instance = new CheckInterfaceUtil();
        }
        return instance;
    }
    /*
     * @Author
     * @Description //1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     * @Date 13:34 2020/7/23
     * @Param [signature, timestamp, nonce, token]
     * @return {@link boolean}
     **/
    public boolean checkSignature(String signature, String timestamp, String nonce,String token){
        try {
            String shaToken=dictionarySorting(timestamp,nonce,token);
            if(signature.equals(shaEncode(shaToken))){
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return false;
    }
    /*
     * @Author
     * @Description //字典排序
     * @Date 13:32 2020/7/23
     * @Param [token, timestamp, nonce]
     * @return {@link java.lang.String}
     **/
    public String dictionarySorting(String token,String timestamp,String nonce){
        String[] shaToken = new String[] { token, timestamp, nonce };
        Arrays.sort(shaToken);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < shaToken.length; i++) {
            stringBuilder.append(shaToken[i]);
        }
        return stringBuilder.toString();
    }
    /*
     * @Author
     * @Description //sha1加密算法
     * @Date 13:25 2020/7/23
     * @Param [inStr]
     * @return {@link java.lang.String}
     **/
    public static String shaEncode(String inStr) throws Exception {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (Exception e) {
            log.info(e.getMessage(),e);
            return "";
        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
