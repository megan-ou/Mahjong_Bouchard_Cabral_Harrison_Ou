package edu.up.cs301.mahjong.OldCode;

import edu.up.cs301.mahjong.tiles.MahjongTile;

/**
 * A place to hold old code that was scrapped, but we don't want to delete and review later
 * Please don't look at this code
 */
public class OldCode {

    public void sortHand(MahjongTile[] mahjongTiles) {
        //hold the beginning of the players hand as an index
        int fromRefTile = 0;
        MahjongTile holder;

        //iterates through each tile in a players hand
        for(int q = 0; q < 13; q+=(fromRefTile + 1)){
            fromRefTile = 0;
            //sorts the tiles based on suit
            for(int x = q+1; x < 13; x++){
                if(mahjongTiles[x].getSuit().equals(mahjongTiles[q].getSuit())){
                    holder = mahjongTiles[x];
                    mahjongTiles[x] = mahjongTiles[q+1+fromRefTile];
                    mahjongTiles[q+1+fromRefTile] = holder;
                    fromRefTile++;
                }
            }
        }

        for (int i = 0; i < 13; i ++) {
            fromRefTile = 0;

            //sorts the tiles based on value (IE: runs)
            for(int l = i+1; (mahjongTiles[l].getSuit()).equals(mahjongTiles[i].getSuit()); l++){
                if ((mahjongTiles[l].getValue()+1) == mahjongTiles[i].getValue()
                        || (mahjongTiles[l].getValue()) == mahjongTiles[i].getValue()){
                    holder = mahjongTiles[l];
                    mahjongTiles[l] = mahjongTiles[i+1+fromRefTile];
                    mahjongTiles[i+1+fromRefTile] = holder;

                }
            }
        }

    }
}
