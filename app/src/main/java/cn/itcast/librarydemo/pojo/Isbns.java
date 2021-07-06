package cn.itcast.librarydemo.pojo;

public class Isbns {
    String title;
    String ab;
    String isbn;
    String jj;
    String zz;
    String url;

    public Isbns() {
    }

    public Isbns(String title, String ab,String isbn,String jj,String zz,String url) {
        this.title = title;
        this.ab = ab;
        this.isbn = isbn;
        this.jj = jj;
        this.zz = zz;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getJj() {
        return jj;
    }

    public void setJj(String jj) {
        this.jj = jj;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
