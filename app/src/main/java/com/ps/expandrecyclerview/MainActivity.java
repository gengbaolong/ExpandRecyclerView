package com.ps.expandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ExpandBean> mLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, mLists);
        mRecyclerView.setAdapter(recyclerAdapter);
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
            ExpandBean bean = new ExpandBean();
            bean.dueDate = "2018.03.0"+i;
            bean.paymentDue = "107"+i;
            bean.status = "逾期"+i;
            bean.benJin = "100"+i;
            bean.liXi = "7"+i;
            mLists.add(bean);
        }
    }
}
