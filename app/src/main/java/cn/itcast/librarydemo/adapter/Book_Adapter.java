package cn.itcast.librarydemo.adapter;

import android.app.Person;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import cn.itcast.librarydemo.adapter.Book_inventory;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.itcast.librarydemo.Main3Activity_rebook;
import cn.itcast.librarydemo.R;


import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
//适配器
public class Book_Adapter extends BaseAdapter {
    private Context context;
    private List<Book_inventory> list;

    public Book_Adapter() {
        this.context = context;
        this.list = list;

    }

    public Book_Adapter(Main3Activity_rebook main3Activity_rebook, ArrayList<Book_inventory> stuList) {
    }

    public static void add(Book_inventory s) {
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        Bmob.initialize(context, "9e7f573ebd7607fe1da9c208e515fd2b");//注册ID
        if (view == null) {
//            String names;
//            names = (String) list.get(position).getName();
//            LayoutInflater inflater =LayoutInflater.from(context);
//            view =inflater.inflate(R.layout.borrow_item,null);//
//            TextView textView = view.findViewById(R.id.borrow_title1);
//            textView.setText(names);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.headImage = view.findViewById(R.id.book_image);
            viewHolder.id = view.findViewById(R.id.borrow_intro);
            viewHolder.bookName = view.findViewById(R.id.borrow_title1);

            view.setTag(viewHolder);

        }

        Book_inventory s = list.get(i);
        ViewHolder viewHolder = (ViewHolder) view.getTag();

//        headImage.setImageResource(s.getHead());
//        String url=s.getHeadImage().getUrl();
        Glide.with(context).load(s.getHeadImage().getFileUrl()).into(viewHolder.headImage);

        viewHolder.id.setText(s.getId());
        viewHolder.bookName.setText(s.getBookname());


        viewHolder.headImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book_inventory s = new Book_inventory();
//              stuData.get(i).getObjectId();
                s.setObjectId(list.get(i).getObjectId());
                s.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {

                        if (e == null) {
                            list.remove(i);
                            Book_Adapter.this.notifyDataSetChanged();
                        }
                    }
                });

            }
        });


        return view;

    }

    public void changeData(List<Book_inventory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView headImage;
        TextView id;
        TextView bookName;

    }
}