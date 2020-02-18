package com.example.gameapp.model;

import androidx.databinding.ObservableInt;


import java.io.Serializable;

public class LevelSelector implements Serializable {

    Level[] levels;
    public int currentLevel;
    public final int totalLevels=3;
    public final ObservableInt levelUpTo = new ObservableInt();

    public LevelSelector(){
        //levels.add
        levelUpTo.set(1);

    }
    public LevelSelector(int level){
        //levels.add
        levelUpTo.set(level);

    }

    //levelNumber will correlate to speed
    public boolean startLevel(int levelNumber) {
        if(levelUpTo.get()>=levelNumber) {
            currentLevel = levelNumber;
            return true;
        }
        return false;

    }



    public void finishLevel(boolean winStatus) {
        //won the currentToBeat level
        if (winStatus&&currentLevel==levelUpTo.get()) {
            levelUpTo.set(levelUpTo.get() + 1);
        }
    }

    public boolean wonGame(){
        if(levelUpTo.get()>totalLevels){
            return true;
        }
        return false;
    }
}
