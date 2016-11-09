package ay.qf.com.hday12;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.ArrayList;

import ay.qf.com.hday12.fragment.ReFragment;

/*
* 需求:
* 1. 让页面中显示标题栏+TabLayout+ViewPager
* 2. 实现协调滚动效果
* */
public class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mPager;

    private ArrayList<Fragment> list = new ArrayList<>();

    private String[] titles = {"电影","电视剧","综艺","音乐"};
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();

        //处理ViewPager的显示
        initPagerData();

        initPagerAdapter();

        mTablayout.setupWithViewPager(mPager);

    }

    private void initPagerAdapter() {
        adapter = new MyAdapter(getSupportFragmentManager());
        mPager.setAdapter(adapter);

    }

    private void initPagerData() {
        for (int i = 0; i < 4; i++) {
            list.add(ReFragment.getInstance(titles[i]));
        }
    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mPager = (ViewPager) findViewById(R.id.pager);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
