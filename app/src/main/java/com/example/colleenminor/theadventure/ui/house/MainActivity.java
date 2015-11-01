package com.example.colleenminor.theadventure.ui.house;

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
import com.example.colleenminor.theadventure.ui.ItemsListActivity;
import com.example.colleenminor.theadventure.ui.NameActivity;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private int mActions;
    private TextView mTakeItem;
    private TextView mActionsTextView;
    private TextView mNextRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTakeItem = (TextView) findViewById(R.id.takeItem);
        mNextRoom = (TextView) findViewById(R.id.nextLagoon);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);

        resetRooms();
        putActionsInPrefs();
        deleteAllItems();
        setTheItemButton();
        getTheUser();

        mActions = 1;
        setActionsText();

        mTakeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem("candlestick");
                setActionsText();
                mTakeItem.setVisibility(View.GONE);
            }
        });

        mNextRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TwistyActivity.class);
                addActionsToIntent(intent);
                startActivity(intent);
                finish();
            }

        });

    }
    //End of onCreate

    private void resetRooms() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("Twisty", false);
        editor.putBoolean("Moaner", false);
        editor.putBoolean("Ocean", false);
        editor.putBoolean("Mermaid", false);
        editor.putBoolean("MermaidPalace", false);
        editor.putBoolean("MermaidKingdom", false);
        editor.putBoolean("OldManDead", false);
        editor.commit();
    }

    private void putActionsInPrefs() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt("Actions", 1);
        editor.commit();
    }


    private void addActionsToIntent(Intent intent){
        String actionString = String.valueOf(mActions);
        intent.putExtra("theActions", actionString);
    }

    private void setActionsText() {
        mActions = mUser.getActions();
        mActionsTextView = (TextView) findViewById(R.id.actionsRemaining);
        mActionsTextView.setText("Actions remaining " + mActions);
    }

    private void getTheUser(){
        if (!nameChosen()) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        }
    }


    private boolean nameChosen() {
        String username = mPreferences.getString("username", null);
        if (username == null) {
            return false;
        } else {
            setUser(username);
            return true;
        }
    }

    private void setUser(String username) {
        User user = User.find(username);
        if (user != null) {
            mUser = user;
           // mActions = mUser.getActions();
        } else {
            mUser = new User(username, 1);
            mUser.save();
           // mActions = mUser.getActions();
        }
//        Toast.makeText(this, "Welcome to the start of your great adventure, " + mUser.getName()
//                +"! The number of actions that you have is displayed in " +
//                " the icon in the icon in the bottom left corner. " +
//                " Choices that cost an action are highlighted." +
//                "You can view your item inventory any time by clicking the bottom right icon.", Toast.LENGTH_SHORT).show();
    }

    private void deleteAllItems() {
        Item.delete("crab");
        Item.delete("candlestick");
        Item.delete("seashells");
        Item.delete("skull of seo");
        Item.delete("Anti-Mermaid Spray");
    }
    
    private void addItem(String itemName) {
        Item item = new Item(itemName, mUser);
        item.save();
        Toast.makeText(this, mUser.getName() + ", candlestick has been added to your inventory", Toast.LENGTH_SHORT).show();

    }

    private void setTheItemButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemsListActivity.class);
                startActivity(intent);
            }
        });
    }


}
