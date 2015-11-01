package com.example.colleenminor.theadventure.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;
import com.example.colleenminor.theadventure.ui.house.ExplanationActivity;
import com.example.colleenminor.theadventure.ui.house.MainActivity;
import com.example.colleenminor.theadventure.ui.house.MoanerActivity;
import com.example.colleenminor.theadventure.ui.house.TwistyActivity;
import com.example.colleenminor.theadventure.ui.water.CaveActivity;
import com.example.colleenminor.theadventure.ui.water.CavePointActivity;
import com.example.colleenminor.theadventure.ui.water.MermaidKingdomActivity;
import com.example.colleenminor.theadventure.ui.water.MermaidPalaceActivity;
import com.example.colleenminor.theadventure.ui.water.OceanActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemsListActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private ArrayList<Item> mItems;
    private ArrayList<String> mItemsString;
    private Button mBackButton;
    private ListView lv;
    // private ItemAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        mBackButton = (Button) findViewById(R.id.backButton);
        setBackButton();
        String username = mPreferences.getString("username", null);
        mItems = (ArrayList) Item.all();

        lv = (ListView) findViewById(R.id.list);

        List<String> itemStringList = new ArrayList<String>();
        for(int i = 0; i < mItems.size(); i++){
            Item item = mItems.get(i);
            itemStringList.add(item.getItem());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                itemStringList);

        lv.setAdapter(arrayAdapter);
    }
    public void setBackButton(){
        String context = mPreferences.getString("EnteredItemButtonFrom", null);
        switch(context) {
            case "Main":
            mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "Twisty":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, TwistyActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "Moaner":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, MoanerActivity.class);
                        startActivity(intent);
                    }
                });
                break;
                case "Explanation":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this,ExplanationActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "Cave":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, CaveActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "CavePoint":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, CavePointActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "MermaidPalace":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, MermaidPalaceActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "MermaidKingdom":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, MermaidKingdomActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case "Ocean":
                mBackButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ItemsListActivity.this, OceanActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }

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



