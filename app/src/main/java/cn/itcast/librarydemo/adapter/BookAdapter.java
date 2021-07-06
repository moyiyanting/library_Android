package cn.itcast.librarydemo.adapter;
//推荐碎片的listview的item适配器
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.List;

import cn.itcast.librarydemo.R;
import cn.itcast.librarydemo.pojo.Books;
import cn.itcast.librarydemo.util.ParseJson;

public class BookAdapter extends ArrayAdapter {
    private final int resourId;                                           // Item界面

    public BookAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        resourId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;                           // 缓存的Item对象
        Books books = (Books) getItem(position);                // 获取当前项的图书实例
        if (convertView == null) {                              // 缓存Item对象为空，加载控件
            convertView = LayoutInflater.from(getContext()).inflate(resourId, null);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) convertView.findViewById(R.id.iv_book_image);
            viewHolder.name= (TextView) convertView.findViewById(R.id.tv_book_name);
            viewHolder.score = (TextView) convertView.findViewById(R.id.tv_book_score);
            convertView.setTag(viewHolder);                     // 将对象viewHolder添加进缓存convertView对象
        } else {                                                // 缓存Item对象不为空
            viewHolder = (ViewHolder) convertView.getTag();     // 获取缓存在convertView对象中的viewHolder类对象
        }
        // 设置数据
        String url = books.getUrl();
        Glide.with(getContext()).load(url).into(viewHolder.image);      // 加载封面
        viewHolder.name.setText(books.getTitle());                      // 加载书名
        String rating = books.getRating();                              // 获取评分
        // 解析评分数据
        ParseJson parseJson = new ParseJson();
        try {
            String value = parseJson.jsonRating(rating);
            viewHolder.score.setText("评分：" + value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}

class ViewHolder {
    public ImageView image;         // 图书封面
    public TextView name;           // 图书书名
    public TextView score;          // 图书评分
}

