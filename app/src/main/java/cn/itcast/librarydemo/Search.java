package cn.itcast.librarydemo;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import cn.itcast.librarydemo.pojo.Books;
import cn.itcast.librarydemo.util.AsynNetUtils;
import cn.itcast.librarydemo.adapter.MyAdapter;
import cn.itcast.librarydemo.util.ParseJson;
import cn.itcast.librarydemo.view.BookView;
import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    private TextView tv2;

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
    //定义自动完成的列表
    private final String[] mStrings = {"软件工程","计算机","操作系统","数据库"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("搜索");
        }


        searchView = (SearchView)findViewById(R.id.main_title_search);
        listView = (ListView) findViewById(R.id.lv);
        final ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStrings);
        listView.setAdapter(adapter);
        //为ListView启动过滤
        listView.setTextFilterEnabled(true);
        searchView = (SearchView) findViewById(R.id.sv);
        //设置SearchView自动缩小为图标
        searchView.setIconifiedByDefault(false);//设为true则搜索栏 缩小成俄日一个图标点击展开
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置默认提示文字
        searchView.setQueryHint("输入您想查找的内容");
        //配置监听器
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                //此处添加查询开始后的具体时间和方法
//                https://book.feelyou.top/search/
                String url="https://book.feelyou.top/search/"+ URLEncoder.encode(query);
                AsynNetUtils.get(url, new AsynNetUtils.Callback() {
                    @Override
                    public void onResponse(String response) throws JSONException {
                        List<Books> list = new ArrayList<>();
                        ParseJson parseJson = new ParseJson();
                        try {
                            list=parseJson.json(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        MyAdapter adapter = new MyAdapter(Search.this,R.layout.search_list,list);
                        ListView listView = findViewById(R.id.lv);
                        listView.setAdapter(adapter);
                        final List<Books> finalList = list;
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Books books = finalList.get(position);
                                Intent intent = new Intent(Search.this, BookView.class);
                                intent.putExtra("url",books.getUrl());
                                intent.putExtra("title",books.getTitle());
                                intent.putExtra("ab",books.getAb());
                                intent.putExtra("rating",books.getRating());
                                startActivity(intent);
                            }
                        });
                    }
                });
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //如果newText长度不为0
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }else{
                    listView.setFilterText(newText);
//          adapter.getFilter().filter(newText.toString());//替换成本句后消失黑框！！！
                }
                return true;
            }
        });



        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object string = adapter.getItem(position);
                searchView.setQuery(string.toString(),true);
            }
        });
    }
}


