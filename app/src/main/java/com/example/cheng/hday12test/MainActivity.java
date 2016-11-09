package com.example.cheng.hday12test;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.cheng.hday12test.fragment.MyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private String[] titles = {"电视剧","电影","综艺","娱乐"};
    private RecyclerView rv;
    private ArrayList<String> list = new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

        initData();

        initAdapter();
    }

    private void initAdapter() {
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        adapter = new MyAdapter();

        rv.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("张海峰是傻逼    " + i  );
        }
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rcl);
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(new TextView(getApplicationContext()));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
                        holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder {
            TextView tv;
            public MyHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView;
                tv.setTextSize(30);
                tv.setPadding(10,10,10,10);
            }
        }
    }
}
