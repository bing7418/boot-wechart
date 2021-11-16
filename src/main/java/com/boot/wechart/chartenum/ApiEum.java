package com.boot.wechart.chartenum;

/**
 * @author
 * @version 1.0
 * @date 2020/7/20 13:12
 */
public enum ApiEum {
    ACCESS_TOKEN_URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"),
    OAUTH2_ACCESS_TOKEN(" https://api.weixin.qq.com/sns/oauth2/access_token"),
    SMS_USER_INFO("https://api.weixin.qq.com/sns/userinfo"),
    //ROBBOT_URL("http://api.tianapi.com/txapi/robot/index"),
    API_SET_INDUSTRY("template/api_set_industry"),
    TEMPLATE_SEND("message/template/send"),//发送模板消息
    GET_ALL_PRIVATE_TEMPLATE("template/get_all_private_template"),
    GET_INDUSTRY("template/get_industry"),
    ROBBOT_URL("http://api.tianapi.com/txapi/robot/index"),
    BASE_URL(" https://api.weixin.qq.com/cgi-bin/"),
    DELETE_PERSONAL_MENU_URL("menu/delconditional"),//删除个性化菜单
    CREATE_PERSONALIZED_MENU_URL("menu/addconditional"),//创建个性化菜单
    MENU_GET_URL("menu/get"),//自定义菜单的查询接口
    MENU_DELETE_URL("menu/delete"),//自定义菜单删除
    MENU_CREATE_URL("menu/create"),//自定义菜单创建
    MEDIA_GETALL_URL("material/batchget_material"),
    MEDIA_UPLOAD_URL("material/add_material");//上传永久素材
    private String info;
    ApiEum(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
}
