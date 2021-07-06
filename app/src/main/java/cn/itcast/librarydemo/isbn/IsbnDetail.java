package cn.itcast.librarydemo.isbn;
//isbn码图书详情页面
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import cn.itcast.librarydemo.R;
import cn.itcast.librarydemo.util.ParseJson;

public class IsbnDetail extends AppCompatActivity {

    private TextView title2;//书名
//    private TextView rating2;
    private TextView ab2;//图书摘要
    private TextView isbn2;//ISBN
    private TextView jj2;//图书简介
    private TextView zz2;//作者简介
    private ImageView img2;//封面

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
        setContentView(R.layout.activity_isbn_detail);

        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("详情");
        }

        Intent intent = getIntent();

        String title1 = intent.getStringExtra("title");
        String isbn1 = intent.getStringExtra("isbn");
        String ab1 = intent.getStringExtra("ab");
        String jj1 = intent.getStringExtra("jj");
        String zz1 = intent.getStringExtra("zz");
        String url = intent.getStringExtra("url");

        title2 = (TextView) findViewById(R.id.is_name);
        isbn2 = (TextView)findViewById(R.id.is_isbn);
        ab2 = (TextView)findViewById(R.id.is_zaiyao);
        jj2 = (TextView)findViewById(R.id.is_jianjie);
        zz2 = (TextView)findViewById(R.id.is_zuozhe);
        img2 = (ImageView)findViewById(R.id.fengmian);

        ParseJson parseJson = new ParseJson();
        title2.setText(title1);
        isbn2.setText(isbn1);
        ab2.setText(ab1);
        jj2.setText(jj1);
        zz2.setText(zz1);
        Glide.with(this).load(url).into(img2);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {     // 物理键为后退键
            this.finish();                          // 关闭Activity
        }
        return super.onKeyDown(keyCode, event);
    }

}