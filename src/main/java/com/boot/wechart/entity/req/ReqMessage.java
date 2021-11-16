package com.boot.wechart.entity.req;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

/**
 * @author bjiang
 * @version 1.0
 * @date 2020/7/20 15:05
 */
@Data
@XStreamAlias("xml")
public class ReqMessage {
    @XStreamAlias("ToUserName")
    protected String ToUserName;
    @XStreamAlias("FromUserName")
    protected String fromUserName;
    @XStreamAlias("CreateTime")
    protected long createTime;
    @XStreamAlias("MsgType")
    protected String msgType;
    @XStreamAlias("Content")
    private String content;//文本消息内容
    @XStreamAlias("ediaId")
    private String mediaId;//语音消息媒体id，可以调用获取临时素材接口拉取数据。
    @XStreamAlias("Format")
    private String format;//语音格式，如amr，speex等
    @XStreamAlias("MsgId")
    private String msgId;//消息id，64位整型
    @XStreamAlias("MediaID")
    private String mediaID;//语音消息媒体id，可以调用获取临时素材接口拉取该媒体
    @XStreamAlias("Recognition")
    private String recognition;//语音识别结果，UTF8编码
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    @XStreamAlias("Location_X")
    private String location_X;//地理位置维度
    @XStreamAlias("Location_Y")
    private String location_Y;//Location_Y
    @XStreamAlias("Scale")
    private String scale;//地图缩放大小
    @XStreamAlias("Title")
    private String title;//消息标题
    @XStreamAlias("Description")
    private String description;//消息描述
    @XStreamAlias("Url")
    private String url;//消息链接
}
