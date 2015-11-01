package com.example.colleenminor.theadventure.ui.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.example.colleenminor.theadventure.R;

public class MermaidFinalActivity extends AppCompatActivity {
    //private TextView mGiveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid_final);
        setTheItemButton();
        itemButtonAnimation();
//        mGiveItem = (TextView) findViewById(R.id.optionChoice1);
//        mGiveItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MermaidFinalActivity.this, MermaidFinalGiveActivity.class);
//                startActivity(intent);
//            }
//        });

    }
    private void setTheItemButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MermaidFinalActivity.this, MermaidFinalGiveActivity.class);
                startActivity(intent);
            }
        });
    }
    private void itemButtonAnimation() {
        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        animation.setDuration(300); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(3); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        fab.startAnimation(animation);
    }



}
