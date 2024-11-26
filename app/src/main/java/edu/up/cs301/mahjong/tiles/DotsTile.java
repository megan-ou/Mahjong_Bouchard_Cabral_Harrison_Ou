package edu.up.cs301.mahjong.tiles;

import java.io.Serializable;

/**
 * @author Jazmine Cabral
 * @author Megan Ou
 * @version October 2024
 *
 * Suit of dots
 */

public class DotsTile extends MahjongTile implements Serializable {
    /**
     * Constructor
     *
     * @param value
     **/
    public DotsTile(int value) {
        super("Dots", value);
    }

}
