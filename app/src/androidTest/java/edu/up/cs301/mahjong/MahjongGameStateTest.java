package edu.up.cs301.mahjong;

import junit.framework.TestCase;

import java.lang.invoke.MethodHandle;

import edu.up.cs301.mahjong.tiles.DotsTile;
import edu.up.cs301.mahjong.tiles.HanziTile;
import edu.up.cs301.mahjong.tiles.MahjongTile;
import edu.up.cs301.mahjong.tiles.StickTile;
import edu.up.cs301.mahjong.tiles.SymbolsTile;

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

        // Change one of the elements in the array to then get an error that it did not opy right
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

        // Set up a test deck and populate it
        MahjongGameState state = new MahjongGameState();
        MahjongTile[] deck = new MahjongTile[136];
        MahjongTile[] populatedDeck = state.mahjongDeck(deck);

        // Test that the deck is not null and has 136 tiles
        assertNotNull("The deck should not be null", populatedDeck);
        assertEquals("The deck should contain 136 tiles", 136, populatedDeck.length);

        // A count for each suit
        int hanziCount = 0;
        int dotsCount = 0;
        int stickCount = 0;
        int symbolsCount = 0;

        // Count the number of tiles per suit and update the count
        for (MahjongTile tile : populatedDeck) {
            if (tile instanceof HanziTile) {
                hanziCount++;
            } else if (tile instanceof DotsTile) {
                dotsCount++;
            } else if (tile instanceof StickTile) {
                stickCount++;
            } else if (tile instanceof SymbolsTile) {
                symbolsCount++;
            }
        }


        assertEquals("There should be 36 Hanzi tiles", 36, hanziCount);
        assertEquals("There should be 36 Dots tiles", 36, dotsCount);
        assertEquals("There should be 36 Stick tiles", 36, stickCount);

        // Each of the 7 unique symbol tiles should appear 4 times (7 * 4 = 28)
        assertEquals("There should be 28 Symbols tiles", 28, symbolsCount);

    }

    public void testSortHand() {
        MahjongGameState mgs = new MahjongGameState();
        mgs.mahjongDeck(mgs.getDeck());
        mgs.dealTiles();

        for (int i = 0; i < mgs.getPlayerOneHand().length - 1; i++) {
            System.out.println(mgs.getPlayerOneHand()[i].toString());
        }

        mgs.sortHand(mgs.getPlayerOneHand());

        System.out.println("-----------------------------------------------");


        for (int i = 0; i < mgs.getPlayerOneHand().length - 1; i++) {
            System.out.println(mgs.getPlayerOneHand()[i].toString());
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
        MahjongGameState state = new MahjongGameState();
        state.dealTiles();

        for (int i = 0; i < state.getDeck().length; i++) {
            System.out.println(state.getDeck()[i].getLocationNum());
        }

        System.out.println("-----------------------------------------------");

        state.sortDeck();

        for (int i = 0; i < state.getDeck().length; i++) {
            System.out.println(state.getDeck()[i].getLocationNum());
        }


//        int locationNumPlus = 0;
//        int locationNumMinus = 0;
//        for(int q = 1; q < 134; q++){
//
//            locationNumMinus = state.getDeck()[q-1].getLocationNum();
//            locationNumPlus = state.getDeck()[q+1].getLocationNum();
//            assertFalse(state.getDeck()[q].getLocationNum() <=
//                    state.getDeck()[q-1].getLocationNum());
//            if(state.getDeck()[q].getLocationNum() <=
//                    state.getDeck()[q-1].getLocationNum()){
//                System.out.println("not in order: test1 " + locationNumMinus);
//            }
//            assertFalse(state.getDeck()[q].getLocationNum() >=
//                    state.getDeck()[q+1].getLocationNum());
//            if(state.getDeck()[q].getLocationNum() >=
//                    state.getDeck()[q+1].getLocationNum()){
//                System.out.println("not in order: test2 " + locationNumPlus);
//            }
//        }

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