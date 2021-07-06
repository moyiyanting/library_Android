package cn.itcast.librarydemo.util;

import cn.itcast.librarydemo.pojo.Books;
import cn.itcast.librarydemo.pojo.Isbns;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/*
 * 使用方法：
 * 1.先定义ParseJson类（无参构造）
 * 2.调用对应的解析方法（将api获取的数据传入即可），这里的解析方法我只写了我用的，想要解析其它属于，拷贝一下方法修改一下就可以了（方法名也需要修改）
 * */

public class ParseJson {
    public List<Books> json(String s) throws JSONException {
        JSONArray jsa = new JSONArray(s);
        String title;
        String ab;
        String url;
        String rating;
        Books book;
        List<Books> list = new ArrayList<>();
        for (int i = 0; i < jsa.length();i++) {
            JSONObject jso = jsa.getJSONObject(i);
            title = jso.optString("title");//书名
            ab = jso.optString("abstract");//简介
            url = jso.optString("cover_url");
            rating = jso.optString("rating");
            book = new Books(title, ab,url,rating);
            list.add(book);
        }
        return list;
    }
//解析Isbns对象
    public List<Isbns> json1(String s1) throws JSONException {
        JSONObject jso = new JSONObject(s1);
        List<Isbns> list = new ArrayList<>();
        String title = jso.optString("title");//书名
        String ab = jso.optString("abstract");//图书摘要
        String isbn = jso.optString("isbn");//ISBN
        String jj = jso.optString("book_intro");//图书简介
        String zz = jso.optString("author_intro");//作者简介
        String url = jso.optString("cover_url");//图书封面
        Isbns isbns = new Isbns(title, ab,isbn,jj,zz,url);
        list.add(isbns);
        return list;
    }



    public String jsonRating(String s) throws JSONException {
        JSONObject jso = new JSONObject(s);
        String value = jso.optString("value");

        return value;
    }
}
