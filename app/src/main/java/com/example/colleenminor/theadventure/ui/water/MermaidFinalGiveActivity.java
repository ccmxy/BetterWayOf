package com.example.colleenminor.theadventure.ui.water;

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

public class MermaidFinalGiveActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private TextView mNoItems;
    private Button mBackButton;
    private ArrayList<Item> mItems;
    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid_final_give);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        mNoItems = (TextView) findViewById(R.id.noItems);
        mBackButton = (Button) findViewById(R.id.backButton);
        mBackButton.setVisibility(View.INVISIBLE);

        //  getActionsFromIntent();
        mItems = (ArrayList) Item.all();
        if (mItems.size() == 0){
            mNoItems.setVisibility(TextView.VISIBLE);
        }

        lv = (ListView) findViewById(R.id.listView);

        List<String> itemStringList = new ArrayList<>();
        for(int i = 0; i < mItems.size(); i++){
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
                if ((!(theItem.equals("Anti-Mermaid Spray")))  && (!(theItem.equals("skull of seo")))) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No, not the " + arg0.getItemAtPosition(position) + "!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    toast.show();
                }

                if (theItem.equals("Anti-Mermaid Spray")) {
                    Intent intent = new Intent(MermaidFinalGiveActivity.this, MermaidMassacreActivity.class);
                    startActivity(intent);
                }
                if (theItem.equals("skull of seo")) {
                    Intent intent = new Intent(MermaidFinalGiveActivity.this, MermaidConfessionActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}