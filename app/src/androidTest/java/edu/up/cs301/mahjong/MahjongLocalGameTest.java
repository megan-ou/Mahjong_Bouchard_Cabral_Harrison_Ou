package edu.up.cs301.mahjong;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.up.cs301.mahjong.tiles.DotsTile;
import edu.up.cs301.mahjong.tiles.HanziTile;
import edu.up.cs301.mahjong.tiles.MahjongTile;
import edu.up.cs301.mahjong.tiles.SymbolsTile;

public class MahjongLocalGameTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkIfGameOver() {
        MahjongGameState state = new MahjongGameState();
        MahjongLocalGame lg = new MahjongLocalGame(state);

        state.setPlayerID(0);

        //check if game is over given a winning hand

        //set a winning hand (tested with different versions/combinations of hands and players)

        state.setPlayerOneHand(0, new HanziTile(6));
        state.setPlayerOneHand(1, new HanziTile(7));
        state.setPlayerOneHand(2, new HanziTile(9));

        state.setPlayerOneHand(3, new HanziTile(9));
        state.setPlayerOneHand(4, new HanziTile(9));

        state.setPlayerOneHand(5, new DotsTile(4));
        state.setPlayerOneHand(6, new DotsTile(6));
        state.setPlayerOneHand(7, new DotsTile(9));

        state.setPlayerOneHand(8, new SymbolsTile("Water"));
        state.setPlayerOneHand(9, new SymbolsTile("Water"));
        state.setPlayerOneHand(10, new SymbolsTile("Water"));

        state.setPlayerOneHand(11, new SymbolsTile("Earth"));
        state.setPlayerOneHand(12, new SymbolsTile("Earth"));
        state.setPlayerOneHand(13, new SymbolsTile("Earth"));
    }


    @Test
    public void testInfiniteDraw() {
        MahjongGameState mgs = new MahjongGameState();
        MahjongLocalGame mlg = new MahjongLocalGame(mgs);

        boolean works = false;
        for (int i = 0; i < 100; i++) {
            //works = mlg.testMethod(); testMethod() class is commented out below
            assertTrue(works);
        }
    }

//    /**
//     * temp test method, meant to be in local game class
//     * @return
//     */
//    public boolean testMethod() {
////            gameState.reshuffleDiscard();
////        }
////
////        hasDrawnTile = false;
////        MahjongTile discTile;
////        //keep randomly selecting a tile until an unused tile is drawn
////        while (!hasDrawnTile) {
////            drawnTile = gameState.getDeck()[(int) (Math.random() * 135.0)];
////            if (drawnTile.getLocationNum() == 0) {
////                gameState.setCurrentDrawnTile(drawnTile);
////                drawnTile.setLocationNum(gameState.getPlayerID() + 1);
////                hasDrawnTile = true;
////            }
////
////            //discard a random tile
////            discTile = gameState.getDeck()[(int) (Math.random() * 135.0)];
////
////            if (discTile.getLocationNum() != 0) {
////                discTile.setLocationNum(5);
////            }
////        }
////        if (hasDrawnTile) {
////            return true;
////        }
//        return false;
//    }

}