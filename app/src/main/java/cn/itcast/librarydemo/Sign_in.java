////登录页面
//package cn.itcast.librarydemo;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import cn.bmob.v3.Bmob;
//import cn.bmob.v3.BmobUser;
//import cn.bmob.v3.exception.BmobException;
//import cn.bmob.v3.listener.SaveListener;
//
//public class Sign_in extends AppCompatActivity {
//    //1.声明控件
//    TextView register;
//    private EditText musername;
//    private EditText muserpassword;
//    Button signin;
//    private TextView forgetpassword;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_in);
//        Bmob.initialize(this, "9e7f573ebd7607fe1da9c208e515fd2b");//注册ID
//        //隐藏标题栏
//        ActionBar actionbar = getSupportActionBar();
//        if(actionbar != null){
//            actionbar.hide();
//        }
//        //2.找到你需要操作的控件  findViewById
//        register=(TextView)findViewById(R.id.user_register);
//        signin=(Button)findViewById(R.id.btn_sign_in);
//        musername =findViewById(R.id.user_sign_id);
//        muserpassword = findViewById(R.id.user_password_a);
//        //3.添加监听器——监听有没有谁触发我  SetXXXListener
//        //4.实现对应接口——触发之后我需要做什么   new XXXListener
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Sign_in.this,Register_in.class);
//                startActivity(intent);
//
//            }
//        });//新用户注册
//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //5.实现逻辑    实现XXX方法
//                String new_username = musername.getText().toString();
//                String new_password = muserpassword.getText().toString();
//                BmobUser user = new BmobUser();
//                user.setUsername(new_username);
//                user.setPassword(new_password);
//                user.login(new SaveListener<BmobUser>() {
//                    @Override
//                    public void done(BmobUser bmobUser, BmobException e) {
//                        if(e == null){
//                            Toast.makeText(Sign_in.this, "登录成功", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Sign_in.this,MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }else {
//                            Toast.makeText(Sign_in.this,"登录失败"+e.getMessage(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
//            }
//        });//登录
//
//    }
//}
package cn.itcast.librarydemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.itcast.librarydemo.model.User;

/**
 * 登录页面
 */
public class Sign_in extends AppCompatActivity {
    //1.声明控件
    TextView register;
    Button signin;
    private EditText user_sign_id,user_password;
    String us_id,us_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        Bmob.initialize(this, "9e7f573ebd7607fe1da9c208e515fd2b");//注册ID
        //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        //2.找到你需要操作的控件  findViewById
        register=(TextView)findViewById(R.id.user_register);
        signin=(Button)findViewById(R.id.btn_sign_in);
        user_sign_id=(EditText) findViewById(R.id.user_sign_id);//账号
        user_password=(EditText) findViewById(R.id.user_password_a);//密码
        //3.添加监听器——监听有没有谁触发我  SetXXXListener
        //4.实现对应接口——触发之后我需要做什么   new XXXListener
        //注册
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_in.this,Register_in.class);
                startActivity(intent);

            }
        });
        //登录
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us_id=user_sign_id.getText().toString().trim();
                String us_pw=user_password.getText().toString().trim();
                if(!us_id.isEmpty()){
                    User user = new User();
                    user.setUsername(us_id);
                    user.setPassword(us_pw);
                    user.login(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if(e == null){
                                Toast.makeText(Sign_in.this, "登录成功", Toast.LENGTH_SHORT).show();
                                SPUtils.getInstance().put("userName",us_id);
                                Intent intent = new Intent(Sign_in.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(Sign_in.this,"登录失败"+e.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(getApplicationContext(), "手机号不能为空！", Toast.LENGTH_SHORT).show();

                }
//                BmobQuery<usera> query = new BmobQuery<usera>();
//                query.getObject("1177d4e2c7", new QueryListener<usera>() {
//
//                            @Override
//                            public void done(usera object, BmobException e) {
//                                if (e == null) {
//                                    //获得playerName的信息
//                                    String str = object.getUsername();
//                                    Log.i("打印登录成功数据", "：" + str);
//                                    //获得数据的objectId信息
//                                    String s = object.getPassword();
//                                    Log.i("打印登录成功数据", "密码：" + s);
//                                    //获得createdAt数据创建时间（注意是：createdAt，不是createAt）
//
//                                    Log.i("打印登录成功数据", "object.getCreatedAt()：" + object.getCreatedAt());
//                                } else {
//                                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
//                                }
//                            }
//                        });

                //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
//                usera p2 = new usera();
//                p2.setPassword("aa123456");
//                p2.update("1177d4e2c7", new UpdateListener() {
//                    @Override
//                    public void done(BmobException e) {
//                        if(e==null){
//                            Log.i("bmob", "成功：" + e.getMessage() + "," + e.getErrorCode());
//                       Toast.makeText(getApplicationContext(), "更新成功:"+p2.getUpdatedAt(), Toast.LENGTH_SHORT).show();
//                        }else{
//                       Toast.makeText(getApplicationContext(), "更新失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                });






            }
        });//登录
    }
}
