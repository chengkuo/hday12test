package ay.qf.com.hday12;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

/*
* 需求：
* 1. 在页面上显示ImageView，标题栏，列表，FloatingActionButton
* 2. 实现上下滚动时，让ImageView和标签栏有切换渐变效果
* */
public class TwoActivity extends AppCompatActivity {

    private RecyclerView mRcv;

    private ArrayList<String> list = new ArrayList<>();
    private MyAdapter adapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_two);
        initView();


        
        initData();
        
        initAdapter();
    }

    private void initAdapter() {
        mRcv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter();
        mRcv.setAdapter(adapter);
    }

    private void initData() {

        for (int i = 0; i < 30; i++) {
            list.add("coor item  "+i);
        }
    }

    private void initView() {
        mRcv = (RecyclerView) findViewById(R.id.rcv);
        mToolbar= (Toolbar)findViewById(R.id.toolbar);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(new TextView(TwoActivity.this));
    }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tv.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyHolder extends RecyclerView.ViewHolder{
            TextView tv;

            public MyHolder(View itemView) {
                super(itemView);

                tv = (TextView) itemView;
                tv.setTextSize(25);
                tv.setPadding(0,10,0,10);
            }
        }
    }
}
