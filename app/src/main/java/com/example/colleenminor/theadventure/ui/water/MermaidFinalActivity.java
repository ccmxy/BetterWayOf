package com.example.colleenminor.theadventure.ui.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;

public class MermaidFinalActivity extends AppCompatActivity {
    private TextView mGiveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid_final);
        mGiveItem = (TextView) findViewById(R.id.optionChoice1);
        mGiveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MermaidFinalActivity.this, MermaidFinalGiveActivity.class);
                startActivity(intent);
            }
        });

    }

}
