package com.example.colleenminor.theadventure.ui.water;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;
import com.example.colleenminor.theadventure.models.User;
import com.example.colleenminor.theadventure.ui.house.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class EelActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private TextView mNoItems;
    private TextView mSlayEel;
    private Button mBackButton;
    private ArrayList<Item> mItems;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eel);
        getPreferencesAndUser();
        mSlayEel = (TextView) findViewById(R.id.slayEel);
        mNoItems = (TextView) findViewById(R.id.noItems);
        mBackButton = (Button) findViewById(R.id.backButton);

        mItems = (ArrayList) Item.all();
        if (mItems.size() == 0) {
            mNoItems.setVisibility(TextView.VISIBLE);
        }
        if(Item.find("crab") != null) {
            mBackButton.setVisibility(View.INVISIBLE);
            mSlayEel.setVisibility(View.VISIBLE);


            lv = (ListView) findViewById(R.id.listView);

            List<String> itemStringList = new ArrayList<>();
            for (int i = 0; i < mItems.size(); i++) {
                Item item = mItems.get(i);
                itemStringList.add(item.getItem());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    itemStringList);
            lv.setAdapter(arrayAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    String theItem = (String) arg0.getItemAtPosition(position);
                    if (!(theItem.equals("crab"))) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "You can't kill an eel with the " + arg0.getItemAtPosition(position) + "!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (theItem.equals("crab")) {
                        Item.delete("crab");
                        addItem("skull of seo");
                        Intent intent = new Intent(EelActivity.this, LeaveCaveActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        else{
            mBackButton.setVisibility(View.VISIBLE);
            mSlayEel.setVisibility(View.INVISIBLE);
            mBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EelActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });


        }
    }
    private void getPreferencesAndUser() {
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        String username =  mPreferences.getString("username", null);
        mUser = User.find(username);
    }

    private void addItem(String itemName) {
        Item item = new Item(itemName, mUser);
        item.save();
        Toast.makeText(this, mUser.getName() + "," + itemName + " has been added to your inventory", Toast.LENGTH_LONG).show();

    }




}