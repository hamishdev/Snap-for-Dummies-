package com.example.gameapp;


import com.example.gameapp.Model.Board;
import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.CardCollections.Hand;
import com.example.gameapp.Model.Deck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.SecurityPermission;

import static com.example.gameapp.Model.Card.Value.ACE;
import static com.example.gameapp.Model.Card.Value.SEVEN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class Board_test {
    Board emptyBoard;
    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
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
    public void checksnap_test(){
        emptyBoard.getDeck().getPlayingPile().add(0,new Card(ACE, Card.Suit.Spades));
        assertTrue(emptyBoard.playerSnap());
    }

    @Test
    public void checksnap_moveCards_test(){
        assertEquals(emptyBoard.getDeck().getPlayerHand().size(),26);
        emptyBoard.getDeck().getPlayingPile().add(0,new Card(ACE, Card.Suit.Spades));
        assertTrue(emptyBoard.isPlayersTurn());
        assertTrue(emptyBoard.playerSnap());
        assertEquals(emptyBoard.getDeck().getPlayerHand().size(),27);
    }

    @Test
    public void checksnap_fail_test(){
        assertEquals(emptyBoard.getDeck().getPlayerHand().size(),26);
        emptyBoard.flipFromPlayerHand();
        emptyBoard.flipFromComputerHand();
        emptyBoard.getDeck().getPlayingPile().add(0,new Card(SEVEN, Card.Suit.Spades));
        assertTrue(emptyBoard.isPlayersTurn());
        assertEquals(emptyBoard.getDeck().getPlayingPile().size(),3);
        assertFalse(emptyBoard.playerSnap());
        assertEquals(emptyBoard.getDeck().getPlayingPile().size(),0);

        assertEquals(emptyBoard.getDeck().getComputerHand().size(),28);
        assertEquals(emptyBoard.getDeck().getPlayerHand().size(),25);

    }


}
