package cn.itcast.librarydemo.ui.recommend;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import org.json.JSONException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.librarydemo.Main2Activity;

import cn.itcast.librarydemo.R;

import cn.itcast.librarydemo.SearchIsbn;
import cn.itcast.librarydemo.adapter.BookAdapter;
import cn.itcast.librarydemo.pojo.Books;
import cn.itcast.librarydemo.util.AsynNetUtils;
import cn.itcast.librarydemo.util.ParseJson;
import cn.itcast.librarydemo.view.BookView;
import cn.itcast.librarydemo.zxing.android.CaptureActivity;

import static android.app.Activity.RESULT_OK;


public class RecommendFragment extends Fragment implements View.OnClickListener{
    private View root;
    private List<Books> list = new ArrayList<>();
    private ListView book_listview;                                                     // 图书列表控件
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10;   // 滑动条按钮控件


    private RecommendViewModel recommendViewModel;

    private Handler handler;
//扫描二维码
    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";
    private static final int REQUEST_CODE_SCAN = 0x0000;
//扫描二维码

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recommendViewModel =
                ViewModelProviders.of(this).get(RecommendViewModel.class);
        root = inflater.inflate(R.layout.fragment_recommend, container, false);
//        final TextView textView = root.findViewById(R.id.text_recommend);

// 图书列表
        if (root != null) {        // 视图不为空
            query("机器学习");
        }

        // 初始化控件
        initView();

        // 列表条目监听
        book_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BookView.class);
                Books books = list.get(position);
                intent.putExtra("url",books.getUrl());          // 封面
                intent.putExtra("title",books.getTitle());      // 书名
                intent.putExtra("ab",books.getAb());            // 简介
                intent.putExtra("rating",books.getRating());    // 评分
                startActivity(intent);                                 // 跳转界面
            }
        });


        //找到
        final ImageButton touxiang=root.findViewById(R.id.title_touxiang);
        ImageButton saoma=root.findViewById(R.id.sao_ma);

        //实现
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Main2Activity.class);//推荐跳到头像个人资料页面去
                startActivity(intent);
            }
        });
        //扫描条形码
        saoma.setOnClickListener(this);

        return root;

    }

    // 初始化控件
    private void initView() {
        book_listview = (ListView) root.findViewById(R.id.lv_book_list);
        btn_1 = (Button) root.findViewById(R.id.btn_1);
        btn_2 = (Button) root.findViewById(R.id.btn_2);
        btn_3 = (Button) root.findViewById(R.id.btn_3);
        btn_4 = (Button) root.findViewById(R.id.btn_4);
        btn_5 = (Button) root.findViewById(R.id.btn_5);
        btn_6 = (Button) root.findViewById(R.id.btn_6);
        btn_7 = (Button) root.findViewById(R.id.btn_7);
        btn_8 = (Button) root.findViewById(R.id.btn_8);
        btn_9 = (Button) root.findViewById(R.id.btn_9);
        btn_10 = (Button) root.findViewById(R.id.btn_10);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_10.setOnClickListener(this);
    }

    // 加载API数据
    private void query(String query) {
        String url = "https://book.feelyou.top/search/" + URLEncoder.encode(query);
        AsynNetUtils.Callback callback = new AsynNetUtils.Callback() {
            @Override
            public void onResponse(String response) throws JSONException {
                ParseJson parseJson = new ParseJson();
                try {
                    list = parseJson.json(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                BookAdapter bookAdapter = new BookAdapter(getActivity(),
                        R.layout.book_recommend_selected_item, list);
                book_listview.setAdapter(bookAdapter);
            }
        };
        AsynNetUtils.get(url, callback);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sao_ma:
                //动态权限申请
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    goScan();
                }
                break;
            case R.id.btn_1:
                query("机器学习");
                break;
            case R.id.btn_2:
                query("深度学习");
                break;
            case R.id.btn_3:
                query("数据库");
                break;
            case R.id.btn_4:
                query("JAVA");
                break;
            case R.id.btn_5:
                query("Android");
                break;
            case R.id.btn_6:
                query("数据结构");
                break;
            case R.id.btn_7:
                query("算法");
                break;
            case R.id.btn_8:
                query("区块链");
                break;
            case R.id.btn_9:
                query("大数据");
                break;
            case R.id.btn_10:
                query("Linux");
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到扫码界面扫码
     */
    private void goScan(){
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE_SCAN);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    goScan();
                } else {
                    Toast.makeText(getActivity(), "你拒绝了权限申请，可能无法打开相机扫码哟！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), SearchIsbn.class);
                //返回的文本内容
                intent1.putExtra("ISBN", data.getStringExtra(DECODED_CONTENT_KEY));
                startActivity(intent1);


                //返回的BitMap图像
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
            }

        }
    }
}
