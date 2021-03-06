/**
 * @Title PostTalk.java 
 * @Package com.std.forum.domain 
 * @Description 
 * @author xieyj  
 * @date 2016年8月28日 下午8:26:17 
 * @version V1.0   
 */
package com.std.forum.domain;

import java.util.Date;

import com.std.forum.dao.base.ABaseDO;

/** 
 * 点赞/收藏/打赏
 * @author: xieyj 
 * @since: 2016年8月28日 下午8:26:17 
 * @history:
 */
public class PostTalk extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 1L;

    // 编号
    private String code;

    // 类型(1 点赞 2 收藏 3 打赏 4 阅读 5 帖子举报 6 评论举报)
    private String type;

    // 帖子编号
    private String postCode;

    // 操作人
    private String talker;

    // 操作时间
    private Date talkDatetime;

    // 帖子信息
    private Post post;

    // 备注
    private String remark;

    // 版块编号
    private String plateCode;

    // ------------db properties------------

    // 昵称
    private String nickname;

    // 帖子名称
    private String postTitle;

    // 帖子内容
    private String postContent;

    // 发布人
    private String publisher;

    // 头像
    private String photo;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getTalker() {
        return talker;
    }

    public void setTalker(String talker) {
        this.talker = talker;
    }

    public Date getTalkDatetime() {
        return talkDatetime;
    }

    public void setTalkDatetime(Date talkDatetime) {
        this.talkDatetime = talkDatetime;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlateCode() {
        return plateCode;
    }

    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
