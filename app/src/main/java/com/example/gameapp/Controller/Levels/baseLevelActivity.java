package com.example.gameapp.Controller.Levels;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.Points;
import com.example.gameapp.Model.Board;
import com.example.gameapp.R;

import static android.content.ContentValues.TAG;

public abstract class baseLevelActivity extends Activity {


    Points playerPoints = new Points();
    Board board;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int playerTime =1000;
        setContentView(getLayoutResourceId());
        board = new Board();


    }

    //Actions
    public void drawPlayerCard(){
        board.flipFromPlayerHand();
        updateEverything();
    }

    public void drawComputerCard(){
        board.flipFromComputerHand();
        updateEverything();

    }

    //
    public void updateEverything(){
        updatePlayerCard();
        updateComputerCard();
        updatePlayingPile();
        updatePlayerPoints();
        updateCompPoints();
        updatePlayingPilePoints();
        checkWon();
    }

    //View controllers
    public void updatePlayerCard(){

        Card card =board.getDeck().getPlayerHand().getTopCard();
        if(card!=null) {
            updateTextView("HIDDEN", R.id.playerHand);

            //updateTextView(card.getValue() + " of " + card.getSuit(), R.id.playerHand);
        }
        else{
            updateTextView("empty",R.id.playerHand);
        }
    }

    public void updateComputerCard(){
    Card card =board.getDeck().getComputerHand().getTopCard();
        if(card!=null) {
            updateTextView("HIDDEN", R.id.computerHand);

            //updateTextView(card.getValue() + " of " + card.getSuit(), R.id.computerHand);
        }
        else{
            updateTextView("empty",R.id.computerHand);
        }
    }

    public void postWonMessage(){
        updateTextView("You won!!!",R.id.computerHand);
        updateTextView("You won!!!",R.id.stack);
        updateTextView("You won!!!",R.id.compPoints);
    }

    public void postLostMessage(){
        updateTextView("You lost...",R.id.playerHand);
        updateTextView("You lost...",R.id.stack);
        updateTextView("You lost...",R.id.playerPoints);
    }


    public void checkWon(){

    }


    public void updatePlayingPile(){

        Card card =board.getDeck().getPlayingPile().getTopCard();
        if(card!=null) {
            updateTextView(card.getValue() + " of " + card.getSuit(), R.id.stack);
        }
        else{
            updateTextView(null,R.id.stack);
        }

    }
    public void updateCompPoints(){
        updateTextView(String.valueOf(board.getCompTotal()),R.id.compPoints);

    }

    public void updatePlayingPilePoints(){
        updateTextView(String.valueOf(board.getDeck().getPlayingPile().size()),R.id.ordinal);
    }

    public void updatePlayerPoints(){
        updateTextView(String.valueOf(board.getPlayerTotal()),R.id.playerPoints);
    }

    private void updateTextView(String toThis, int viewID) {
        TextView textView = (TextView) findViewById(viewID);
        textView.setText(toThis);
    }
    protected abstract int getLayoutResourceId();
}

