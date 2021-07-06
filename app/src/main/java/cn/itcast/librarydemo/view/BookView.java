//图书详情页面
package cn.itcast.librarydemo.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import cn.itcast.librarydemo.R;
import cn.itcast.librarydemo.util.ParseJson;
import com.bumptech.glide.Glide;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BookView extends AppCompatActivity {

    private ImageView img1;
    private TextView title1;
    private TextView rating1;
    private TextView ab1;

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
        setContentView(R.layout.activity_details);

        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("详情");
        }

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("url");
        String ab = intent.getStringExtra("ab");
        String rating = intent.getStringExtra("rating");

        img1 = (ImageView) findViewById(R.id.imgweb);
        title1 = (TextView) findViewById(R.id.bk_name);
        rating1 = (TextView) findViewById(R.id.bk_pingfen);
        ab1 = (TextView) findViewById(R.id.bk_jianjie);

        ParseJson parseJson = new ParseJson();
        try {
            String value = parseJson.jsonRating(rating);
            rating1.setText(value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Glide.with(this).load(url).into(img1);
        title1.setText(title);

        ab1.setText(ab);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {     // 物理键为后退键
            this.finish();                          // 关闭Activity
        }
        return super.onKeyDown(keyCode, event);
    }

}
