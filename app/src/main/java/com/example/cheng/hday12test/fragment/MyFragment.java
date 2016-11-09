package com.example.cheng.hday12test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cheng.hday12test.R;

import java.util.ArrayList;

/**
 * Created by a452542253 on 2016/11/8.
 */

public class MyFragment extends Fragment {

    private RecyclerView rcl;
    private ArrayList<String> list = new ArrayList<>();
    private MyAdapter adapter;


    public static MyFragment getInstance(String str) {
        MyFragment mf = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", str);
        mf.setArguments(bundle);
        return mf;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcl = (RecyclerView) view.findViewById(R.id.rcl);
        initData();

        initAdapetr();
    }

    private void initAdapetr() {
        rcl.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new MyAdapter();
        rcl.setAdapter(adapter);
    }

    private void initData() {
        list.clear();
        String str = getArguments().getString("key");

        for (int i = 0; i < 30; i++) {
            list.add(str + "    " + i);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(new TextView(getActivity()));
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
                tv.setPadding(10, 10, 10, 10);
            }
        }
    }
}
