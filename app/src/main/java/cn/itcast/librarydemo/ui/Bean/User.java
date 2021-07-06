package cn.itcast.librarydemo.ui.Bean;
//发现页的用户昵称类
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

public class User extends BmobUser {

    private String nickname;

    private String gender;

    //关注的人数
    private Integer focusId_sum = 0;

    //private user_followers follower_id;

   /*public user_followers getFollower_id() {
        return follower_id;
    }*/

   /* public void setFollower_id(user_followers follower_id) {
        this.follower_id = follower_id;
    }*/

    public Integer getFocusId_sum() {
        return focusId_sum;
    }

    public void setFocusId_sum(Integer focusId_sum) {
        this.focusId_sum = focusId_sum;
    }

    public BmobRelation getFocuId() {
        return focuId;
    }

    public void setFocuId(BmobRelation focuId) {
        this.focuId = focuId;
    }

    private BmobRelation focuId;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;

    }
}