package edu.up.cs301.mahjong.tiles;

/**
 * @author Jazmine Cabral, Megan Ou
 * @version October 2024
 *
 * Abstract class for tile object with suit, value, and boolean values to detect if it
 * is a part of a set and can be drawn
 */
public class MahjongTiles {

    /** Instance Variables **/
    private String suit;
    private int value;
    private boolean partOfSet;
    private boolean canDraw;
    private boolean isRevealed;

    /**
     * this integer will indicate the location of mahjong tiles
     * allowing for parsing of player hands, discard, and draw pile
     * 0: draw pile
     * 1: player
     * 2: AI 1
     * 3: AI 2
     * 4: AI 3
     * 5: last discarded
     * 6: discard pile
     *
     * potentially create a sorting algorithm, reduces the need for multiple arrays
     *
     */
    private int locationNum;

    /** Constructor **/
    public MahjongTiles(String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.partOfSet = false;  // Default value
        this.canDraw = true;// Default value
        this.isRevealed = false;
        this.locationNum = 0;
    }

    /** Copy Constructor **/
    public MahjongTiles(MahjongTiles orig){
        suit = orig.suit;
        value = orig.value;
        partOfSet = orig.partOfSet;
        canDraw = orig.partOfSet;
        isRevealed = orig.isRevealed;
        locationNum = orig.locationNum;
    }

    /** Getter Methods **/
    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public boolean isPartOfSet() {
        return partOfSet;
    }

    public boolean isCanDraw(){
        return canDraw;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public int getLocationNum() { return locationNum; }

    /** Setter Methods **/
    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPartOfSet(boolean partOfSet) {
        this.partOfSet = partOfSet;
    }

    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public void setLocationNum(int location) { locationNum = location; }

    /** Returns true if card can be added to a set **/
    public boolean canAddToSet(){
        // If tile is discarded and not part of a set then we can add the tile to a set
        return isPartOfSet();
    }

    /** Returns true if card was discarded and false if not **/
    public boolean discard(){
        // If tile is not been discarded, discard it
        if (canDraw){
            setCanDraw(false);
            return true; //Card was successfully discarded
        }
        return false; // Card was not discarded
    }

    /** Returns true if the tiles are equal **/
    public boolean isEquals(MahjongTiles other) {
        if (other == null) return false;
        return this.suit == other.suit && this.value == other.value;
    }

    /** Converts tile data to a string **/
    public String toString() {
        if (getValue() == 0) {
            return getSuit();
        }
        else {
            return getValue() + " of " + getSuit();
        }
    }

    //public abstract void displayTile();
}
