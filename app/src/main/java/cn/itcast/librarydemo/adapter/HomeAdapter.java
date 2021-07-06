//发现页面社区的适配器
package cn.itcast.librarydemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.itcast.librarydemo.R;
import cn.itcast.librarydemo.Sign_in;
import cn.itcast.librarydemo.ui.Bean.Post;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Post> data;

    private final int N_TYPE = 0;
    private final int F_TYPE = 1;

    private int Max_num = 15;  //预加载的数据  一共15条

    private Boolean isfootview = true;  //是否有footview


    public HomeAdapter(Context context, List<Post> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ord_item,viewGroup,false);
        View footview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.foot_item,viewGroup,false);
        if (i == F_TYPE){
            return new RecyclerViewHolder(footview,F_TYPE);
        }else {
            return new RecyclerViewHolder(view,N_TYPE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (isfootview && (getItemViewType(i))== F_TYPE){
            //底部加载提示
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
            recyclerViewHolder.Loading.setText("加载中...");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Max_num += 8;
                    notifyDataSetChanged();
                }
            },2000);
        }else {

            //这是ord_item的内容
            final RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) viewHolder;
            final Post post = data.get(i);
//                recyclerViewHolder.username.setText(post.getName());
            recyclerViewHolder.nickname.setText(post.getNickname());
            recyclerViewHolder.title.setText(post.getTitle());
            recyclerViewHolder.content.setText(post.getContent());
            recyclerViewHolder.time.setText(post.getCreatedAt());

           /* recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = recyclerViewHolder.getAdapterPosition();
                    if (BmobUser.getCurrentUser(BmobUser.class) != null){
                        Intent in = new Intent(context,Recive.class);
                        in.putExtra("username",post.getName());
                        in.putExtra("title",post.getTitle());
                        in.putExtra("content",post.getContent());
                        in.putExtra("time",post.getCreatedAt());
                        in.putExtra("user_onlyid",post.getUserOnlyId());
                        in.putExtra("id",data.get(position).getObjectId());
                        context.startActivity(in);
                    }else {
                        Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, Sign_in.class));
                    }
                }
            });*/
        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position == Max_num - 1){
            return F_TYPE;  //底部type
        }else {
            return N_TYPE;
        }
    }

    @Override
    public int getItemCount() {
        if (data.size() < Max_num){
            return data.size();
        }
        return Max_num;
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {

        public TextView nickname,title,content,time; //ord_item的TextView
        public TextView Loading;


        public RecyclerViewHolder(View itemview, int view_type) {
            super(itemview);
            if (view_type == N_TYPE){
//                username = itemview.findViewById(R.id.username);
                nickname = itemview.findViewById(R.id.nickname);
                title = itemview.findViewById(R.id.title);
                content = itemview.findViewById(R.id.content);
                time = itemview.findViewById(R.id.time);
            }else if(view_type == F_TYPE){
                Loading = itemview.findViewById(R.id.footText);
            }
        }
    }
}


