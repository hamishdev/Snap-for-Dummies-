package com.example.gameapp;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.CardCollections.Hand;
import com.example.gameapp.Model.Deck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Deck_test {
    Deck emptyDeck;
    Card aceofSpades;
    Hand oneCardHand;
    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        emptyDeck = new Deck();
        aceofSpades = new Card(Card.Value.ACE, Card.Suit.Spades);
        oneCardHand = new Hand();
        oneCardHand.add(new Card(Card.Value.ACE, Card.Suit.Spades));
    }

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        emptyDeck = null;
        oneCardHand = null;
    }

    @Test
    //Deal makes 26 cards in player hand
    public void test_deal(){
        assertEquals(emptyDeck.getFreshDeck().size(),52);
        assertEquals(emptyDeck.getPlayerHand().size(),0);
        emptyDeck.deal();
        assertEquals(emptyDeck.getPlayerHand().size(),26);

    }

    @Test
    public void test_snap(){
        emptyDeck.getPlayingPile().add(aceofSpades);
        assertTrue(emptyDeck.checkSnap());
    }

    }