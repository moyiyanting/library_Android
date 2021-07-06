package cn.itcast.librarydemo.ui.Bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;
//响应昵称用户发帖信息
public class Post extends BmobObject {

    //上传的对应用户
    private User author;

    //贴子对应的信息
    private String title,content,nickname,name;

    private BmobRelation relation;

    private String isrelated;

    private String userOnlyId;

    public String getUserOnlyId() {
        return userOnlyId;
    }

    public void setUserOnlyId(String userOnlyId) {
        this.userOnlyId = userOnlyId;
    }

    public String getIsrelated() {
        return isrelated;
    }

    public void setIsrelated(String isrelated) {
        this.isrelated = isrelated;
    }

    public BmobRelation getRelation() {
        return relation;
    }

    public void setRelation(BmobRelation relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAuthor() {

        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
