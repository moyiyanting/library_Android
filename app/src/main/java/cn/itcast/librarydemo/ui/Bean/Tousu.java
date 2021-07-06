package cn.itcast.librarydemo.ui.Bean;

import cn.bmob.v3.BmobObject;


public class Tousu extends BmobObject {

    private String title;
    private String content;
    private User author;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
