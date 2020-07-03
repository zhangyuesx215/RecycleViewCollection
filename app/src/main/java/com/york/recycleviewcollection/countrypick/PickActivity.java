package com.york.recycleviewcollection.countrypick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.york.baselibrary.base.BaseActivity;
import com.york.recycleviewcollection.R;

import java.util.ArrayList;

public class PickActivity extends BaseActivity {

    private ArrayList<Country> selectedCountries = new ArrayList<>();
    private ArrayList<Country> allCountries = new ArrayList<>();
    private CountryPickerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        initView();
    }


    private void initView() {
        RecyclerView rvPick = (RecyclerView) findViewById(R.id.rv_pick);
        SideBar side = (SideBar) findViewById(R.id.side);
        TextView tvLetter = (TextView) findViewById(R.id.tv_letter);
        allCountries.clear();
        allCountries.addAll(Country.getAll(this, null));
        selectedCountries.clear();
        selectedCountries.addAll(allCountries);
        adapter = new CountryPickerAdapter(selectedCountries);
        adapter.setOnItemClicK(new CountryPickerAdapter.OnItemClicK() {
            @Override
            public void OnTtemClick(int position) {
                Intent data = new Intent();
                data.putExtra("country", position);
                setResult(Activity.RESULT_OK , data);
                finish();
            }
        });
        rvPick.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvPick.setLayoutManager(manager);
        rvPick.setAdapter(adapter);
        rvPick.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        side.addIndex("#", side.indexes.size());
        side.setOnLetterChangeListener(new SideBar.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                tvLetter.setVisibility(View.VISIBLE);
                tvLetter.setText(letter);
                int  position = adapter.getLetterPosition(letter);
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }

            @Override
            public void onReset() {
                tvLetter.setVisibility(View.GONE);
            }
        });
    }

}
