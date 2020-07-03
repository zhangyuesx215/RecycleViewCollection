package com.york.recycleviewcollection;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.york.baselibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    List<String> list = new ArrayList<>();
    private MainAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
        initRecycle();
    }

    private void initRecycle() {
        adapter = new MainAdapter(list,this);
        recyclerView= findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void initList() {
        list.add("国家区号选择");
    }
}
