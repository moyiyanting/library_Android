package cn.itcast.librarydemo.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.itcast.librarydemo.R;
import cn.itcast.librarydemo.Search;
import cn.itcast.librarydemo.adapter.HomeAdapter;
import cn.itcast.librarydemo.ui.Bean.Post;

public class DiscoverFragment extends Fragment {
    private RecyclerView rv;
    private SwipeRefreshLayout stlayout;
    private ImageView write;
    private TextView fx;
    private SearchView sousuo;


    List<Post> data;

    private HomeAdapter homeAdapter;

    private DiscoverViewModel discoverViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();

        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Search.class);//跳转到搜索页面
                startActivity(intent);
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PushComunity.class);
                startActivity(intent);
            }
        });

        stlayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_blue_light);

        stlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Refresh();
            }

            private void Refresh() {
                BmobQuery<Post> Po = new BmobQuery<Post>();
                Po.order("createdAt");
                Po.setLimit(1000);
                Po.findObjects(new FindListener<Post>() {
                    @Override
                    public void done(List<Post> list, BmobException e) {
                        stlayout.setRefreshing(false);
                        if (e == null) {
                            data = new ArrayList<>();
                            data.addAll(list);
                            homeAdapter = new HomeAdapter(getActivity(), data);
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv.setAdapter(homeAdapter);
                        } else {
                            stlayout.setRefreshing(false);
                            Toast.makeText(getActivity(), "获取数据失误", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


    private void initView() {
        rv = getActivity().findViewById(R.id.recyclerview);
        stlayout = getActivity().findViewById(R.id.swipe);
        fx = getActivity().findViewById(R.id.faxian);
        write=getActivity().findViewById(R.id.write);
        sousuo=getActivity().findViewById(R.id.main_title_search);
    }
}
