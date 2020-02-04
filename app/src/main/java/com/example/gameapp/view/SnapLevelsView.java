package com.example.gameapp.view;

import com.example.gameapp.model.Card;

public interface SnapLevelsView {
    void startLevel(int levelNumber);

    void setCard(Card topCard);
}
