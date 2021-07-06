//注册页面
package cn.itcast.librarydemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register_in extends AppCompatActivity {
    private TextView sign;
    private EditText mphonenumber;
    private EditText musername;
    private EditText muserpassword;
    private Button mregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_in);
        //初始化BmobSDK
        Bmob.initialize(this, "9e7f573ebd7607fe1da9c208e515fd2b");//注册ID
        init();
        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        sign=(TextView)findViewById(R.id.user_sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_in.this,Sign_in.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        mphonenumber = findViewById(R.id.user_phonenumber);
        musername = findViewById(R.id.user_name_id);
        muserpassword = findViewById(R.id.user_password);
        mregister = findViewById(R.id.btn_register_in);
        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户输入的账号和密码
                String new_phonenumber = mphonenumber.getText().toString();
                String new_username = musername.getText().toString();
                String new_password = muserpassword.getText().toString();
                BmobUser user = new BmobUser();
                user.setMobilePhoneNumber(new_phonenumber);
                user.setUsername(new_username);
                user.setPassword(new_password);
                //注册
                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e == null){
                            Toast.makeText(Register_in.this,"注册成功",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Register_in.this,"注册失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
