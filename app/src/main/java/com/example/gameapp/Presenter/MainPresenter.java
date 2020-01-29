package com.example.gameapp.Presenter;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.Game;
import com.example.gameapp.View.SnapLevelsView;

import java.io.Serializable;

public class MainPresenter implements Presenter, Serializable {

    private SnapLevelsView view;
    private Game model;
    //Could use Service for an AI? This is good though.
    //private Computer ai;

    public MainPresenter(SnapLevelsView view,Game _model){
        this.model= _model;
        this.view = view;
        //this.ai = new Computer(model);

    }

    @Override
    public void onCreate(){
        model = new Game();
    }
    @Override
    public void onPause(){};
    @Override
    public void onResume(){};
    @Override
    public void onDestroy(){};




    public boolean levelSelected(int levelNumber) {
        if(model.startLevel(levelNumber)) {
            view.startLevel(levelNumber);
            return true;
        }
        return false;
    }

}
