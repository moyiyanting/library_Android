package cn.itcast.librarydemo.pojo;

public class Books {
    String title;
    String ab;
    String url;
    String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Books(String title, String ab, String url, String rating) {
        this.title = title;
        this.ab = ab;
        this.url = url;
        this.rating = rating;
    }

    public Books() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

}
