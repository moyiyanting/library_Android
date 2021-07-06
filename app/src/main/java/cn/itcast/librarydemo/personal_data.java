//个人资料
package cn.itcast.librarydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.itcast.librarydemo.model.User;

/**
 *
 */
public class personal_data extends AppCompatActivity {
    private EditText ed_name,ed_zh,ed_cz,ed_school,ed_age,ed_info;
    private TextView tv_ok;
    private String ObjectId="";

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
        setContentView(R.layout.activity_personal_data);
        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("个人资料");
        }
        ed_name = (EditText) findViewById(R.id.ed_name);//名字
        ed_zh = (EditText) findViewById(R.id.ed_zh);//账号
        ed_cz = (EditText) findViewById(R.id.ed_cz);//常住地
        ed_school = (EditText) findViewById(R.id.ed_school);//学校
        ed_age = (EditText) findViewById(R.id.ed_age);//生日
        ed_info = (EditText) findViewById(R.id.ed_info);//个性签名
        tv_ok = (TextView) findViewById(R.id.tv_ok);//确定


        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereEqualTo("username", SPUtils.getInstance().getString("userName"));
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if(e==null){
                    ed_name.setHint(object.get(0).getUsername());
                    ed_zh.setHint(object.get(0).getUser_account());
                    ed_cz.setHint(object.get(0).getUser_address());
                    ed_school.setHint(object.get(0).getUser_school());
                    ed_age.setHint(object.get(0).getUser_age());
                    ed_info.setHint(object.get(0).getInfo());
                }else{
                    Toast.makeText(getApplicationContext(),"更新用户信息失败：" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ed_name.getText().toString().trim().isEmpty()){    //判断名字是否为空

                    if(!ed_zh.getText().toString().trim().isEmpty()){
                        if(!ed_cz.getText().toString().trim().isEmpty()){
                            if(!ed_school.getText().toString().trim().isEmpty()){
                                if(!ed_age.getText().toString().trim().isEmpty()){
                                    if(!ed_info.getText().toString().trim().isEmpty()){
//                                        SPUtils.getInstance().put("ed_name",ed_name.getText().toString().trim());//保存名字
//                                        SPUtils.getInstance().put("ed_zh",ed_zh.getText().toString().trim());//保存账号
//                                        SPUtils.getInstance().put("ed_cz",ed_cz.getText().toString().trim());//保存常住地
//                                        SPUtils.getInstance().put("ed_school",ed_school.getText().toString().trim());//保存学校
//                                        SPUtils.getInstance().put("ed_age",ed_age.getText().toString().trim());//保存生日
//                                        SPUtils.getInstance().put("ed_info",ed_info.getText().toString().trim());//保存个性签名

                                        //更新Person表里面id为6b6c11c537的数据，个人资料内容更新为“”
                                        User p2 = new User();
                                        p2.setUsername(ed_name.getText().toString().trim());
                                        p2.setUser_account(ed_zh.getText().toString().trim());
                                        p2.setUser_address(ed_cz.getText().toString().trim());
                                        p2.setUser_school(ed_school.getText().toString().trim());
                                        p2.setUser_age(ed_age.getText().toString().trim());
                                        p2.setInfo(ed_info.getText().toString().trim());
                                        BmobUser bmobUser = BmobUser.getCurrentUser(User.class);
                                        p2.update(bmobUser.getObjectId(),new UpdateListener() {
                                            @Override
                                            public void done(BmobException e) {
                                                if(e==null){
                                                    Toast.makeText(getApplicationContext(),"个人资料修改成功！",Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }else{
                                                    Toast.makeText(getApplicationContext(), "更新失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    Log.i("bmob", "成功：" + e.getMessage() + "," + e.getErrorCode()+e.toString());
                                                }
                                            }
                                        });

                                    } else {
                                        Toast.makeText(getApplicationContext(),"个性签名不能为空！",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(),"生日不能为空！",Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),"学校不能为空！",Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"常住地不能为空！",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"账号不能为空！",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"名字不能为空！",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

}
