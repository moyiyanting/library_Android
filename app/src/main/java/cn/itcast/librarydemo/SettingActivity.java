package cn.itcast.librarydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.itcast.librarydemo.ui.Bean.User;


public class SettingActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText password1;
    TextView modify;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("修改密码");
        }

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        password1=findViewById(R.id.password1);
        modify=findViewById(R.id.modify);

        User user= BmobUser.getCurrentUser(User.class);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().equals(user.getUsername())){
                    Toast.makeText(SettingActivity.this,"只能修改当前登录账号",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.getText().toString().equals(password1.getText().toString())){
                    Toast.makeText(SettingActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(SettingActivity.this,"更新用户信息成功",Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(SettingActivity.this,"更新用户信息失败",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }
}
