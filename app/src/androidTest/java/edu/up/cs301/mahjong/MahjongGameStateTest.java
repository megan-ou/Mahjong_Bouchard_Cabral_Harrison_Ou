package edu.up.cs301.mahjong;

import junit.framework.TestCase;

import java.util.ArrayList;

import edu.up.cs301.mahjong.tiles.MahjongTiles;

public class MahjongGameStateTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testCopyArray() {
    }

    public void testCopyArrayList() {
    }

    public void testMahjongDeck() {
        MahjongGameState mgs = new MahjongGameState();

        ArrayList<MahjongTiles> testDeck = mgs.mahjongDeck(mgs.getDeck());

        int deckSize = testDeck.size();

        assertEquals(135,deckSize);

    }

    public void testStartGame() {
    }

    public void testDealTiles() {
    }

    public void testMakeDiscardAction() {
    }

    /**
     * Test to make sure only draw actions can be made in drawTile class
     */
    public void testMakeDrawTileAction() {
        MahjongGameState mgs = new MahjongGameState();

        mgs.factorDrawTileAction();

        assertNotNull(mgs.getCurrentDrawnTile());
    }

    public void testMakeChowAction() {
    }

    public void testMakeSwitchViewAction() {
    }

    public void testTestToString() {
    }

    public void testDeckToString() {
    }

    public void testHandToString() {
    }

    public void testGetLastDrawnTile() {
    }

    public void testGetNumPairs() {
    }

    public void testGetPlayerID() {
    }

    public void testGetDeck() {
    }

    public void testGetPlayerOneHand() {
    }

    public void testGetCurrentDrawnTile() {
    }

    public void testGetNumSets() {
    }

    public void testGetLastDiscarded() {
    }

    public void testGetIsTurn() {
    }

    public void testSetCurrentDrawnTile() {
    }

    public void testSetLastDiscarded() {
    }

    public void testSetLastDrawnTile() {
    }

    public void testSetNumPairs() {
    }

    public void testSetNumSets() {
    }

    public void testSetPlayerID() {
    }

    public void testSetPlayerOneHand() {
    }

    public void testSetPlayerTwoHand() {
    }

    public void testSetPlayerThreeHand() {
    }

    public void testSetPlayerFourHand() {
    }

    public void testSetTurn() {
    }
}