package com.example.colleenminor.theadventure.ui.water;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;

public class LeaveCaveActivity extends AppCompatActivity {
    private TextView mFollowSeawed;
    private ImageView mSeaWeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_cave);

        mFollowSeawed = (TextView) findViewById(R.id.followSeaweed);
        mFollowSeawed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("NeedsToDealWithOldMan", true);
                editor.commit();
                Intent intent = new Intent(LeaveCaveActivity.this, MermaidInstructionsActivity.class);
                startActivity(intent);
            }
        });
        mSeaWeed = (ImageView) findViewById(R.id.sea);
        mSeaWeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("NeedsToDealWithOldMan", true);
                editor.commit();
                Intent intent = new Intent(LeaveCaveActivity.this, MermaidInstructionsActivity.class);
                startActivity(intent);
            }
        });

    }

}
