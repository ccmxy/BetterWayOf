package com.example.colleenminor.theadventure.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemsListActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private ArrayList<Item> mItems;
    private ArrayList<String> mItemsString;
    private ListView lv;
    // private ItemAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        String username = mPreferences.getString("username", null);
        mItems = (ArrayList) Item.all();

        lv = (ListView) findViewById(R.id.list);

        List<String> itemStringList = new ArrayList<String>();
        for(int i = 0; i < mItems.size(); i++){
            Item item = mItems.get(i);
            itemStringList.add(item.getItem());
        }


        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("foo");
        your_array_list.add("bar");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                itemStringList);

        lv.setAdapter(arrayAdapter);
    }
}



    //Now, look thru the database and find all of the items associated with user



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });



