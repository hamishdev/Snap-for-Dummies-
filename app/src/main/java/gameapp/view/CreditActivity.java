package gameapp.view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import gameapp.R;

public class CreditActivity extends Activity {

    @Override
    public  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        Button reset_game = findViewById(R.id.reset_game_button);

        reset_game.setOnClickListener(view ->
               resetGame() );
    }

    public void resetGame(){
        // Store values between instances here
        SharedPreferences preferences = getSharedPreferences("saveState",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();  // Put the values from the UI
        int level = 0;

        editor.putInt("level", level);
        // Commit to storage
        editor.commit();
    }
}
