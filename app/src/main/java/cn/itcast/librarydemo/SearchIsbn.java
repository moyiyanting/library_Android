package cn.itcast.librarydemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.itcast.librarydemo.isbn.IsbnDetail;
import cn.itcast.librarydemo.pojo.Books;
import cn.itcast.librarydemo.pojo.Isbns;
import cn.itcast.librarydemo.util.AsynNetUtils;
import cn.itcast.librarydemo.util.ParseJson;
import cn.itcast.librarydemo.view.BookView;

public class SearchIsbn extends AppCompatActivity {
//    private SearchView searchView;
    private TextView isbn;

    //返回按钮实现

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_isbn);

//        startMainActivity();

        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("搜索");
        }


        isbn =(TextView)findViewById(R.id.tvisbn);
        String content = this.getIntent().getExtras().getString("ISBN");
        isbn.setText(content);



        isbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://book.feelyou.top/isbn/" + content;
                AsynNetUtils.get(url, new AsynNetUtils.Callback() {
                    @Override
                    public void onResponse(String response) {

                        List<Isbns> list = new ArrayList<>();
                        ParseJson parseJson = new ParseJson();
                        try {
                            list=parseJson.json1(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        final List<Isbns> finalList = list;
                        Isbns isbns = finalList.get(0);
                        Intent intent = new Intent(SearchIsbn.this, IsbnDetail.class);
                        intent.putExtra("title",isbns.getTitle());
                        intent.putExtra("isbn",isbns.getIsbn());
                        intent.putExtra("ab",isbns.getAb());
                        intent.putExtra("jj",isbns.getJj());
                        intent.putExtra("zz",isbns.getJj());
                        intent.putExtra("url",isbns.getUrl());
                        startActivity(intent);

                    }
                });

            }
        });

        isbn.performClick();//自动点击


//        isbn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = "https://book.feelyou.top/isbn/" + content;
//                AsynNetUtils.get(url, new AsynNetUtils.Callback() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        List<Isbns> list = new ArrayList<>();
//                        ParseJson parseJson = new ParseJson();
//                        try {
//                            list=parseJson.json1(response);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        final List<Isbns> finalList = list;
//                        Isbns isbns = finalList.get(0);
//                        Intent intent = new Intent(SearchIsbn.this, IsbnDetail.class);
//                        intent.putExtra("title",isbns.getTitle());
//                        intent.putExtra("isbn",isbns.getIsbn());
//                        intent.putExtra("ab",isbns.getAb());
//                        intent.putExtra("jj",isbns.getJj());
//                        intent.putExtra("zz",isbns.getJj());
//                        intent.putExtra("url",isbns.getUrl());
//                        startActivity(intent);
//
//                    }
//                });
//            }
//        });










//        isbn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = "https://book.feelyou.top/isbn/" + content;
//                AsynNetUtils.get(url, new AsynNetUtils.Callback() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        List<Isbns> list = new ArrayList<>();
//                        ParseJson parseJson = new ParseJson();
//                        try {
//                            list=parseJson.json1(response);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        final List<Isbns> finalList = list;
//                        Isbns isbns = finalList.get(0);
//                        Intent intent = new Intent(SearchIsbn.this, IsbnDetail.class);
//                        intent.putExtra("title",isbns.getTitle());
//                        intent.putExtra("isbn",isbns.getIsbn());
//                        intent.putExtra("ab",isbns.getAb());
//                        intent.putExtra("jj",isbns.getJj());
//                        intent.putExtra("zz",isbns.getJj());
//                        intent.putExtra("url",isbns.getUrl());
//                        startActivity(intent);
//
//                    }
//                });
//            }
//        });
        return;
    }
}
