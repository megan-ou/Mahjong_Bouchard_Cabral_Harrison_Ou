package edu.up.cs301.mahjong;

import java.util.ArrayList;
import java.util.Arrays;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.*;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Mahjong game which includes methods and information about
 * specific variables relevant to the game.
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

	private final int MAX_TILES = 14; //Max number of tiles in a hand

	private int playerID;
	private boolean isTurn;
	private int numSets;
	private int numPairs;
	private MahjongTiles[] playerOneHand;
	private MahjongTiles[] playerTwoHand;
	private MahjongTiles[] playerThreeHand;
	private MahjongTiles[] playerFourHand;
	private MahjongTiles currentDrawnTile;
	private MahjongTiles lastDiscarded;
	private MahjongTiles[] deck; // 136 tiles in a deck
	private String lastDrawnTile;

	/**
	 * default ctor
	 */
	public MahjongGameState(){
		this.playerID = 0;
		this.isTurn = false;
		this.numSets = 0;
		this.numPairs = 0;
		this.playerOneHand = new MahjongTiles[14]; //need to copy each object to a new array when constructing, example on moodle
		this.playerTwoHand = new MahjongTiles[14];
		this.playerThreeHand = new MahjongTiles[14];
		this.playerFourHand = new MahjongTiles[14];
		this.currentDrawnTile = null;
		this.deck = new MahjongTiles[136];
		this.deck = mahjongDeck(this.deck);
		this.lastDiscarded = null;
		this.lastDrawnTile = "none";
	}

	/**
	 * Copy ctor
	 */
	public MahjongGameState(MahjongGameState mgs){
		this.playerID = mgs.playerID;
		this.isTurn = mgs.isTurn;
		this.numSets = mgs.numSets;
		this.numPairs = mgs.numPairs;
		this.playerOneHand = new MahjongTiles[mgs.playerOneHand.length];
		copyArray(mgs.playerOneHand,this.playerOneHand);
		this.playerTwoHand = new MahjongTiles[mgs.playerTwoHand.length];
		copyArray(mgs.playerTwoHand,this.playerTwoHand);
		this.playerThreeHand = new MahjongTiles[mgs.playerThreeHand.length];
		copyArray(mgs.playerThreeHand,this.playerThreeHand);
		this.playerFourHand = new MahjongTiles[mgs.playerFourHand.length];
		copyArray(mgs.playerFourHand,this.playerFourHand);
		this.currentDrawnTile = mgs.currentDrawnTile;
		this.lastDiscarded = mgs.lastDiscarded;
		this.deck = new MahjongTiles[mgs.getDeck().length];
		copyArray(this.deck, mgs.deck);
		this.lastDrawnTile = mgs.lastDrawnTile;

	}

	/**
	 * Helper method for deep copy ctor to copy arrays
	 */
	public void copyArray (MahjongTiles[] newArray, MahjongTiles[] origArray) {
		for (int i = 0; i < origArray.length; i++) {
			newArray[i] = new MahjongTiles(origArray[i].getSuit(), origArray[i].getValue());
		}
	}

	/**
	 * Helper method for deep copy ctor to copy array lists
	 */
	public void copyArrayList (ArrayList<MahjongTiles> newArrayList,
							   ArrayList<MahjongTiles> origArrayList) {
        newArrayList.addAll(origArrayList);
	}

	/**
	 * Helper method that clears an array
	 */
	public void clearHand(MahjongTiles[] hand) {
		for (int i = 0 ; i < hand.length; i++) {
			hand[i] = null;
		}
	}

	/**
	 * method that initializes and adds all elements to the classes deck
	 * takes an arraylist, adds all tiles with for loops
	 * to the array list and returns the array list
	 * LJH( use of chatgpt to debug for loops)
	 */
	public MahjongTiles[] mahjongDeck(MahjongTiles[] deck){
		clearHand(deck);

		//index of entire deck array, should cap at 135
		int deckIndex = 0;

		//creates 4 copies of each tile
		for (int i = 0; i < 4; i++) {
			//create tiles in each of three suits: hanzi, dots, sticks
			for (int j = 0; j < 9; j++) {
				deck[deckIndex] = new HanziTiles(j+1);
				deckIndex++;
				deck[deckIndex] = new DotsTiles(j+1);
				deckIndex++;
				deck[deckIndex] = new StickTiles(j+1);
				deckIndex++;
			}

			//create all 7b unique symbol tiles
			//all symbol tiles have a value of 0
			deck[deckIndex] = new SymbolsTiles("Earth");
			deckIndex++;
			deck[deckIndex] = new SymbolsTiles("Fire");
			deckIndex++;
			deck[deckIndex] = new SymbolsTiles("Water");
			deckIndex++;
			deck[deckIndex] = new SymbolsTiles("Wind");
			deckIndex++;
			deck[deckIndex] = new SymbolsTiles("Flower");
			deckIndex++;
			deck[deckIndex] = new SymbolsTiles("Star");
			deckIndex++;
			deck[deckIndex] = new SymbolsTiles("Cat");
			deckIndex++;
		}


//		//array of tile suits
//		String[] tileSuits = {"Hanzi", "Sticks", "Dots", "Cat", "Earth", "Flower", "Fire",
//									"Star", "Water", "Wind"};
//
//		//iterates through all suits
//		for(int t = 0; t < 9; t++) {
//
//
//			if (t > 2){//for non-numbered sets
//				for (int q = 0; q < 4; q++) {
//					theDeck.add(new MahjongTiles(tileSuits[t], 0));
//				}
//			}
//			else{//for numerical sets
//				for(int m = 1; m < 9; m++){
//					for(int l = 0; l < 4; l++){
//						theDeck.add(new MahjongTiles(tileSuits[t], m));
//					}
//				}
//			}
//			}
		return deck;

	}

	/**
	 * will start the game
	 * deal the tiles in the deck
	 */
	public void startGame() {
		//this.deck = dealTiles(this.deck);

	}

	/**
	 * method that will deal the 13 initial tiles to all 4 players
	 * If a tile is dealt, set locationNum variable to player ID (1-4)
	 * this will randomly draw a tile, if it is not in the draw pile (Location 0), it will  draw again
	 */
	public void dealTiles() {
		//set all hands to null
		clearHand(playerOneHand);
		clearHand(playerTwoHand);
		clearHand(playerThreeHand);
		clearHand(playerFourHand);

		//deal tiles to player 1
		int numDrawn = 0;
		int randIndex;

		while (numDrawn < 13) {
			randIndex = (int)(Math.random() * 135.0);
			if (deck[randIndex].getLocationNum() == 0) {
				deck[randIndex].setLocationNum(1); //tile location in deck set to player 1
				playerOneHand[numDrawn] = deck[randIndex]; //shallow copy, we want both to point at same object
				numDrawn ++;
			}
		}

		//deal tiles to player 2
		numDrawn = 0;

		while (numDrawn < 13) {
			randIndex = (int)(Math.random() * 135.0);
			if (deck[randIndex].getLocationNum() == 0) {
				deck[randIndex].setLocationNum(2); //tile location in deck set to player 2
				playerTwoHand[numDrawn] = deck[randIndex]; //shallow copy, we want both to point at same object
				numDrawn ++;
			}
		}

		//deal tiles to player 3
		numDrawn = 0;

		while (numDrawn < 13) {
			randIndex = (int)(Math.random() * 135.0);
			if (deck[randIndex].getLocationNum() == 0) {
				deck[randIndex].setLocationNum(3); //tile location in deck set to player 3
				playerThreeHand[numDrawn] = deck[randIndex]; //shallow copy, we want both to point at same object
				numDrawn ++;
			}
		}

		//deal tiles to player 4
		numDrawn = 0;

		while (numDrawn < 13) {
			randIndex = (int)(Math.random() * 135.0);
			if (deck[randIndex].getLocationNum() == 0) {
				deck[randIndex].setLocationNum(4); //tile location in deck set to player 4
				playerFourHand[numDrawn] = deck[randIndex]; //shallow copy, we want both to point at same object
				numDrawn ++;
			}
		}
	} //dealTiles()



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
	public boolean makeDrawTileAction (GameAction action) {
		if (action instanceof MahjongDrawTileAction) {
//			this.currentDrawnTile = action.getDrawnTile();
			factorDrawTileAction();
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Factored code for testing
	 */
	public void factorDrawTileAction() {
		boolean cardDrawn = false;
		while (!cardDrawn) {
			currentDrawnTile = deck[(int) (Math.random() * 135.0)];
			if (currentDrawnTile.getLocationNum() == 0) {
				lastDrawnTile = currentDrawnTile.toString();
				currentDrawnTile.setLocationNum(playerID);
				cardDrawn = true;
			}
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
			this.playerOneHand[2] = action.getChowTile();
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

		return "\nPlayer ID: " + playerID + "\nPlayer's turn? " + isTurn + "\nNumber of Sets: "
				+ numSets + "\nNumber of Pairs: " + numPairs + "\nCurrent hand: "
				+ handToString(playerOneHand, MAX_TILES) + "\nCurrent Drawn Tile: "
				+ lastDrawnTile + "\nLast Tile Discarded: " + lastDiscarded.toString()
				+ "\nThe deck: " + deckToString(deck, deck.length);
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
	public String deckToString(MahjongTiles[] deck, int index) {
		String listTiles = "";

		for (int i = 0; i < deck.length; i++) {
			listTiles = listTiles + deck[i].toString();
		}
		return listTiles;
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

//		for (int i = 0; i < hand.length; i++) {
//			listTiles = listTiles + hand[i].toString();
//		} TEMPORARY COMMENT OUT BECAUSE HAND IS NULL

		listTiles = "null hand";

		return listTiles;
	}

	/**
	 * Getter Methods
	 */
	public String getLastDrawnTile () {
		return this.lastDrawnTile;
	}

	public int getNumPairs() {
		return numPairs;
	}

	public int getPlayerID() {
		return playerID;
	}

	public MahjongTiles[] getDeck() {
		return deck;
	}

	public MahjongTiles[] getPlayerOneHand() {
		return playerOneHand;
	}

	public MahjongTiles[] getPlayerTwoHand() {
		return playerTwoHand;
	}

	public MahjongTiles[] getPlayerThreeHand() {
		return playerThreeHand;
	}

	public MahjongTiles[] getPlayerFourHand() {
		return playerFourHand;
	}

	public MahjongTiles getCurrentDrawnTile() {
		return currentDrawnTile;
	}

	public int getNumSets() {
		return numSets;
	}

	public MahjongTiles getLastDiscarded() {
		return lastDiscarded;
	}
	public boolean getIsTurn(){
		return isTurn;
	}

	/**
	 * Setter Methods
	 */
	public void setCurrentDrawnTile(MahjongTiles currentDrawnTile) {
		this.currentDrawnTile = currentDrawnTile;
	}

	public void setLastDiscarded(MahjongTiles lastDiscarded) {
		this.lastDiscarded = lastDiscarded;
	}

	public void setLastDrawnTile(String lastDrawnTile) {
		this.lastDrawnTile = lastDrawnTile;
	}

	public void setNumPairs(int numPairs) {
		this.numPairs = numPairs;
	}

	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public void setPlayerOneHand(int index, MahjongTiles tile) {
		this.playerOneHand[index] = tile;
	}

	public void setPlayerTwoHand(int index, MahjongTiles tile) {
		this.playerTwoHand[index] = tile;
	}

	public void setPlayerThreeHand(int index, MahjongTiles tile) {
		this.playerThreeHand[index] = tile;
	}

	public void setPlayerFourHand(int index, MahjongTiles tile) {
		this.playerFourHand[index] = tile;
	}

	public void setTurn(boolean turn) {
		isTurn = turn;
	}
}
