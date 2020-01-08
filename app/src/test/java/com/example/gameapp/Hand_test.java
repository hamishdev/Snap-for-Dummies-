package com.example.gameapp;

import com.example.gameapp.Model.Card;
import com.example.gameapp.Model.CardCollections.Hand;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Hand_test {
    Hand emptyHand;
    Card aceofSpades;
    Hand oneCardHand;
    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        emptyHand = new Hand();
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
        emptyHand = null;
        oneCardHand = null;
    }

    @Test
    public void testHandEmpty() {
        assertEquals("Empty list should have 0 elements", 0, emptyHand.size());
    }

    @Test
    public void testAddingCard() {
        emptyHand.add(aceofSpades);
        assertEquals("should have one card",1,emptyHand.size());
    }

    @Test
    public void test_movingCard() {
        assertEquals(oneCardHand.size(),1);
        assertEquals(emptyHand.size(),0);
        oneCardHand.move(oneCardHand.getTopCard(),emptyHand);
        assertEquals(oneCardHand.size(),0);
        assertEquals(emptyHand.size(),1);
    }

    }