package edu.up.cs301.mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;
import edu.up.cs301.mahjong.tiles.*;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the counter.
 * 
 * @author Steven R. Vegdahl
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author Landon Harrison
 * @author Megan Ou
 * @version October 2024
 */
public class MahjongGameState extends GameState {
	
	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;

	private final int MAX = 13; //Max number of tiles in a hand

	int playerID;
	boolean isTurn;
	boolean isPair;
	boolean isSet;
	int numSets;
	int numPairs;
	MahjongTiles[] currentHand;
	MahjongTiles currentDrawnTile;
	MahjongTiles lastDiscarded;
	ArrayList<MahjongTiles> deck;

	/**
	 * default ctor
	 */
	MahjongGameState(){
		this.playerID = 0;
		this.isTurn = false;
		this.isPair = false;
		this.isSet = false;
		this.numSets = 0;
		this.numPairs = 0;
		this.currentHand = new MahjongTiles[14]; //need to copy each object to a new array when constructing, example on moodle
		this.currentDrawnTile = null;
		this.lastDiscarded = null;
		this.deck = new ArrayList<>();
	}

	/**
	 * Copy ctor
	 */
	public MahjongGameState(MahjongGameState mgs){
		this.playerID = mgs.playerID;
		this.isTurn = mgs.isTurn;
		this.isPair = mgs.isPair;
		this.isSet = mgs.isSet;
		this.numSets = mgs.numSets;
		this.numPairs = mgs.numPairs;
		this.currentHand = mgs.currentHand;
		this.currentDrawnTile = mgs.currentDrawnTile;
		this.lastDiscarded = mgs.lastDiscarded;
		this.deck = mgs.deck;

	}

	/**
	 * Discard tile action
	 */
	public boolean makeDiscardAction (MahjongDiscardTileAction action, int index) {
		if (action instanceof MahjongDiscardTileAction) {
			currentHand[index].discard();
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Draw tile action
	 */
	public boolean makeDrawTileAction (MahjongDrawTileAction action) {
		if (action instanceof MahjongDrawTileAction) {
			this.currentDrawnTile = action.getDrawnTile();
			return true;
		}
		else {
			return false;
		}
	}

	/**
	* This method describes the state of the game by printing the values of key
	* variables in the MahjongGameState class
	*/
	@Override
	public String toString() {

		return "\nPlayer ID: " + playerID + "\nPlayer's turn? " + isTurn + "\nA pair? " + isPair
				+ "\nA set? " + isSet + "\nNumber of Sets: " + numSets + "\nNumber of Pairs: "
				+ numPairs + "\nCurrent hand: " + handToString(currentHand, MAX)
				+ "\nCurrent Drawn Tile: " + currentDrawnTile.toString() + "\nLast Tile Discarded: "
				+ lastDiscarded.toString() + "\nThe deck: " + deckToString(deck, deck.size());
	}

	/**
	 * This recursive method will return a string of the deck with all mahjong tiles that haven't
	 * been used yet. This method must be passed the size of the array list in order to print
	 * front to back.
	 *
	 * @param deck - the array list to be returned as a string
	 * @param index - the current element of the deck being examined
	 * @return the deck element as a string
	 */
	public String deckToString(ArrayList<MahjongTiles> deck, int index) {
		if (index == 0) {
			return deck.get(index).toString();
		}
		else if (index > 0) {
			return " " + deck.get(index).toString();
		}
		else {
			return deckToString(deck, index - 1);
		}
	}

	/**
	* This is a recursive method that will return a string of all of the tiles
	* in a specified hand. This method needs to first be called with the max number
	* of tiles so that it will print the tiles in order.
	*
	* @param hand - the hand of tiles to be printed
	 * @return all of the tiles of the hand as a string
	*
	*/
	public String handToString(MahjongTiles[] hand, int index) {
		if(index == 0) {
			return hand[index].toString();
		}
		else if(index > 0) {
			return " " + hand[index].toString();
		}
		else {
			return handToString(hand, index - 1);
		}
	}
}
