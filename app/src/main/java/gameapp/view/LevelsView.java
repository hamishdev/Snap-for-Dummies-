package gameapp.view;

import gameapp.model.Card;

public interface LevelsView {
    void setPlayingCard(Card topCard);

    void setTotals(int playerTotal, int computerTotal, int pileTotal);

    void gameOver(boolean playerWon);

    void computerFlip(int delay);

    void computerSnapIfCan();

    void clearHandlers();

}
