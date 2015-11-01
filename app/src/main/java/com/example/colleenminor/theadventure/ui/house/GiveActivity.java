package com.example.colleenminor.theadventure.ui.house;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;

import java.util.ArrayList;
import java.util.List;

public class GiveActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private TextView mNoItems;
    private Button mButton;
    private ArrayList<Item> mItems;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        mNoItems = (TextView) findViewById(R.id.noItems);
        mButton = (Button) findViewById(R.id.tackButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiveActivity.this, MoanerActivity.class);
                startActivity(intent);
            }
        });


         mItems = (ArrayList) Item.all();
        if (mItems.size() == 0){
            mNoItems.setVisibility(View.VISIBLE);
        }

        lv = (ListView) findViewById(R.id.listView);

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String theItem = (String) arg0.getItemAtPosition(position);
                if (!(theItem.equals("skull of seo"))) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No, no the " + (String) arg0.getItemAtPosition(position) + "!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                if (theItem.equals("skull of seo")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ah, yes, the skull of seo!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Item.delete("skull of seo");
                    Intent intent = new Intent(GiveActivity.this, ExplanationActivity.class);
                    startActivity(intent);
                }
            }

//            public void myClickHandler(View target) {
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Yes! The " , Toast.LENGTH_SHORT);
//                toast.show();
//                Intent
//
//                // Do stuff
//            }


        });

    }


}
