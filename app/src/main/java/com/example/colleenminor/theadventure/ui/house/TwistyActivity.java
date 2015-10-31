package com.example.colleenminor.theadventure.ui.house;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.view.Gravity;
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
import com.example.colleenminor.theadventure.ui.water.OceanActivity;



public class TwistyActivity extends AppCompatActivity {
    private User mUser;
    private SharedPreferences mPreferences;
    private int mActions;
    private TextView mActionsTextView;
    private TextView mOptionChoice1; //re-light candle
    private TextView mOptionChoice2; //go upstairs (moaning)
    private TextView mOptionChoice3; //go downstairs (ocean)
    private LruCache<String, Bitmap> mMemoryCache;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twisty);
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };


        setTheItemButton();
        getPreferencesAndUser();
        getActionsFromPrefs();
        checkIfRoomHasBeenVisited("Twisty");

        mOptionChoice2 = (TextView) findViewById(R.id.optionChoice2);
        mOptionChoice3 = (TextView) findViewById(R.id.optionChoice3);

        mOptionChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwistyActivity.this, MoanerActivity.class);
             //  addActionsToIntent(intent);
                putActionsInPrefs();
                startActivity(intent);

            }
        });

        mOptionChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwistyActivity.this, OceanActivity.class);
           //   addActionsToIntent(intent);
                putActionsInPrefs();
                startActivity(intent);
                finish();


            }
        });

    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }


    private void checkIfRoomHasBeenVisited(String roomName){
        //Read to see if room has been visited:
        boolean userHasBeenHere = mPreferences.getBoolean(roomName, false);
        if(userHasBeenHere == true){
            setActionsText();
        }
        else {
            //If room has not been visited:
            addActions(1);
            Toast toast = Toast.makeText(TwistyActivity.this, "New location! +1 action", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
            toast.show();

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

    //Add a bigger bundle here which says if rooms have been visited, validate if room is visited by checking if it's in the bundle
    private void addActionsToIntent(Intent intent){
        String actionString = String.valueOf(mActions);
        intent.putExtra("theActions", actionString);
    }

    private boolean checkIntentForRoom(String roomName) {
        Bundle extras = getIntent().getExtras();
        String isRoom = extras.getString(roomName);
        if(isRoom != null){
            return true;
        }
        else{
            return false;
        }
    }
    private void getActionsFromIntent() {
        Bundle extras = getIntent().getExtras();
        String myActions = extras.getString("theActions");
        mActions = Integer.parseInt(myActions);
        //mUser.setActions(mActions);
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
                Intent intent = new Intent(TwistyActivity.this, ItemsListActivity.class);
                startActivity(intent);
            }
        });
    }


    private void deleteItem(String itemName) {
        Item.delete(itemName);
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
