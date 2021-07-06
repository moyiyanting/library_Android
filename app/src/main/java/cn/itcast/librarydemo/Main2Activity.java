////我的页面
//package cn.itcast.librarydemo;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
//import android.app.AlertDialog;
//import android.content.ContentUris;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.DocumentsContract;
//
//import android.provider.MediaStore;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import java.io.File;
//import java.io.IOException;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class Main2Activity extends AppCompatActivity {
//
//    Button exit;//退出按钮
//
//    CircleImageView tx;//上传头像按钮
//    public Intent data;
//    public static final int CHOOSE_PHOTO = 2;
//
//    //
////
//    @SuppressLint("WrongViewCast")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//        //标题栏有返回按钮
//        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            //设置标题名称
//            actionBar.setTitle("我的");
//        }
//        //找到
//        exit = (Button) findViewById(R.id.bn_exit);
//        tx = (CircleImageView) findViewById(R.id.user_touxiang);
//        //退出实现
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog dialog;//定义对话框
//                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this)
//                        .setMessage("是否确定退出登录？")
//                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {//点击确定时
//                                Intent intent = new Intent(Main2Activity.this, Sign_in.class);
//                                startActivity(intent);
//                                Toast.makeText(Main2Activity.this, "退出登录成功",
//                                        Toast.LENGTH_SHORT).show();//显示
//                            }
//                        })
//                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {//点击取消时
//                                dialog.dismiss();//关闭对话框
//                            }
//                        });
//                dialog = builder.create();//创建对话框
//                dialog.show();//显示对话框
//                return;
//            }
//        });//退出登录
////        上传头像
//        tx.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ContextCompat.checkSelfPermission(Main2Activity.this,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
//                        PackageManager.PERMISSION_GRANTED) {
//                    ActivityCompat.requestPermissions(Main2Activity.this, new String[]{
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//                } else {
//                        openAlbum();
//                    }
//                }
//        });
//
//    }
//    //返回按钮实现
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case android.R.id.home:
//                this.finish();
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    //调用系统相册
//    private void openAlbum() {
//        // 使用Intent来跳转
//        Intent intent = new Intent("android.intent.action.GET_CONTENT");
//        // setType是设置类型，只要系统中满足该类型的应用都会被调用，这里需要的是图片
//        intent.setType("image/*");
//        // 打开满足条件的程序，CHOOSE_PHOTO是一个常量，用于后续进行判断，下面会说
//        startActivityForResult(intent, CHOOSE_PHOTO);
//    }
//    //申请用户权限
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.
//                        PERMISSION_GRANTED) {
//                    // 用户允许授权
//                    openAlbum();
//                } else {
//                    // 用户拒绝授权
//                    Toast.makeText(this, "You denied the permission",
//                            Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//        }
//    }
//
//    //选择图片
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            // 上面的CHOOSE_PHOTO就是在这里用于判断
//            case CHOOSE_PHOTO:
//                // 判断手机系统版本号
//                if (resultCode == RESULT_OK) {
//                    if (Build.VERSION.SDK_INT >= 19) {
//                        // 手机系统在4.4及以上的才能使用这个方法处理图片
//                        handleImageOnKitKat(data);
//                    } else {
//                        // 手机系统在4.4以下的使用这个方法处理图片
//                        handleImageBeforeKitKat(data);
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//    }
//    //选择图片函数
//    @TargetApi(19)
//    private void handleImageOnKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = null;
//        // 如果是document类型的Uri，，则通过document id处理
//        if (DocumentsContract.isDocumentUri(this, uri)) {
//            String docId = DocumentsContract.getDocumentId(uri);
//            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
//                // 解析出数字格式的id
//                String id = docId.split(":")[1];
//                String selection = MediaStore.Images.Media._ID + "=" + id;
//                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        selection);
//            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
//                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
//                        "//downloads/public_downloads"), Long.valueOf(docId));
//                imagePath = getImagePath(contentUri, null);
//            }
//        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
//            // 如果是file类型的Uri，直接获取图片路径
//            imagePath = uri.getPath();
//        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
//            // 如果是content类型的Uri，使用普通方式处理
//            imagePath = getImagePath(uri, null);
//        }
//        // 根据得到的图片路径显示图片
//    }
//    //获取图片路径
//    private String getImagePath(Uri uri,String selection){
//        String path = null;
//        //通过Uri和selection来获取真实的图片路径
//        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
//        if(cursor != null){
//            if(cursor.moveToFirst()){
//                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            }
//            cursor.close();
//        }
//        return path;
//    }
//
//    private void handleImageBeforeKitKat(Intent data) {
//        Uri uri = data.getData();
//        String imagePath = getImagePath(uri, null);
//
//        // 根据得到的图片路径显示图片
//    }
//    //显示图片+压缩图片
//    private void displayImage(String imagePath){
//        if(imagePath!=null){
//            BitmapFactory.Options options = new BitmapFactory.Options();// 解析位图的附加条件
//            options.inJustDecodeBounds = true;    // 不去解析位图，只获取位图头文件信息
//            Bitmap bitmap= BitmapFactory.decodeFile(imagePath,options);
//            tx.setImageBitmap(bitmap);
//            int btwidth = options.outWidth;		// 获取图片的宽度
//            int btheight = options.outHeight;	//获取图片的高度
//
//            int dx = btwidth/100;    // 获取水平方向的缩放比例
//            int dy = btheight/100;    // 获取垂直方向的缩放比例
//
//            int sampleSize = 1;	// 设置默认缩放比例
//
//            // 如果是水平方向
//            if (dx>dy&&dy>1) {
//                sampleSize = dx;
//            }
//
//            //如果是垂直方向
//            if (dy>dx&&dx>1) {
//                sampleSize = dy;
//            }
//            options.inSampleSize = sampleSize;       // 设置图片缩放比例
//            options.inJustDecodeBounds = false;		// 真正解析位图
//            // 把图片的解析条件options在创建的时候带上
//            bitmap = BitmapFactory.decodeFile(imagePath, options);
//            tx.setImageBitmap(bitmap);	// 设置图片
//        }else{
//            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
//        }
//    }
//}
package cn.itcast.librarydemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;

