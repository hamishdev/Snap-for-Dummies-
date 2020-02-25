package gameapp.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import gameapp.R;

import gameapp.view.LevelSelectActivity;

public class MainActivity extends Activity {

    Button button;
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("vis","started main");
        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.Main_levels_button);
        Button button_credits = (Button) findViewById(R.id.button2);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        LevelSelectActivity.class);
            startActivity(myIntent);}
        });
        button_credits.setOnClickListener(arg0 -> {

            // Start NewActivity.class
            Intent myIntent = new Intent(MainActivity.this,
                    CreditActivity.class);
            startActivity(myIntent);
        });
    }



}
