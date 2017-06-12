package com.example.mushi.advertapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Advertisment> advertisment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        advertisment = new ArrayList<>();
        advertisment.add(new Advertisment("Qmobile","2500",R.drawable.ic_color_lens_black_24dp,"Karachi"));
        advertisment.add(new Advertisment("Mushahid","25",R.drawable.ic_color_lens_black_24dp,"Lahore"));
        advertisment.add(new Advertisment("Mushahid","25",R.drawable.ic_color_lens_black_24dp,"Multan"));
        advertisment.add(new Advertisment("Mushahid","25",R.drawable.ic_color_lens_black_24dp,"Islamabad"));
        advertisment.add(new Advertisment("Mushahid","25",R.drawable.ic_color_lens_black_24dp,"lahore"));
        advertisment.add(new Advertisment("Mushahid","25",R.drawable.ic_color_lens_black_24dp,"Faisalabad"));


        mAdapter = new MyAdapter(advertisment);
        mRecyclerView.setAdapter(mAdapter);
    }
}
