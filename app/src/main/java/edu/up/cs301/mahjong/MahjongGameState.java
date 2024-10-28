package edu.up.cs301.mahjong;

import java.util.ArrayList;
import java.util.Arrays;

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

	private int playerID;
	private boolean isTurn;
	private boolean isPair;
	private boolean isSet;
	private int numSets;
	private int numPairs;
	private MahjongTiles[] currentHand;
	private MahjongTiles currentDrawnTile;
	private MahjongTiles lastDiscarded;
	private ArrayList<MahjongTiles> deck; // 136 tiles in a deck
	private String lastDrawnTile;

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
		this.deck = MahjongDeck(this.deck);
		this.lastDrawnTile = "none";
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
		this.currentHand = new MahjongTiles[mgs.currentHand.length];
		this.currentHand = Arrays.copyOf(mgs.currentHand, mgs.currentHand.length);
		this.currentDrawnTile = mgs.currentDrawnTile;
		this.lastDiscarded = mgs.lastDiscarded;
		this.deck = new ArrayList<>();
		copyArrayList(this.deck, mgs.deck); //copy array list using helper method
		this.lastDrawnTile = mgs.lastDrawnTile;

	}

	/**
	 * Helper method for deep copy ctor to copy arrays
	 */
	/*public void copyArray (MahjongTiles[] newArray, MahjongTiles[] origArray) {
		for (int i = 0; i < origArray.length; i++) {
			newArray[i] = origArray[i];
		}
	}*/

	/**
	 * Helper method for deep copy ctor to copy array lists
	 */
	public void copyArrayList (ArrayList<MahjongTiles> newArrayList,
							   ArrayList<MahjongTiles> origArrayList) {
        newArrayList.addAll(origArrayList);
	}

	/**
	 * method that initializes and adds all elements to the classes deck
	 * takes an arraylist, adds all tiles with for loops
	 * to the array list and returns the array list
	 * LJH( use of chatgpt to debug for loops)
	 */
	public ArrayList<MahjongTiles> MahjongDeck(ArrayList<MahjongTiles> theDeck){

		//array of tile suits
		String[] tileSuits = {"Hanzi", "Sticks", "Dots", "Cat", "Earth", "Flower", "Fire",
									"Star", "Water", "Wind"};

		//iterates through all suits
		for(int t = 0; t < 9; t++) {


			if (t > 2){//for non-numbered sets
				for (int q = 1; q < 4; q++) {
					theDeck.add(new MahjongTiles(tileSuits[t], 0));
				}
			}
			else{//for numerical sets
				for(int m = 1; m < 9; m++){
					for(int l = 0; l < 4; l++){
						theDeck.add(new MahjongTiles(tileSuits[t], m));
					}
				}
			}
			}
		return theDeck;

	}

	/**
	 * will start the game
	 * deal the tiles in the deck
	 */
	public void startGame() {
		this.deck = dealTiles(this.deck);

	}

	/**
	 * method that will "deal" the initial tiles
	 * essentially each tile in the deck arraylist is given a number 0-6 indicating its
	 * location on the table (locationNum)
	 * this will randomly draw a tile, if it is not in the draw pile, it will try to draw again
	 *
	 * * @param theDeck
	 * @return
	 */
	public ArrayList<MahjongTiles> dealTiles(ArrayList<MahjongTiles> theDeck){

		int randIndex = 0;

		//TODO: review this code and fix potential infinite loop

//		for(int q = 1; q < 4; q++){
//
//			for (int s = 1; s < 13; s++) {
//				randIndex = (int) (136 * Math.random());
//				while (theDeck.get(randIndex).getLocationNum() != 0){
//					randIndex = (int) (136 * Math.random());
//				}
//				theDeck.get(randIndex).setLocationNum(q);
//			}
//		}
			return theDeck;
	}



	/**
	 * Discard tile action
	 */
	public boolean makeDiscardAction (MahjongDiscardTileAction action) {
		if (action instanceof MahjongDiscardTileAction) {

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
//			this.currentDrawnTile = action.getDrawnTile();
			boolean cardDrawn = false;
			MahjongTiles currTile;

			while (!cardDrawn) {
				currTile = deck.get((int) (Math.random() * 100.0));
				if (currTile.isCanDraw()) {
					lastDrawnTile = currTile.toString();
					currTile.setCanDraw(false);
					cardDrawn = true;
				}
			}

			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Chow action method which adds the chow'd tile to the current hand array.
	 *
	 * @param action - the action occuring
	 */
	public boolean makeChowAction (MahjongChowAction action) {
		if (action instanceof MahjongChowAction) {
			//Add chow'd tile to the array
			//replace 2 with an indexChow
			this.currentHand[2] = action.getChowTile();
			//The player will need to discard a tile after

			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * See pile action
	 * Switches from default view to table view and vice versa
	 */
	public boolean makeSwitchViewAction (MahjongSwitchViewAction action) {
		if (action instanceof MahjongSwitchViewAction) {
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
			return "" + deck.get(index).toString();
		}
		else {
			return deckToString(deck, index - 1);
		}
	}

	/**
	* This is a  method that will return a string of all of the tiles
	* in a specified hand.
	*
	* @param hand - the hand of tiles to be printed
	 * @return all of the tiles of the hand as a string
	*
	*/
	public String handToString(MahjongTiles[] hand, int index) {
		//There was an issue with the recursion, we will come back to this
		/*if (index > 0) {
			return handToString(hand, index - 1);
		}
		else {
			return hand[index].toString();
		}*/
		String listTiles = "";

		for (int i = 0; i < hand.length; i++) {
			listTiles = listTiles + hand[i].toString();
		}

		return listTiles;
	}

	/**
	 * Getter method for lastDrawnTile
	 */
	public String getLastDrawnTile () {
		return this.lastDrawnTile;
	}
}
