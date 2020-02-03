package com.example.gameapp.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.Game;
import com.example.gameapp.Presenter.MainPresenter;
import com.example.gameapp.R;
import com.example.gameapp.View.levels.level1Activity;
import com.example.gameapp.View.levels.level2Activity;

import java.util.ArrayList;

public class SnapLevelsActivity extends Activity implements SnapLevelsView {


    ArrayList<Button> buttons;
    Game model = new Game();
    MainPresenter presenter = new MainPresenter(this,model);

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_levels);
        // Locate the level1button in activity_main.xml

        ArrayList<Button> buttons = new ArrayList<Button>() {
            {
                add((Button)findViewById(R.id.Levels_1));
                add((Button)findViewById(R.id.Levels_2));
                add((Button)findViewById(R.id.Levels_3));
                add((Button)findViewById(R.id.Levels_4));
                add((Button)findViewById(R.id.Levels_5));
                add((Button)findViewById(R.id.Levels_6));
                add((Button)findViewById(R.id.Levels_7));
                add((Button)findViewById(R.id.Levels_8));
                add((Button)findViewById(R.id.Levels_9));
                add((Button)findViewById(R.id.Levels_10));
                add((Button)findViewById(R.id.Levels_11));
                add((Button)findViewById(R.id.Levels_12));

            }

        };

        for(Button button :buttons){
            // Capture level1button clicks
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    onLevelClicked(Integer.parseInt((String)button.getText()));

                }
            });
        }


    }

    public void onLevelClicked(int levelNumber){

        presenter.levelSelected(levelNumber);

    }

    public void startLevel(int levelNumber){
        Intent levelIntent = getLevelIntent(levelNumber);
        levelIntent.putExtra("model",model);
        startActivity(levelIntent);


    }

    private Intent getLevelIntent(int levelNumber) {
        switch(levelNumber){
            case 1:
                return new Intent(SnapLevelsActivity.this,
                        level1Activity.class);
            case 2:
                return new Intent(SnapLevelsActivity.this,
                        level2Activity.class);

        }
        return null;
    }

    public void setActivity(Activity activity){

    }

    @Override
    public void setCard(Card topCard) {

    }
}
