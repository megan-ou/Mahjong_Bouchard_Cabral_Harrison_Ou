package edu.up.cs301.mahjong.tiles;

import java.io.Serializable;

/**
 * @author Jazmine Cabral, Megan Ou
 * @version October 2024
 *
 * Abstract class for tile object with suit, value, and boolean values to detect if it
 * is a part of a set and can be drawn
 */
public class MahjongTile implements Serializable {

    /** Instance Variables **/
    private String suit;
    private int value;
    /**
     * this integer will indicate the location of mahjong tiles
     * allowing for parsing of player hands, discard, and draw pile
     * 0: draw pile
     * 1: player 1
     * 2: player 2
     * 3: player 3
     * 4: player 4
     * 5: discard pile
     */
    private int locationNum;
    /**
     * integer to check status of tile
     * 0: lone tile
     * 1: part of pair
     * 2: part of set
     * 3: part of set and is revealed on table
     */
    private int tileStatus;

    /** Constructor **/
    public MahjongTile(String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.locationNum = 0;
        this.tileStatus = 0;
    }

    /** Copy Constructor **/
    public MahjongTile(MahjongTile orig){
        suit = orig.suit;
        value = orig.value;
        locationNum = orig.locationNum;
        tileStatus = orig.tileStatus;
    }

    /** Getter Methods **/
    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public int getLocationNum() { return locationNum; }

    public int getTileStatus() {
        return tileStatus;
    }

    /** Setter Methods **/
    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLocationNum(int location) { locationNum = location; }

    public void setTileStatus(int tileStatus) {
        this.tileStatus = tileStatus;
    }

    /** Returns true if the tiles are equal **/
    public boolean isEquals(MahjongTile other) {
        if (other == null) return false;
        return this.suit == other.suit && this.value == other.value;
    }

    /** Converts tile data to a string **/
    public String toString() {
        if (getValue() == 0) {
            return this.suit;
        }
        else {
            return (this.value + " of " + this.suit);
        }
    }

    //public abstract void displayTile();
}
