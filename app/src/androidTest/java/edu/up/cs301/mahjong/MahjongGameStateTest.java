package edu.up.cs301.mahjong;

import junit.framework.TestCase;

import edu.up.cs301.mahjong.tiles.HanziTile;
import edu.up.cs301.mahjong.tiles.MahjongTile;

public class MahjongGameStateTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {
    }

    // Jazmine Cabral
    public void testCopyArray1() {
        MahjongGameState state = new MahjongGameState();
        MahjongTile[] originalArray;
        MahjongTile[] copyArray;
        originalArray = new MahjongTile[]{
                new MahjongTile("bamboo", 2)
        };
        assertNotNull(originalArray);
    }

    // Jazmine Cabral
    public void testCopyArray2(){
        MahjongGameState state = new MahjongGameState();
        MahjongTile[] originalArray;
        MahjongTile[] copyArray;
        originalArray = new MahjongTile[]{
                new MahjongTile("bamboo", 2),
                new MahjongTile("character", 5),
                new MahjongTile("circle", 7)
        };
        copyArray = new MahjongTile[originalArray.length];

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

        //ArrayList<MahjongTile> testDeck = mgs.mahjongDeck(mgs.getDeck());

        //int deckSize = testDeck.size();

        //assertEquals(135,deckSize);
        mgs.mahjongDeck(mgs.getDeck());

        for (int i = 0; i < mgs.getDeck().length; i++) {
            assertNotNull(mgs.getDeck()[i]);
            System.out.println(mgs.getDeck()[i].toString());
        }



    }

    public void testStartGame() {
    }

    public void testDealTiles() {
        //does it work?

        MahjongGameState mgs = new MahjongGameState();
        mgs.dealTiles();

        assertEquals(2,mgs.getPlayerTwoHand()[0].getLocationNum());

        MahjongTile testTile = new MahjongTile(mgs.getPlayerTwoHand()[0]);

        mgs.dealTiles();

        assertEquals(mgs.getPlayerTwoHand()[0].getLocationNum(),testTile.getLocationNum());

        //this test should fail
        //assertEquals(mgs.getPlayerTwoHand()[0],testTile);

        //passed test commented out
//        for (int i = 0; i < 13; i++) {
//            assertNotNull(mgs.getPlayerOneHand()[i]);
//            System.out.println(mgs.getPlayerOneHand()[i].toString());
//            assertNotNull(mgs.getPlayerTwoHand()[i]);
//            System.out.println(mgs.getPlayerTwoHand()[i].toString());
//            assertNotNull(mgs.getPlayerThreeHand()[i]);
//            System.out.println(mgs.getPlayerThreeHand()[i].toString());
//            assertNotNull(mgs.getPlayerFourHand()[i]);
//            System.out.println(mgs.getPlayerFourHand()[i].toString());
//        }
//
//        assertNull(mgs.getPlayerOneHand()[13]);
//        assertNull(mgs.getPlayerTwoHand()[13]);
//        assertNull(mgs.getPlayerThreeHand()[13]);
//        assertNull(mgs.getPlayerFourHand()[13]);

    }

    public void testMakeDiscardAction() {
        MahjongGameState mgs = new MahjongGameState();
        MahjongComputerPlayer1 player = new MahjongComputerPlayer1("Jaz");
        MahjongDiscardTileAction discardAction = new MahjongDiscardTileAction(player);
        boolean isDiscardAction = mgs.makeDiscardAction(discardAction);
        assertTrue(isDiscardAction);

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

    // Jazmine Cabral
    public void testMakeSwitchViewAction() {
        MahjongGameState state = new MahjongGameState();
        MahjongComputerPlayer1 player1 = new MahjongComputerPlayer1("Jaqcui");
        MahjongSwitchViewAction viewAction = new MahjongSwitchViewAction(player1);
        boolean test = state.makeSwitchViewAction(viewAction);
        assertTrue(test);
        
    }

    public void testSortDeck(){

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
        MahjongGameState mgs = new MahjongGameState();
        MahjongTile lastDrawn = new MahjongTile("Hanzi", 4);
        mgs.setLastDrawnTile(lastDrawn.getSuit());
        assertEquals("Hanzi", lastDrawn.getSuit());
    }

    public void testSetNumPairs() {
        MahjongGameState mgs = new MahjongGameState();
        mgs.setNumPairs(5);
        int num = mgs.getNumPairs();
        assertEquals(5, num);
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

        mgs.setPlayerOneHand(0,new HanziTile(3));

        HanziTile hanzi3 = new HanziTile(3);

        assertEquals("Hanzi", mgs.getPlayerOneHand()[0].getSuit());
    }

    //Landon
    public void test2SetPlayerOneHand() {
        MahjongGameState gameState = new MahjongGameState();
        MahjongTile newTile = new MahjongTile("Hanzi", 3);

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