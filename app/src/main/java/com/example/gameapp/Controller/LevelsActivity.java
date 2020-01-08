package com.example.gameapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gameapp.Controller.Levels.level1Activity;
import com.example.gameapp.R;

import java.util.ArrayList;

public class LevelsActivity extends Activity {


    Button level1button;
    Button level2button;
    private int levelsCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        ArrayList<Integer> keys = new ArrayList<Integer>();
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);;

        SharedPreferences.Editor ed = mPrefs.edit();



        // Locate the level1button in activity_main.xml
        level1button = (Button) findViewById(R.id.Levels_1);
        level1button.setBackgroundColor(Color.parseColor("#FFFFFF"));

        // Capture level1button clicks
        level1button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(LevelsActivity.this,
                        level1Activity.class);
                startActivity(myIntent);
            }
        });

        // Locate the level1button in activity_main.xml
        level2button = (Button) findViewById(R.id.Levels_2);
        if(mPrefs.getBoolean("level1Completed",false)) {
            level2button.setBackgroundColor(Color.parseColor("#D81B60"));
        }
        // Capture level1button clicks
        level2button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                //Intent myIntent = new Intent(LevelsActivity.this,
                //        level2Activity.class);
                //startActivity(myIntent);
            }
        });

    }
}