import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class Main2Activity extends AppCompatActivity {

    Button exit;//退出按钮
    CircleImageView tx;//上传头像按钮
    Button ziliao;//个人资料按钮
    Button setting;//修改密码按钮
    Button tousu;//投诉按钮
    public Intent data;
    public static final int CHOOSE_PHOTO = 2;

    //
//
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        ziliao=(Button)findViewById(R.id.bn_ziliao);

        //个人资料
        ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,personal_data.class);
                startActivity(intent);
            }
        });

        //投诉跳转
        tousu=(Button)findViewById(R.id.bn_tousu);

        tousu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,TousuActivity.class);
                startActivity(intent);
            }
        });


        //设置跳转
        setting=(Button)findViewById(R.id.bn_set);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        //标题栏有返回按钮
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置标题名称
            actionBar.setTitle("我的");
        }
        //找到
        exit = (Button) findViewById(R.id.bn_exit);
        tx = (CircleImageView) findViewById(R.id.user_touxiang);  //绑定头像
        //退出实现
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog;//定义对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this)
                        .setMessage("是否确定退出登录？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//点击确定时
                                Intent intent = new Intent(Main2Activity.this, Sign_in.class);
                                startActivity(intent);
                                Toast.makeText(Main2Activity.this, "退出登录成功",
                                        Toast.LENGTH_SHORT).show();//显示
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//点击取消时
                                dialog.dismiss();//关闭对话框
                            }
                        });
                dialog = builder.create();//创建对话框
                dialog.show();//显示对话框
                return;
            }
        });//退出登录
