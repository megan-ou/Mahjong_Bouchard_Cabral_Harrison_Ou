package edu.up.cs301.mahjong;

import junit.framework.TestCase;

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