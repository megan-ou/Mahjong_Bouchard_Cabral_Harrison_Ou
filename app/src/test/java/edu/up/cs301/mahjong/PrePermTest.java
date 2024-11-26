package edu.up.cs301.mahjong;

import junit.framework.TestCase;

import edu.up.cs301.mahjong.tiles.DotsTile;
import edu.up.cs301.mahjong.tiles.HanziTile;
import edu.up.cs301.mahjong.tiles.MahjongTile;
import edu.up.cs301.mahjong.tiles.StickTile;
import edu.up.cs301.mahjong.tiles.SymbolsTile;

public class PrePermTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testPrePerm() {

        MahjongGameState mgs = new MahjongGameState();
        MahjongTile[] hand = new MahjongTile[14];
        mgs.setEmptyHand(hand);
        mgs.setCurrentDrawnTile(new SymbolsTile("Fire"));

        hand[0] = new HanziTile(1);
        hand[1] = new HanziTile(2);
        hand[2] = new HanziTile(3);
        hand[3] = new StickTile(1);
        hand[4] = new StickTile(2);
        hand[5] = new StickTile(3);
        hand[6] = new DotsTile(1);
        hand[7] = new DotsTile(2);
        hand[8] = new DotsTile(3);
        hand[9] = new HanziTile(5);
        hand[10] = new HanziTile(6);
        hand[11] = new HanziTile(7);
        hand[12] = new SymbolsTile("Fire");

        int results = mgs.prePerm(hand);

//        for(int i = 0; i < hand.length; i++) {
//
//            System.out.println(hand[i].getSuit() + " " + hand[i].getValue());
//        }

        assertEquals(41, results);



    }
}