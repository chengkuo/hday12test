package ay.qf.com.hday12.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Ref;
import java.util.ArrayList;

import ay.qf.com.hday12.R;

/**
 * Created by samsung on 2016/6/16.
 */
public class ReFragment extends Fragment{

    private RecyclerView rcv;

    private ArrayList<String> list = new ArrayList<>();

    public static ReFragment getInstance(String str) {
        ReFragment mlf = new ReFragment();
        Bundle args = new Bundle();
        args.putString("key",str);
        mlf.setArguments(args);
        return mlf;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_re,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcv = (RecyclerView)view.findViewById(R.id.rcv);
        list.clear();

        for (int i = 0; i < 30; i++) {
            list.add(getArguments().getString("key")+i);
        }

        rcv.setLayoutManager(new LinearLayoutManager(getActivity()));

        rcv.setAdapter(new MyReAdapter());

    }

    public class MyReAdapter extends RecyclerView.Adapter <MyReAdapter.MyHolder> {

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
                tv.setPadding(0,10,0,10);
            }
        }
    }
}
