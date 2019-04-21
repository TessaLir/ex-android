package ru.vetukov.sb.homerecyclerviewadv;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Item> mItems;

    private static RecyclerView mRecycler;
    private static ListAdapter mAdapter;

    private static FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItems = new ArrayList<>();
        getStations();

        mRecycler = findViewById(R.id.main_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ListAdapter(this, mItems);
        mRecycler.setAdapter(mAdapter);

        fab = findViewById(R.id.main_fab);
        fab.setOnClickListener(fabClick());
    }

    private View.OnClickListener fabClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "Add " + mItems.size();
                int color = mItems.get(mItems.size() % 10).getmLogoColor();
                mItems.add(new Item(name, color));
                mAdapter.notifyDataSetChanged();
            }
        };
    }

    private static void getStations() {

        for (int i = 0; i < 2; i++) {
            mItems.add(new Item("One", 0xFF900020));
            mItems.add(new Item("Two", 0xFF34C924));
            mItems.add(new Item("Three", 0xFF3E5F8A));
            mItems.add(new Item("Four", 0xFF6495ED));
            mItems.add(new Item("Five", 0xFF64400F));
            mItems.add(new Item("Six", 0xFFCD7F32));
            mItems.add(new Item("Seven", 0xFF702963));
            mItems.add(new Item("Eight", 0xFFFFDC33));
            mItems.add(new Item("Nine", 0xFFB5B8B1));
            mItems.add(new Item("Ten", 0xFF7FFFD4));
        }
    }

}
