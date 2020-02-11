package com.example.gameapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import androidx.databinding.DataBindingUtil;

import androidx.databinding.DataBindingUtil;


import com.example.gameapp.databinding.ActivityLevelsSelectBinding;
import com.example.gameapp.model.Card;
import com.example.gameapp.model.Level;
import com.example.gameapp.model.LevelSelector;
import com.example.gameapp.presenter.LevelSelectPresenter;
import com.example.gameapp.R;

import java.util.ArrayList;

public class LevelSelectActivity extends Activity implements SnapLevelsView {


    LevelSelector model;
    LevelSelectPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            //gameState = savedInstanceState.getString(GAME_STATE_KEY);
            model = new LevelSelector();
        }

        presenter = new LevelSelectPresenter(this,model);
        ActivityLevelsSelectBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_levels_select);
        binding.setLevelSelector(model);

        // Locate the level1button in activity_main.xml

        ArrayList<Button> buttons = new ArrayList<Button>() {
            {
                add((Button)findViewById(R.id.Levels_1));
                add((Button)findViewById(R.id.Levels_2));
                add((Button)findViewById(R.id.Levels_3));
                //add((Button)findViewById(R.id.Levels_12));

            }

        };

        for(Button button :buttons){
            // Capture level1button clicks
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    onLevelClicked(Integer.parseInt((String)button.getTag()));

                }
            });
        }

    }

    public void onLevelClicked(int levelNumber){

        presenter.levelSelected(levelNumber);

    }

    static final int START_LEVEL =1;

    public void startLevel(int levelNumber){
        Intent levelIntent = getLevelIntent(levelNumber);
        startActivityForResult(levelIntent,START_LEVEL);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == START_LEVEL) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                boolean win = data.getBooleanExtra("win",false);
                presenter.levelEnded(win);
            }
        }
    }
    private Intent getLevelIntent(int levelNumber) {
        switch(levelNumber){
            case 1:
                return new Intent(LevelSelectActivity.this,
                        level1Activity.class);
            case 2:
                return new Intent(LevelSelectActivity.this,
                        level2Activity.class);
            case 3:
                return new Intent(LevelSelectActivity.this, level3Activity.class);

        }
        return null;
    }


}
