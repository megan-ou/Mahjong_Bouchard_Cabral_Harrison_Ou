package edu.up.cs301.mahjong;

import junit.framework.TestCase;

import java.util.ArrayList;

import edu.up.cs301.mahjong.tiles.HanziTiles;
import edu.up.cs301.mahjong.tiles.MahjongTiles;

import edu.up.cs301.mahjong.tiles.MahjongTiles;

public class MahjongGameStateTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {
    }

    public void testCopyArray1() {
        MahjongGameState state = new MahjongGameState();
        MahjongTiles[] originalArray;
        MahjongTiles[] copyArray;
        originalArray = new MahjongTiles[]{
                new MahjongTiles("bamboo", 2)
        };
        assertNotNull(originalArray);
    }

    public void testCopyArray2(){
        MahjongGameState state = new MahjongGameState();
        MahjongTiles[] originalArray;
        MahjongTiles[] copyArray;
        originalArray = new MahjongTiles[]{
                new MahjongTiles("bamboo", 2),
                new MahjongTiles("character", 5),
                new MahjongTiles("circle", 7)
        };
        copyArray = new MahjongTiles[originalArray.length];

        state.copyArray(copyArray, originalArray);

        // Change on of the elements in the array to then get an error that it did not opy right
        copyArray[0].setValue(5);
        copyArray[0].setSuit("circle");

        for (int i = 0; i < originalArray.length; i++) {
            assertEquals(originalArray[i].getSuit(), copyArray[i].getSuit());
            assertEquals(originalArray[i].getValue(), copyArray[i].getValue());
            assertNotSame(originalArray[i], copyArray[i]);
        }
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
        MahjongGameState gameState = new MahjongGameState();

        gameState.setPlayerID(2);

        assertEquals(gameState.getPlayerID(), 2);
    }

    //Megan
    public void test1SetPlayerOneHand() {
        MahjongGameState mgs = new MahjongGameState();

        mgs.setPlayerOneHand(0,new HanziTiles(3));

        HanziTiles hanzi3 = new HanziTiles(3);

        assertEquals("Hanzi", mgs.getPlayerOneHand()[0].getSuit());
    }

    //Landon
    public void test2SetPlayerOneHand() {
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