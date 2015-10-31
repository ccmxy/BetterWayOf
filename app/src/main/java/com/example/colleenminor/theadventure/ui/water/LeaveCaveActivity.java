package com.example.colleenminor.theadventure.ui.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;

public class LeaveCaveActivity extends AppCompatActivity {
    private TextView mFollowSeawed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_cave);

        mFollowSeawed = (TextView) findViewById(R.id.followSeaweed);
        mFollowSeawed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaveCaveActivity.this, MermaidInstructionsActivity.class);
                startActivity(intent);
            }
        });

    }

}
