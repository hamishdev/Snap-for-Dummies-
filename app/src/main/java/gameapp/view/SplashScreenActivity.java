package gameapp.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import gameapp.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread(){

            @Override
            public void run(){
                try{
                sleep(5000);

                }
                catch(Exception e){
                    e.printStackTrace();

                }
                finally{
                    Intent mainIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
