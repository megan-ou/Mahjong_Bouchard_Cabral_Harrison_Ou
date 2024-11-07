package edu.up.cs301.mahjong;

import junit.framework.TestCase;

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
    }

    public void testStartGame() {
    }

    public void testDealTiles() {
    }

    public void testMakeDiscardAction() {
    }

    public void testMakeDrawTileAction() {
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
        MahjongGameState gameState = new MahjongGameState();

        String cardInHand =
                gameState.handToString(gameState.getPlayerOneHand(), 2);
        assertNotNull(cardInHand);


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
        MahjongGameState gameState = new MahjongGameState();

        gameState.setPlayerID(2);

        assertEquals(gameState.getPlayerID(), 2);
    }

    public void testSetPlayerOneHand() {
        MahjongGameState gameState = new MahjongGameState();
        MahjongTiles newTile = new MahjongTiles("Hanzi", 3);

        gameState.setPlayerOneHand(0, newTile);

        assertNotNull(gameState.getPlayerOneHand()[0]);

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