//        上传头像
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Main2Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Main2Activity.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                } else {
                    openAlbum();
                }
            }
        });
        if(!SPUtils.getInstance().getString("urlpath").isEmpty()){    //设置头像
            if (ContextCompat.checkSelfPermission(Main2Activity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(Main2Activity.this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 101);

            } else {
                String url=SPUtils.getInstance().getString("urlpath");
                Bitmap bitmap = getLoacalBitmap(url); //从本地取图片(在cdcard中获取)  //
                tx.setImageBitmap(bitmap);
            }



        }
    }
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

    //调用系统相册
    private void openAlbum() {
        // 使用Intent来跳转
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        // setType是设置类型，只要系统中满足该类型的应用都会被调用，这里需要的是图片
        intent.setType("image/*");
        // 打开满足条件的程序，CHOOSE_PHOTO是一个常量，用于后续进行判断，下面会说
        startActivityForResult(intent, CHOOSE_PHOTO);
    }
    //申请用户权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {
                    // 用户允许授权
                    openAlbum();
                } else {
                    // 用户拒绝授权
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    //选择图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            // 上面的CHOOSE_PHOTO就是在这里用于判断
            case CHOOSE_PHOTO:   //CHOOSE_PHOTO=2
                // 判断手机系统版本号
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 手机系统在4.4及以上的才能使用这个方法处理图片
                        //         handleImageOnKitKat(data);

                        /*-------------------------2020.5.27   lafei-------------------------*/
                        Uri uri = data.getData();
                        //获取bitmap位图
                        Bitmap bitmap2 = PhotoUtils.getBitmapFromUri(uri, this);
                        tx.setImageBitmap(bitmap2);
                        // 把裁剪后的图片保存至本地 返回路径
                        String urlpath = FileUtil.saveFile(this, "crop.jpg", bitmap2);
                        Log.i("TAG", "裁剪图片地址->: " + urlpath);
                        //保存图片地址。
                        SPUtils.getInstance().put("urlpath",urlpath);
                        File file = new File(urlpath);
                        Log.i("TAG", "打印图片文件: " + file);


                    } else {
                        // 手机系统在4.4以下的使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    //选择图片函数
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = null;
        // 如果是document类型的Uri，，则通过document id处理
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                // 解析出数字格式的id
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径
            imagePath = uri.getPath();
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，使用普通方式处理
            imagePath = getImagePath(uri, null);
        }
        // 根据得到的图片路径显示图片
    }
    //获取图片路径
    private String getImagePath(Uri uri,String selection){
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);

        // 根据得到的图片路径显示图片
    }
    //显示图片+压缩图片
    private void displayImage(String imagePath){
        if(imagePath!=null){
            BitmapFactory.Options options = new BitmapFactory.Options();// 解析位图的附加条件
            options.inJustDecodeBounds = true;    // 不去解析位图，只获取位图头文件信息
            Bitmap bitmap= BitmapFactory.decodeFile(imagePath,options);
            tx.setImageBitmap(bitmap);
            int btwidth = options.outWidth;		// 获取图片的宽度
            int btheight = options.outHeight;	//获取图片的高度

            int dx = btwidth/100;    // 获取水平方向的缩放比例
            int dy = btheight/100;    // 获取垂直方向的缩放比例

            int sampleSize = 1;	// 设置默认缩放比例

            // 如果是水平方向
            if (dx>dy&&dy>1) {
                sampleSize = dx;
            }

            //如果是垂直方向
            if (dy>dx&&dx>1) {
                sampleSize = dy;
            }
            options.inSampleSize = sampleSize;       // 设置图片缩放比例
            options.inJustDecodeBounds = false;		// 真正解析位图
            // 把图片的解析条件options在创建的时候带上
            bitmap = BitmapFactory.decodeFile(imagePath, options);
            tx.setImageBitmap(bitmap);	// 设置图片
        }else{
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 加载本地SD卡中的图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}


























