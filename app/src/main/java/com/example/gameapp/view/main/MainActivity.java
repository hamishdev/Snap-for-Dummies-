package com.example.gameapp.View.Main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gameapp.R;
import com.example.gameapp.View.SnapLevelsActivity;

public class MainActivity extends Activity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("vis","started main");
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.Main_levels_button);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        SnapLevelsActivity.class);
                startActivity(myIntent);
            }
        });
    }


}
