package edu.up.cs301.mahjong.tiles;

import java.io.Serializable;

/**
 * @author Jazmine Cabral
 * @version October 2024
 */

public class HanziTile extends MahjongTile implements Serializable {
    /**
     * Constructor
     *
     * @param value
     **/
    public HanziTile(int value) {
        super("Hanzi", value);
    }

}
