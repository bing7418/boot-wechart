package com.boot.wechart.service;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/31 11:16
 */
public interface OauthTokenService {
    String getOauthAccessToken(String code);
    String getUserByOpenId(String token);
}
