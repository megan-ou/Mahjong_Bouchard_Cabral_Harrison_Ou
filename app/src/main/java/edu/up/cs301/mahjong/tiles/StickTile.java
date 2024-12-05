package edu.up.cs301.mahjong.tiles;

import java.io.Serializable;

/**
 * @author Jazmine Cabral
 * @version October 2024
 *
 * Suit of Sticks
 */

public class StickTile extends MahjongTile implements Serializable {
    /**
     * Constructor
     *
     * @param value
     **/
    public StickTile(int value) {
        super("Sticks", value);
    }

}
