package com.terryyamg.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] ss = {"no.01", "no.02", "no.03","no.04", "no.05", "no.06","no.07", "no.08", "no.09","no.10", "no.11", "no.12"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        MyAdapter adapter = new MyAdapter(ss);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setHasFixedSize(true); //當RecyclerView大小沒改變時最佳化
        rv.setItemAnimator(new DefaultItemAnimator()); //預設動畫效果
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL)); //分隔線
        rv.setAdapter(adapter);
        //監聽事件
        rv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, ss[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private String[] ss;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.tv);
            }
        }

        public MyAdapter(String[] ss) {
            this.ss = ss;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //設定item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //放入資料
            holder.tv.setText(ss[position]);
        }

        @Override
        public int getItemCount() {
            //取得長度
            return ss.length;
        }
    }

}
