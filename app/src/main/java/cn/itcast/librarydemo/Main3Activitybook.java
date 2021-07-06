package cn.itcast.librarydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.itcast.librarydemo.adapter.Book_Adapter;
import cn.itcast.librarydemo.adapter.Book_inventory;

public class Main3Activitybook extends AppCompatActivity {
    private ListView ListView2;

    private ListView view1;
    private Button button;
    private ArrayList<Book_inventory> stuList = new ArrayList();
    private Book_Adapter adapter;


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
        setContentView(R.layout.activity_main3_activitybook);

        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();


        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("已借");
        }
//        Bmob.initialize(this, "9e7f573ebd7607fe1da9c208e515fd2b");//注册ID
//        insert();
//        queryPage();
//        ListView2 = (ListView) findViewById(R.id.borrow_booked);//初始化控件
//        Book_Adapter rebookadapter = new Book_Adapter (       );
//        ListView2.setAdapter(rebookadapter);
//
//        BmobQuery<Book_inventory> query1 = new BmobQuery<Book_inventory>();
//        query1.order("-createdAt");//时间降序
//        query1.findObjects(new FindListener<Book_inventory>() {
//            @Override
//            public void done(List<Book_inventory> list, BmobException e) {
//
//            }
//
//        });
//
//
//
//    }
//    //添加数据
//    private void insert() {
//        Book_inventory b1 = new Book_inventory();
//        b1.setId("2");
//        b1.setBookname("英语");
//        b1.setInventory(0);
//        b1.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if (e!=null){
//                    show(e.getMessage());
//                }else {
//                    show("success");
//                }
//
//            }
//        });
//
//
//    }
//
//
//    public void show (String msg){
//        Toast.makeText(Main3Activitybook.this,msg,Toast.LENGTH_LONG).show();
//    }
//
//
//
//    //查询所有数据
//    public void queryPage(){
//        BmobQuery<Book_inventory> query = new BmobQuery<Book_inventory>();
//        //查询存在“objectId”字段的数据。
//        //query.addWhereExists("objectId");
//        //获取查询数据
//        query.findObjects(new FindListener<Book_inventory>() {
//            @Override
//            public void done(List<Book_inventory> list, BmobException e) {
//                if (e == null) {
//                    for(int i=0;i< list.size();i++){
//                        Book_inventory s=list.get(i);
//                        Book_Adapter.add(s);
////                        Log.e("TAG","stuList====="+stuList.get(0));
//                        //adapter = new Book_Adapter(Main3Activitybook.this, stuList);
//                        view1.setAdapter(adapter);
//                    }
//
//                }else {
//                    Toast.makeText(Main3Activitybook.this, e.toString(), Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//
    }
}
