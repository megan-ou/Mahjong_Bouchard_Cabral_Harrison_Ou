package edu.up.cs301.mahjong.tiles;

import java.io.Serializable;

/**
 * @author Jazmine Cabral
 * @author Megan Ou
 * @version October 2024
 *
 * Miscellaneous Symbols suit
 */

public class SymbolsTile extends MahjongTile implements Serializable {
    /**
     * Constructor
     *
     * @param suit
     * Value is 0 because symbol tiles have no value, only for matching
     **/
    public SymbolsTile(String suit) {
        super(suit, 0);
    }

}
