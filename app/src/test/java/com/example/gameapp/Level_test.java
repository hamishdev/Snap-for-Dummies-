package com.example.gameapp;


import com.example.gameapp.model.Level;
import com.example.gameapp.model.Card;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.example.gameapp.model.Card.Value.ACE;
import static com.example.gameapp.model.Card.Value.SEVEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Level_test {
    Level emptyLevel;
    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
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
    public void checksnap_test(){
        emptyLevel.getDeck().getPlayingPile().add(0,new Card(ACE, Card.Suit.Spades));
        assertTrue(emptyLevel.playerSnap());
    }

    @Test
    public void checksnap_moveCards_test(){
        assertEquals(emptyLevel.getDeck().getPlayerHand().size(),26);
        emptyLevel.getDeck().getPlayingPile().add(0,new Card(ACE, Card.Suit.Spades));
        assertTrue(emptyLevel.isPlayersTurn());
        assertTrue(emptyLevel.playerSnap());
        assertEquals(emptyLevel.getDeck().getPlayerHand().size(),27);
    }

    @Test
    public void checksnap_fail_test(){
        assertEquals(emptyLevel.getDeck().getPlayerHand().size(),26);
        emptyLevel.flipFromPlayerHand();
        emptyLevel.flipFromComputerHand();
        emptyLevel.getDeck().getPlayingPile().add(0,new Card(SEVEN, Card.Suit.Spades));
        assertTrue(emptyLevel.isPlayersTurn());
        assertEquals(emptyLevel.getDeck().getPlayingPile().size(),3);
        assertFalse(emptyLevel.playerSnap());
        assertEquals(emptyLevel.getDeck().getPlayingPile().size(),0);

        assertEquals(emptyLevel.getDeck().getComputerHand().size(),28);
        assertEquals(emptyLevel.getDeck().getPlayerHand().size(),25);

    }


}
