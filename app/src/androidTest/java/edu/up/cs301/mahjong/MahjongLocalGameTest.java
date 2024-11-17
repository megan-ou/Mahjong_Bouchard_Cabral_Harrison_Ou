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

        //check if game is over given a winning hand

        //set a winning hand

        state.setPlayerOneHand(0, new HanziTile(1));
        state.setPlayerOneHand(1, new HanziTile(2));
        state.setPlayerOneHand(2, new HanziTile(3));

        state.setPlayerOneHand(3, new HanziTile(4));
        state.setPlayerOneHand(4, new HanziTile(5));
        state.setPlayerOneHand(5, new HanziTile(6));

        state.setPlayerOneHand(6, new HanziTile(7));
        state.setPlayerOneHand(7, new HanziTile(8));
        state.setPlayerOneHand(8, new HanziTile(9));

        state.setPlayerOneHand(9, new DotsTile(2));
        state.setPlayerOneHand(10, new DotsTile(2));
        state.setPlayerOneHand(11, new DotsTile(2));

        state.setPlayerOneHand(12, new SymbolsTile("Flower"));
        state.setPlayerOneHand(13, new SymbolsTile("Flower"));

        String winner = lg.checkIfGameOver();

        assertEquals("Player 0 has won!!! Yippee!",winner);

    }
}