package com.example.colleenminor.theadventure.ui.water;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;

public class CaveActivity extends AppCompatActivity {
    private TextView mCaveSegment;
    private TextView mCheckLeft;
    private TextView mCheckRight;
    private TextView mMoveForward;
    private TextView mNothingHere;
    private int mSegmentTracker;

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


        mNothingHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCaveSegment.setVisibility(View.VISIBLE);
                mCheckLeft.setVisibility(View.VISIBLE);
                mCheckLeft.setVisibility(View.VISIBLE);
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

    }
}
