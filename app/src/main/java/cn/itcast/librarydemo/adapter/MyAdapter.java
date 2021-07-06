package cn.itcast.librarydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import cn.itcast.librarydemo.R;
import cn.itcast.librarydemo.pojo.Books;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Books> {
    private int resourceId;
    public MyAdapter(@NonNull Context context, int resource, @NonNull List<Books> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }
    @Override
    public View getView(int position,View convertView,  ViewGroup parent) {
        Books books = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.title = view.findViewById(R.id.tv_two);
            viewHolder.ab = view.findViewById(R.id.tv_three);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(books.getTitle());
        viewHolder.ab.setText(books.getAb());
        return view;
    }
    class ViewHolder{
        TextView title;
        TextView ab;
    }
}
