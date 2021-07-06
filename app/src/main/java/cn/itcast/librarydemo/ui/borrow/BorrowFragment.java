package cn.itcast.librarydemo.ui.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import cn.itcast.librarydemo.Main3Activity_rebook;
import cn.itcast.librarydemo.Main3Activitybook;
import cn.itcast.librarydemo.Main3Activitybooking;
import cn.itcast.librarydemo.R;

public class BorrowFragment extends Fragment {

    private BorrowViewModel borrowViewModel;
    private Button button1;
    private Button button2;
    private Button button3;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        borrowViewModel =
                ViewModelProviders.of(this).get(BorrowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_borrow, container, false);



        final Button button1 = root.findViewById(R.id.borrow_item_booked);
        final Button button2 = root.findViewById(R.id.borrow_item_booking);
        final Button button3 = root.findViewById(R.id.borrow_item_return_book);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Main3Activitybook.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Main3Activitybooking.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Main3Activity_rebook.class);
                startActivity(intent);
            }
        });

        final TextView textView = root.findViewById(R.id.text_borrow);
        borrowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }
}