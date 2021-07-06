package cn.itcast.librarydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.itcast.librarydemo.ui.Bean.Tousu;
import cn.itcast.librarydemo.ui.Bean.User;


public class TousuActivity extends Activity {

    private ImageView back;
    private EditText com_title,com_content;
    private Button pushcom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tousu);

        initView();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        pushcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = BmobUser.getCurrentUser(User.class);

                Tousu comunity = new Tousu();
                comunity.setTitle(com_title.getText().toString());
                comunity.setContent(com_content.getText().toString());
                comunity.setAuthor(user);
                comunity.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(TousuActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(TousuActivity.this, "提交失败"+e, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void initView() {
        back = findViewById(R.id.back);
        com_title = findViewById(R.id.com_title);
        com_content = findViewById(R.id.com_content);
        pushcom = findViewById(R.id.pushcom);
    }

}
