package com.example.gameapp.View.levels;

import com.example.gameapp.Model.Card;

public interface LevelsView {
    void setPlayingCard(Card topCard);

    void setTotals(int playerTotal, int computerTotal, int pileTotal);

    void gameOver(boolean playerWon);

    void computerFlip();

    void computerSnapIfCan();
}
