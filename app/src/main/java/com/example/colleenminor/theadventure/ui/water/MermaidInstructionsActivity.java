package com.example.colleenminor.theadventure.ui.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;

public class MermaidInstructionsActivity extends AppCompatActivity {
    private TextView mHeadBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid_instructions);
        mHeadBack = (TextView) findViewById(R.id.backToBeach);
        mHeadBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MermaidInstructionsActivity.this, OceanActivity.class);
                startActivity(intent);
            }
        });

    }

}
