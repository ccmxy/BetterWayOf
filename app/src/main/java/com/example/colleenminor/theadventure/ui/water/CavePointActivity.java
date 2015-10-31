package com.example.colleenminor.theadventure.ui.water;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;

public class CavePointActivity extends AppCompatActivity {
    private TextView mOptionOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mOptionOne = (TextView) findViewById(R.id.optionChoice1);
//        mOptionOne.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CavePointActivity.this, CaveActivity.class);
//                startActivity(intent);
//            }
//        });


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cave_point);

    }

}
