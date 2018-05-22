package tech.ganyaozi.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @author Derek.P.Dai[dp419936514@gmail.com]
 * @date 2017/10/13 11:49
 **/
public class WechatUserInfo {

    private String subscribe;
    private String openid;
    private String nickname;
    private String sex;
    private String city;
    private String country;
    private String province;
    private String language;
    @JSONField(name = "headimgurl")
    private String headImgUrl;
    private Date subscribeTime;
    @JSONField(name = "unionid")
    private String unionId;
    private String remark;
    @JSONField(name = "groupid")
    private String groupId;
    @JSONField(name = "tagidList")
    private String tagIdList;
    @JSONField(name = "errcode")
    private String errCode;
    @JSONField(name = "errmsg")
    private String errMsg;

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(String tagIdList) {
        this.tagIdList = tagIdList;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WechatUserInfo{" +
                "subscribe='" + subscribe + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", language='" + language + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", subscribeTime='" + subscribeTime + '\'' +
                ", unionId='" + unionId + '\'' +
                ", remark='" + remark + '\'' +
                ", groupId='" + groupId + '\'' +
                ", tagIdList='" + tagIdList + '\'' +
                '}';
    }
}
