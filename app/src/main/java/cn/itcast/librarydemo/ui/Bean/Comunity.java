package cn.itcast.librarydemo.ui.Bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;
//社区
public class Comunity extends BmobObject {

    private User author;
    private String nickname;
    private String title;
    private String content;

    private String name, info, onwer;

    private BmobRelation relation;

    private String userOnlyId;


    public String getUserOnlyId() {
        return userOnlyId;
    }

    public void setUserOnlyId(String userOnlyId) {
        this.userOnlyId = userOnlyId;
    }

    public BmobRelation getRelation() {
        return relation;
    }

    public void setRelation(BmobRelation relation) {
        this.relation = relation;
    }

    private String isrelated;  //标识值

    public String getIsrelated() {   //ALT + INS
        return isrelated;
    }

    public void setIsrelated(String isrelated) {
        this.isrelated = isrelated;
    }

    public String getOnwer() {
        return onwer;
    }

    public void setOnwer(String onwer) {
        this.onwer = onwer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}