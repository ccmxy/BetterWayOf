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
import com.example.colleenminor.theadventure.models.User;
import com.example.colleenminor.theadventure.ui.ItemsListActivity;

public class MermaidKingdomActivity extends AppCompatActivity {

    private User mUser;
    private SharedPreferences mPreferences;
    private int mActions;
    private TextView mActionsTextView;
    private TextView mOptionChoice1;
    private TextView mOptionChoice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid_kingdom);
        setTheItemButton();
        getPreferencesAndUser();
        getActionsFromPrefs();
        checkIfRoomHasBeenVisited("MermaidKingdom");
        setActionsText();

        mOptionChoice1 = (TextView) findViewById(R.id.optionChoice1);
        mOptionChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtractActions(2);
                setActionsText();
                actionButtonAnimation();
                putActionsInPrefs();
                Intent intent = new Intent(MermaidKingdomActivity.this, CavePointActivity.class);
                startActivity(intent);
            }
        });
        mOptionChoice2 = (TextView) findViewById(R.id.optionChoice2);

        mOptionChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MermaidKingdomActivity.this, NoAirActivity.class);
                startActivity(intent);
            }
        });



    }

    private void checkIfRoomHasBeenVisited(String roomName){
        //Read to see if room has been visited:
        boolean userHasBeenHere = mPreferences.getBoolean(roomName, false);
        if(userHasBeenHere == true){
            setActionsText();
            return;
        }
        else {
            //If room has not been visited:
            addActions(1);
            Toast.makeText(MermaidKingdomActivity.this, "New location! +1 action", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putBoolean(roomName, true);
            editor.commit();
            setActionsText();
            actionButtonAnimation();
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
        //mActions = mUser.getActions();
    }

    //Have to set the correct intent for this one
    private void setTheItemButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MermaidKingdomActivity.this, ItemsListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void subtractActions(int numToAdd) {
        mActions -= numToAdd;
    }

    private void addActions(int numToAdd) {
        mActions += numToAdd;
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

}
