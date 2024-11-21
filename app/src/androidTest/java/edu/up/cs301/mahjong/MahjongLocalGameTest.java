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

        state.setPlayerID(3);

        //check if game is over given a winning hand

        //set a winning hand

        state.setPlayerFourHand(0, new SymbolsTile("Flower"));
        state.setPlayerFourHand(1, new SymbolsTile("Flower"));

        state.setPlayerFourHand(2, new HanziTile(1));
        state.setPlayerFourHand(3, new HanziTile(1));
        state.setPlayerFourHand(4, new HanziTile(1));

        state.setPlayerFourHand(5, new HanziTile(2));
        state.setPlayerFourHand(6, new HanziTile(3));
        state.setPlayerFourHand(7, new HanziTile(4));

        state.setPlayerFourHand(8, new HanziTile(9));
        state.setPlayerFourHand(9, new HanziTile(9));
        state.setPlayerFourHand(10, new HanziTile(9));

        state.setPlayerFourHand(11, new DotsTile(2));
        state.setPlayerFourHand(12, new DotsTile(2));
        state.setPlayerFourHand(13, new DotsTile(2));



        String winner = lg.checkIfGameOver();

        assertEquals("Player 3 has won!!! Yippee!",winner);

    }
}