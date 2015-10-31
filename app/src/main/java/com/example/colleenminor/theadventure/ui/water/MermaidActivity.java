package com.example.colleenminor.theadventure.ui.water;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.models.Item;
import com.example.colleenminor.theadventure.models.User;
import com.example.colleenminor.theadventure.ui.ItemsListActivity;

public class MermaidActivity extends AppCompatActivity {

    private User mUser;
    private SharedPreferences mPreferences;
    private int mActions;
    private boolean mBeenToPalace;
    private TextView mIntroText;
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
        getActionsFromPrefs();
        checkIfRoomHasBeenVisited("Mermaid");
        checkForPalaceVisit();
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
                if(!mBeenToPalace) {
                    Intent intent = new Intent(MermaidActivity.this, MermaidGiveActivity.class);
                    putActionsInPrefs();
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(MermaidActivity.this, MermaidPalaceActivity.class);
                    putActionsInPrefs();
                    startActivity(intent);
                }

            }
        });
        mOptionChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MermaidActivity.this, OceanActivity.class);
                putActionsInPrefs();
                startActivity(intent);
            }
        });
        //"Throw crab at her"
        mOptionChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MermaidActivity.this, MermerActivity.class);
                putActionsInPrefs();
                startActivity(intent);
            }
        });
    }

    private boolean checkForCrabs() {
        Item crabItem = Item.find("crab");
        if (crabItem != null) {
            return true;
        } else {
            return false;
        }
    }
    private void putActionsInPrefs() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove("Actions");
        editor.apply();
        editor.putInt("Actions", mActions);
        editor.commit();
    }


    private void getActionsFromPrefs() {
        mActions = mPreferences.getInt("Actions", 0);
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


    private void addActions(int numToAdd) {
        mActions += numToAdd;
    }
    private void checkIfRoomHasBeenVisited(String roomName){
        //Read to see if room has been visited:
        boolean userHasBeenHere = mPreferences.getBoolean(roomName, false);
        if(userHasBeenHere){
            mIntroText = (TextView) findViewById(R.id.introText);
            mIntroText.setText("Hello again");
            //mIntroText.setVisibility(TextView.INVISIBLE);
            setActionsText();
        }
        else if(!userHasBeenHere){
            //If room has not been visited:
            addActions(1);
            Toast.makeText(MermaidActivity.this, "New location! +1 action", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean(roomName, true);
            editor.commit();
            setActionsText();
            actionButtonAnimation();
        }
    }

    private void setActionsText() {
        mActionsTextView = (TextView) findViewById(R.id.actionsRemaining);
        mActionsTextView.setText("Actions remaining: " + mActions);
    }
    private void actionButtonAnimation(){
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(5); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        mActionsTextView.startAnimation(animation);

    }

    private void checkForPalaceVisit(){
       mBeenToPalace = mPreferences.getBoolean("MermaidPalace", false);
        if(mBeenToPalace){
            mOptionChoice1 = (TextView) findViewById(R.id.optionChoice1);
            mOptionChoice1.setText("Could you take me back to the ocean palace?");
        }
    }


}
