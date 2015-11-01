package com.example.colleenminor.theadventure.ui.house;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;
import com.example.colleenminor.theadventure.models.User;

public class ExplanationActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private TextView mOptionChoice1;
    private TextView mOptionChoice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferencesAndUser();

        setContentView(R.layout.activity_explanation);
        mOptionChoice1 = (TextView) findViewById(R.id.optionChoice1);
        mOptionChoice2 = (TextView) findViewById(R.id.optionChoice3);

        mOptionChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem("Anti-Mermaid Spray");
                mOptionChoice1.setVisibility(View.INVISIBLE);

            }
        });
        mOptionChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExplanationActivity.this, TwistyActivity.class);
                startActivity(intent);
            }
        });



    }


    private void addItem(String itemName) {
        Item item = new Item(itemName, mUser);
        item.save();
        Toast.makeText(this, mUser.getName() + "," + itemName + " has been added to your inventory", Toast.LENGTH_LONG).show();

    }
    private void getPreferencesAndUser() {
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        String username =  mPreferences.getString("username", null);
        mUser = User.find(username);
    }

}
