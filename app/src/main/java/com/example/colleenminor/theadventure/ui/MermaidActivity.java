package com.example.colleenminor.theadventure.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;
import com.example.colleenminor.theadventure.models.User;

public class MermaidActivity extends AppCompatActivity {

    private User mUser;
    private SharedPreferences mPreferences;
    private int mActions;
    private TextView mActionsTextView;
    private TextView mOptionChoice1; //re-light candle
    private TextView mOptionChoice2; //go upstairs (moaning)
    private TextView mOptionChoice3; //go downstairs (ocean)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid);
        setTheItemButton();
        getPreferencesAndUser();
        getActionsFromIntent();
        addActions(1);
        setActionsText();

        mOptionChoice1 = (TextView) findViewById(R.id.optionChoice1);
        mOptionChoice2 = (TextView) findViewById(R.id.optionChoice2);
        mOptionChoice3 = (TextView) findViewById(R.id.optionChoice3);

        boolean hasCrab = checkForCrabs();

        if(hasCrab){
            mOptionChoice3 .setVisibility(TextView.VISIBLE);

        }
        mOptionChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        mOptionChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //"Throw crab at her"
        mOptionChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MermaidActivity.this, MermerActivity.class);
                startActivity(intent);
            }
        });

        //Where other stuff goes




        //End of custom stuffs


    }

    private boolean checkForCrabs() {
        Item crabItem = Item.find("crab");
        if (crabItem != null) {
            return true;
        } else {
            return false;
        }
    }

    private void addActionsToIntent(Intent intent){
        String actionString = String.valueOf(mActions);
        intent.putExtra("theActions", actionString);
    }
    private void getActionsFromIntent() {
        Bundle extras = getIntent().getExtras();
        String myActions = extras.getString("theActions");
        mActions = Integer.parseInt(myActions);
        // mUser.setActions(mActions);
    }


    private void getPreferencesAndUser() {
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
        String username =  mPreferences.getString("username", null);
        mUser = User.find(username);
      //  mActions = mUser.getActions();
    }

    //Have to set the correct intent for this one
    private void setTheItemButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MermaidActivity.this, ItemsListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setActionsText() {
     //   mActions = mUser.getActions();
        mActionsTextView = (TextView) findViewById(R.id.actionsRemaining);
        mActionsTextView.setText("Actions remaining " + mActions);
    }

    private void addItem(String itemName) {
        Item item = new Item(itemName, mUser);
        item.save();
        Toast.makeText(this, mUser.getName() + "," + itemName + " has been added to your inventory", Toast.LENGTH_LONG).show();
    }

    private void subtractActions(int numToSubtract) {
        mActions -= numToSubtract;
    }

    private void addActions(int numToAdd) {
        mActions += numToAdd;
    }

//    private void subtractActions(int numToSubtract) {
//        mUser.subtractActions(numToSubtract);
//        mActions = mUser.getActions();
//    }
//
//    private void addActions(int numToAdd) {
//        mUser.addActions(numToAdd);
//        mActions = mUser.getActions();
//    }

}
