package com.example.colleenminor.theadventure.ui.water;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.ui.ItemsListActivity;

public class CaveActivity extends AppCompatActivity {
    private TextView mCaveSegment;
    private TextView mCheckLeft;
    private TextView mCheckRight;
    private TextView mMoveForward;
    private TextView mNothingHere;
    private ImageView mWiredEel;
    private int mSegmentTracker;
    private TextView mScaryEel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave);
        mSegmentTracker = 1;

        mCaveSegment = (TextView) findViewById(R.id.caveSegment);
        mCheckLeft = (TextView) findViewById(R.id.checkLeft);
        mCheckRight = (TextView) findViewById(R.id.checkRight);
        mMoveForward = (TextView) findViewById(R.id.moveForward);
        mNothingHere = (TextView) findViewById(R.id.nothingHere);
        mWiredEel = (ImageView) findViewById(R.id.wiredEel);
        mScaryEel = (TextView) findViewById(R.id.scaryEel);
        setTheItemButton();
        setTheItemButton();



        mMoveForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSegmentTracker++;
                setCaveSegment();
            }
        });

        mCheckLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLeftCheck();
            }
        });

        mCheckRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRightCheck();
            }
        });

//        mScaryEel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    Intent intent = new Intent(CaveActivity.this, EelActivity.class);
//                    startActivity(intent);
//            }
//        });


        mNothingHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCaveSegment.setVisibility(View.VISIBLE);
                mCheckLeft.setVisibility(View.VISIBLE);
                mCheckRight.setVisibility(View.VISIBLE);
                mMoveForward.setVisibility(View.VISIBLE);
                mNothingHere.setVisibility(View.INVISIBLE);

            }
        });
    }
    private void setLeftCheck(){
        if(mSegmentTracker == 1){
            mCaveSegment = (TextView) findViewById(R.id.caveSegment);
            mCheckLeft = (TextView) findViewById(R.id.checkLeft);
            mCheckRight = (TextView) findViewById(R.id.checkRight);
            mMoveForward = (TextView) findViewById(R.id.moveForward);
            mNothingHere = (TextView) findViewById(R.id.nothingHere);

            mCaveSegment.setVisibility(View.INVISIBLE);
            mCheckLeft.setVisibility(View.INVISIBLE);
            mCheckRight.setVisibility(View.INVISIBLE);
            mMoveForward.setVisibility(View.INVISIBLE);
            mNothingHere.setVisibility(View.VISIBLE);
        }
        if(mSegmentTracker == 2){
                mCaveSegment = (TextView) findViewById(R.id.caveSegment);
                mCheckLeft = (TextView) findViewById(R.id.checkLeft);
                mCheckRight = (TextView) findViewById(R.id.checkRight);
                mMoveForward = (TextView) findViewById(R.id.moveForward);
                mNothingHere = (TextView) findViewById(R.id.nothingHere);
                mWiredEel = (ImageView) findViewById(R.id.wiredEel);
            mScaryEel = (TextView) findViewById(R.id.scaryEel);


                mCaveSegment.setVisibility(View.INVISIBLE);
                mCheckLeft.setVisibility(View.INVISIBLE);
                mCheckRight.setVisibility(View.INVISIBLE);
                mMoveForward.setVisibility(View.INVISIBLE);
                mNothingHere.setVisibility(View.INVISIBLE);
                mWiredEel.setVisibility(View.VISIBLE);
                setTheItemButtonForEel();
                mScaryEel.setVisibility(View.VISIBLE);


            }
        if(mSegmentTracker == 3){
            mCaveSegment = (TextView) findViewById(R.id.caveSegment);
            mCheckLeft = (TextView) findViewById(R.id.checkLeft);
            mCheckRight = (TextView) findViewById(R.id.checkRight);
            mMoveForward = (TextView) findViewById(R.id.moveForward);
            mNothingHere = (TextView) findViewById(R.id.nothingHere);

            mCaveSegment.setVisibility(View.INVISIBLE);
            mCheckLeft.setVisibility(View.INVISIBLE);
            mCheckRight.setVisibility(View.INVISIBLE);
            mMoveForward.setVisibility(View.INVISIBLE);
            mNothingHere.setVisibility(View.VISIBLE);
        }


    }

    private void setRightCheck(){
            mCaveSegment = (TextView) findViewById(R.id.caveSegment);
            mCheckLeft = (TextView) findViewById(R.id.checkLeft);
            mCheckRight = (TextView) findViewById(R.id.checkRight);
            mMoveForward = (TextView) findViewById(R.id.moveForward);
            mNothingHere = (TextView) findViewById(R.id.nothingHere);

            mCaveSegment.setVisibility(View.INVISIBLE);
            mCheckLeft.setVisibility(View.INVISIBLE);
            mCheckRight.setVisibility(View.INVISIBLE);
            mMoveForward.setVisibility(View.INVISIBLE);
            mNothingHere.setVisibility(View.VISIBLE);
    }

    private void setCaveSegment(){
        mCaveSegment = (TextView) findViewById(R.id.caveSegment);
        if(mSegmentTracker == 2){
            mCaveSegment.setText("Cave Segment 2");
        }
        if(mSegmentTracker == 3){
            mCaveSegment.setText("Cave Segment 3");
        }
        if(mSegmentTracker == 4){
            Intent intent = new Intent(CaveActivity.this, LostActivity.class);
            startActivity(intent);
        }

    }

    private void setTheItemButtonForEel() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CaveActivity.this, EelActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setTheItemButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mPreferences = getApplicationContext().getSharedPreferences("TheAdventure", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("EnteredItemButtonFrom", "Cave");
                editor.commit();
                Intent intent = new Intent(CaveActivity.this, ItemsListActivity.class);
                startActivity(intent);
            }
        });
    }


}
