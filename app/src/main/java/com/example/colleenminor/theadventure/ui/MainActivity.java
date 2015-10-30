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
import com.example.colleenminor.theadventure.models.Rooms;
import com.example.colleenminor.theadventure.models.User;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private User mUser;
    private int mActions;
    private Rooms mRooms;
    private TextView mTakeItem;
    private TextView mActionsTextView;
    private TextView mNextRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheItemButton();

        mTakeItem = (TextView) findViewById(R.id.takeItem);
        mNextRoom = (TextView) findViewById(R.id.nextLagoon);
        mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);


        if (!nameChosen()) {
            Intent intent = new Intent(this, NameActivity.class);
            startActivity(intent);
        }
        mUser.setActions(1);
        setActionsText();

        deleteAllItems();

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
                String actionString = String.valueOf(mActions);
                intent.putExtra("theActions", actionString);
                startActivity(intent);
            }

        });

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
            mActions = mUser.getActions();
        } else {
            mUser = new User(username, 1);
            mUser.save();
            mActions = mUser.getActions();
        }
        Toast.makeText(this, "Welcome to the start of your great adventure, " + mUser.getName()
                +"! The number of actions that you have is displayed in " +
                " the icon in the icon in the bottom left corner. " +
                " Choices that cost an action are highlighted." +
                "You can view your item inventory any time by clicking the bottom right icon.", Toast.LENGTH_LONG).show();

    }

    private void deleteAllItems() {
        Item.delete("crab");
        Item.delete("candlestick");

    }
    
    private void addItem(String itemName) {
        Item item = new Item(itemName, mUser);
        item.save();
        Toast.makeText(this, mUser.getName() + ", candlestick has been added to your inventory", Toast.LENGTH_LONG).show();

    }

    private void setActionsText() {
        mActions = mUser.getActions();
        mActionsTextView = (TextView) findViewById(R.id.actionsRemaining);
        mActionsTextView.setText("Actions remaining " + mActions);
    }

    private void subtractActions(int numToSubtract) {
      /*  mUser.subtractActions(numToAdd);
        mActions = mUser.getActions();*/
        mActions = mActions - numToSubtract;
    }

    private void addActions(int numToAdd) {
        //Got rid of mUser.addActions(numToAdd);
       // mUser.addActions(numToAdd);
        mActions = mActions + numToAdd;
    }

    private void setTheItemButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ItemsListActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


}
