package com.example.gameapp.model;

import androidx.databinding.ObservableInt;

import java.io.Serializable;

public class LevelSelector implements Serializable {

    Level[] levels;
    public int currentLevel;
    public final ObservableInt levelUpTo = new ObservableInt();

    public LevelSelector(){
        //levels.add
        levelUpTo.set(1);
    }

    //levelNumber will correlate to speed
    public boolean startLevel(int levelNumber) {
        if(levelUpTo.get()>=levelNumber) {
            currentLevel = levelNumber;
            return true;
        }
        return false;

    }



    public void wonLevel(boolean win) {
        //won the currentToBeat level
        if (win&&currentLevel==levelUpTo.get()) {
            levelUpTo.set(levelUpTo.get() + 1);

        }
    }
}
