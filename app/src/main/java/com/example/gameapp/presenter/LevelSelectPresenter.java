package com.example.gameapp.presenter;

import com.example.gameapp.model.LevelSelector;
import com.example.gameapp.view.SnapLevelsView;

import java.io.Serializable;

public class LevelSelectPresenter implements Presenter, Serializable {

    private SnapLevelsView view;
    private LevelSelector model;
    //Could use Service for an AI? This is good though.
    //private Computer ai;

    public LevelSelectPresenter(SnapLevelsView view, LevelSelector _model){
        this.model= _model;
        this.view = view;
        //this.ai = new Computer(model);

    }





    public boolean levelSelected(int levelNumber) {
        if(model.startLevel(levelNumber)) {
            view.startLevel(levelNumber);
            return true;
        }
        return false;
    }

    public void levelEnded(boolean win) {
        model.finishLevel(win);
        wonGame();

    }

    public void wonGame(){
        if(model.wonGame()){
            view.postConfetti();
        }
    }



}
