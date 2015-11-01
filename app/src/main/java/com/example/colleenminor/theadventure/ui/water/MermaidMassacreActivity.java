package com.example.colleenminor.theadventure.ui.water;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.colleenminor.theadventure.R;
import com.example.colleenminor.theadventure.ui.house.MainActivity;

public class MermaidMassacreActivity extends AppCompatActivity {
    private TextView mPlayAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mermaid_massacre);

        mPlayAgain = (TextView) findViewById(R.id.playAgain);
        mPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MermaidMassacreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
