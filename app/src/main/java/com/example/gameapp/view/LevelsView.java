package com.example.gameapp.view;

import com.example.gameapp.model.Card;

public interface LevelsView {
    void setPlayingCard(Card topCard);

    void setTotals(int playerTotal, int computerTotal, int pileTotal);

    void gameOver(boolean playerWon);

    void computerFlip(int delay);

    void computerSnapIfCan();

    void clearHandlers();

}
