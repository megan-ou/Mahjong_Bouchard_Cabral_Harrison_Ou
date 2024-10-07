package edu.up.cs301.mahjong;

abstract class MahjongTiles {

    /** Instance Variables **/
    private String suit;
    private int value;
    private boolean partOfSet;
    private boolean isDiscarded;
    private boolean canDraw;

    /** Constructor **/
    public MahjongTiles(String suit, int value) {
        this.suit = suit;
        this.value = value;
        this.partOfSet = false;  // Default value
        this.isDiscarded = false; // Default value
        this.canDraw = true; // Default value
    }

    /** Copy Constructor **/
    public MahjongTiles(MahjongTiles orig){
        suit = orig.suit;
        value = orig.value;
        partOfSet = orig.partOfSet;
        isDiscarded = orig.isDiscarded;
        canDraw = orig.partOfSet;
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

    public boolean isDiscarded(){
        return isDiscarded; // True if discarded
    }

    public boolean isCanDraw(){
        return canDraw;
    }

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

    public void setDiscarded(boolean discarded) {
        isDiscarded = discarded;
    }

    public void setCanDraw(boolean canDraw) {
        this.canDraw = canDraw;
    }

    /** Returns true if card can be added to a set **/
    public boolean canAddToSet(){
        // If tile is discarded and not part of a set then we can add the tile to a set
        return isDiscarded() && !isPartOfSet();
    }

    /** Returns true if card was discarded and false if not **/
    public boolean discard(){
        // If tile is not been discarded, discard it
        if (!isDiscarded()){
            setDiscarded(true);
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
        return getSuit() + " of " + getValue();
    }

    public abstract void displayTile();
}
