package com.example.gameapp;


import com.example.gameapp.Model.Level;
import com.example.gameapp.Model.Card;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DeckController_test {

    Level emptyLevel;

    @Before
    public void setUp() {
        emptyLevel = new Level();

    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        emptyLevel = null;
    }

    @Test
    public void test_deck() {
        Assert.assertEquals(emptyLevel.getDeck().getPlayingPile().size(), 0);
        Assert.assertEquals(emptyLevel.getDeck().getFreshDeck().size(),0);
        Assert.assertEquals(emptyLevel.getDeck().getPlayerHand().size(),26);

    }

    @Test
    public void test_turn() {
        Assert.assertTrue(emptyLevel.isPlayersTurn());
    }

    @Test
    public void test_flip(){
        Assert.assertEquals(emptyLevel.getDeck().getFreshDeck().size(),0);
        Assert.assertEquals(emptyLevel.getDeck().getPlayerHand().size(),26);
        emptyLevel.flipFromPlayerHand();
        Assert.assertEquals(emptyLevel.getDeck().getPlayingPile().size(),1);


        Assert.assertEquals(emptyLevel.getDeck().getPlayerHand().size(),25);
    }

    @Test
    public void test_topCardAfterFlip(){
        Assert.assertEquals(emptyLevel.getDeck().getFreshDeck().size(),0);
        Assert.assertEquals(emptyLevel.getDeck().getPlayerHand().size(),26);
        emptyLevel.flipFromPlayerHand();
        Card first = emptyLevel.getDeckController().getPlayingPileTopCard();
        emptyLevel.flipFromPlayerHand();
        Card second = emptyLevel.getDeckController().getPlayingPileTopCard();
        assertNotEquals(first,second);



    }

}
