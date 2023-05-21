package com.nkslearning.questions;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ArrayList<Object> data = fetchRowData();
        // for List with header
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // for Grid with header
        int spanCount = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,spanCount);
        GridLayoutManager.SpanSizeLookup sizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // Because header takes full width therefore return spanCount for it
                return data.get(position) instanceof String?spanCount:1;
            }
        };
        // for better performance according to android docs
        sizeLookup.setSpanGroupIndexCacheEnabled(true);
        sizeLookup.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(sizeLookup);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(new MyAdapter(data));
    }
    // ready data according to your need
    private ArrayList<Object> fetchRowData() {
        ArrayList<Object> data = new ArrayList<>();
        int h=1,p=1;
        for (int i=0;i<30;i++){
            if (i%5 == 0){
                data.add("Header"+h);
                h++;
            }else {
                data.add(new DataItem("Title"+p));
                p++;
            }
        }
        return data;
    }
}
