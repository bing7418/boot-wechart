package com.boot.wechart.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.boot.wechart.entity.Menu;
import com.boot.wechart.entity.template.BaseTemplateEntity;
import com.boot.wechart.entity.template.BaseTemplateMessage;
import com.boot.wechart.entity.template.RefundMessage;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/21 14:39
 */
public class MenuUtil {
    private static MenuUtil instance;
    public static MenuUtil me() {
        if (instance == null) {
            instance = new MenuUtil();
        }
        return instance;
    }
    /*
     * @Author
     * @Description //获取文档里的Menu示例
     * @Date 14:40 2020/7/21
     * @Param []
     * @return {@link com.boot.wechart.entity.Menu}
     **/
    public Menu getSyStemMenu(){
        String message=" {\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"搜索\",\n" +
                "               \"url\":\"http://www.soso.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"click\",\n" +
                "               \"name\":\"赞一下我们\",\n" +
                "               \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";
        return JSON.parseObject(message, Menu.class);
    }
    public BaseTemplateMessage<RefundMessage> getRefundMessage(){
        String message="{\n" +
                "           \"touser\":\"oNinWtxbUKhseQjGPFr5NcX9W_-Q\",\n" +
                "           \"template_id\":\"Qww0jmJr3OvQsjpyOcemAvr_5PYse2tJaGdJ1ohHcFk\",          \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"尊敬的客人你好\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"storeName\":{\n" +
                "                       \"value\":\"江某人店铺\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"orderId\": {\n" +
                "                       \"value\":\"DD54895\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"orderType\": {\n" +
                "                       \"value\":\"退款\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"欢迎再次购买！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        BaseTemplateMessage<RefundMessage> response=JSON.parseObject(message, new TypeReference<BaseTemplateMessage<RefundMessage>>(){});
        return response;
    }
}
