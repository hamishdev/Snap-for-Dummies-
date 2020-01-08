package com.example.gameapp;


import com.example.gameapp.Model.Board;
import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.CardCollections.Hand;
import com.example.gameapp.Model.Deck;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DeckController_test {

    Board emptyBoard;

    @Before
    public void setUp() {
        emptyBoard = new Board();

    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        emptyBoard = null;
    }

    @Test
    public void test_deck() {
        Assert.assertEquals(emptyBoard.getDeck().getPlayingPile().size(), 0);
        Assert.assertEquals(emptyBoard.getDeck().getFreshDeck().size(),0);
        Assert.assertEquals(emptyBoard.getDeck().getPlayerHand().size(),26);

    }

    @Test
    public void test_turn() {
        Assert.assertTrue(emptyBoard.isPlayersTurn());
    }

    @Test
    public void test_flip(){
        Assert.assertEquals(emptyBoard.getDeck().getFreshDeck().size(),0);
        Assert.assertEquals(emptyBoard.getDeck().getPlayerHand().size(),26);
        emptyBoard.flipFromPlayerHand();
        Assert.assertEquals(emptyBoard.getDeck().getPlayingPile().size(),1);


        Assert.assertEquals(emptyBoard.getDeck().getPlayerHand().size(),25);
    }

    @Test
    public void test_topCardAfterFlip(){
        Assert.assertEquals(emptyBoard.getDeck().getFreshDeck().size(),0);
        Assert.assertEquals(emptyBoard.getDeck().getPlayerHand().size(),26);
        emptyBoard.flipFromPlayerHand();
        Card first = emptyBoard.getDeckController().getPlayingPileTopCard();
        emptyBoard.flipFromPlayerHand();
        Card second = emptyBoard.getDeckController().getPlayingPileTopCard();
        assertNotEquals(first,second);



    }

}
