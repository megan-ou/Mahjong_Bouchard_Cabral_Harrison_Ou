package edu.up.cs301.mahjong;

import java.net.BindException;
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
	private MahjongTile[] playerOneHand;
	private MahjongTile[] playerTwoHand;
	private MahjongTile[] playerThreeHand;
	private MahjongTile[] playerFourHand;
	private MahjongTile currentDrawnTile;
	private MahjongTile lastDiscarded;
	private MahjongTile[] deck; // 136 tiles in a deck
	private String lastDrawnTile;

	/**
	 * default ctor
	 */
	public MahjongGameState(){
		this.playerID = 0;
		this.isTurn = false;
		this.numSets = 0;
		this.numPairs = 0;
		this.playerOneHand = new MahjongTile[14]; //need to copy each object to a new array when constructing, example on moodle
		this.playerTwoHand = new MahjongTile[14];
		this.playerThreeHand = new MahjongTile[14];
		this.playerFourHand = new MahjongTile[14];
		this.currentDrawnTile = null;
		this.deck = new MahjongTile[136];
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
		this.playerOneHand = new MahjongTile[mgs.playerOneHand.length];
		copyArray(mgs.playerOneHand,this.playerOneHand);
		this.playerTwoHand = new MahjongTile[mgs.playerTwoHand.length];
		copyArray(mgs.playerTwoHand,this.playerTwoHand);
		this.playerThreeHand = new MahjongTile[mgs.playerThreeHand.length];
		copyArray(mgs.playerThreeHand,this.playerThreeHand);
		this.playerFourHand = new MahjongTile[mgs.playerFourHand.length];
		copyArray(mgs.playerFourHand,this.playerFourHand);
		this.currentDrawnTile = mgs.currentDrawnTile;
		this.lastDiscarded = mgs.lastDiscarded;
		this.deck = new MahjongTile[mgs.getDeck().length];
		copyArray(this.deck, mgs.deck);
		this.lastDrawnTile = mgs.lastDrawnTile;

	}

	/**
	 * Helper method for deep copy ctor to copy arrays
	 */
	public void copyArray (MahjongTile[] newArray, MahjongTile[] origArray) {
		for (int i = 0; i < origArray.length; i++) {
			newArray[i] = new MahjongTile(origArray[i].getSuit(), origArray[i].getValue());
		}
	}

	/**
	 * Helper method for deep copy ctor to copy array lists
	 */
	public void copyArrayList (ArrayList<MahjongTile> newArrayList,
							   ArrayList<MahjongTile> origArrayList) {
        newArrayList.addAll(origArrayList);
	}

	/**
	 * Helper method that clears an array.
	 */
	public void clearArray(MahjongTile[] hand) {
		for (int i = 0 ; i < hand.length; i++) {
			if (hand[i] != null) {
				hand[i] = null;
			}
		}
	}

	/**
	 * method that initializes and adds all elements to the classes deck
	 * takes an array, adds all tiles with for loops
	 */
	public MahjongTile[] mahjongDeck(MahjongTile[] deck){
		clearArray(deck);

		//index of entire deck array, should cap at 135
		int deckIndex = 0;

		//creates 4 copies of each tile
		for (int i = 0; i < 4; i++) {
			//create tiles in each of three suits: hanzi, dots, sticks
			for (int j = 0; j < 9; j++) {
				deck[deckIndex] = new HanziTile(j+1);
				deckIndex++;
				deck[deckIndex] = new DotsTile(j+1);
				deckIndex++;
				deck[deckIndex] = new StickTile(j+1);
				deckIndex++;
			}

			//create all 7 unique symbol tiles
			//all symbol tiles have a value of 0
			deck[deckIndex] = new SymbolsTile("Earth");
			deckIndex++;
			deck[deckIndex] = new SymbolsTile("Fire");
			deckIndex++;
			deck[deckIndex] = new SymbolsTile("Water");
			deckIndex++;
			deck[deckIndex] = new SymbolsTile("Wind");
			deckIndex++;
			deck[deckIndex] = new SymbolsTile("Flower");
			deckIndex++;
			deck[deckIndex] = new SymbolsTile("Star");
			deckIndex++;
			deck[deckIndex] = new SymbolsTile("Cat");
			deckIndex++;
		}
		return deck;
	}

	/**
	 * will start the game
	 * deal the tiles in the deck
	 */
	public void startGame() {
		dealTiles();
	}

	/**
	 * method that will deal the 13 initial tiles to all 4 players
	 * If a tile is dealt, set locationNum variable to player ID (1-4)
	 * this will randomly draw a tile, if it is not in the draw pile (Location 0), it will  draw again
	 */
	public void dealTiles() {
		//set all hands to null
		clearArray(playerOneHand);
		clearArray(playerTwoHand);
		clearArray(playerThreeHand);
		clearArray(playerFourHand);

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
	 * Method that sorts the deck by tile location after every action (deal, draw, discard, or chow)
	 * Tile location numbers should look like this after sorting: 000000,11111,22222,3333,44444,5555
	 *
	 * First switch case counts number of tiles are in each location to figure out starting indexes
	 * in the copy array for each location.
	 *
	 * Second switch case copies over tiles into the copy array based on the starting index calculated
	 * using the previously collected values
	 *
	 * Then, sets the deck to the copy array and clears out the copy array
	 */
	public void sortDeck() {
		MahjongTile[] sortedDeck = new MahjongTile[deck.length];
		clearArray(sortedDeck);

		//counter variables to see how many tiles are in each location

		int num0 = 0;
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		int num4 = 0;
		int num5 = 0;

		//first loop goes into the deck and counts how many tiles are in each location
		for (int i = 0; i < deck.length; i++) {
			switch (deck[i].getLocationNum()) {
				case 0:
					num0++;
					break;
				case 1:
					num1++;
					break;
				case 2:
					num2++;
					break;
				case 3:
					num3++;
					break;
				case 4:
					num4++;
					break;
				case 5:
					num5++;
					break;
			}
		}

		//find starting index for each location based on how many tiles are in each location

		int index0 = 0;
		int index1 = num0;
		int index2 = index1 + num1;
		int index3 = index2 + num2;
		int index4 = index3 + num3;
		int index5 = index4 + num4;


		//second loop goes in and reassigns tiles to sortedDeck

		for (int i = 0; i < deck.length; i++) {
			switch (deck[i].getLocationNum()) {
				case 0:
					sortedDeck[index0] = deck[i];
					index0++;
					break;
				case 1:
					sortedDeck[index1] = deck[i];
					index1++;
					break;
				case 2:
					sortedDeck[index2] = deck[i];
					index2++;
					break;
				case 3:
					sortedDeck[index3] = deck[i];
					index3++;
					break;
				case 4:
					sortedDeck[index4] = deck[i];
					index4++;
					break;
				case 5:
					sortedDeck[index5] = deck[i];
					index5++;
					break;
			}
		}

		//set sorted deck to deck

		for (int i = 0; i < deck.length; i++) {
			this.deck[i] = sortedDeck[i];
		}

		clearArray(sortedDeck);
	}

	/**
	 * method that sorts a given hand by suit and then ascending numerical value
	 * prioritizes the values
	 */
	public void sortHand(MahjongTile[] mahjongTiles) {
		MahjongTile[] sortedHand = new MahjongTile[mahjongTiles.length - 1];
		clearArray(sortedHand);

		//First, sort the hand by suit

		//counter variables to see how many tiles are in each suit

		int numHanzi = 0;
		int numDots = 0;
		int numSticks = 0;
		int numFire = 0;
		int numWater = 0;
		int numEarth = 0;
		int numWind = 0;
		int numFlower = 0;
		int numStar = 0;
		int numCat = 0;

		//first loop goes into the deck and counts how many tiles are in each suit
		for (int i = 0; i < mahjongTiles.length - 1; i++) {
			switch (mahjongTiles[i].getSuit()) {
				case "Hanzi":
					numHanzi++;
					break;
				case "Dots":
					numDots++;
					break;
				case "Sticks":
					numSticks++;
					break;
				case "Fire":
					numFire++;
					break;
				case "Water":
					numWater++;
					break;
				case "Earth":
					numEarth++;
					break;
				case "Wind":
					numWind++;
					break;
				case "Flower":
					numFlower++;
					break;
				case "Star":
					numStar++;
					break;
				case "Cat":
					numCat++;
					break;
			}
		}

		//find starting index for each suit based on how many tiles are in each suit, first must
		//check if any cards of each suit are in the hand

		int lastEndingIndex = 0;

		int indexHanzi = 0;
		int indexDots = 0;
		int indexSticks = 0;
		int indexFire = 0;
		int indexWater = 0;
		int indexEarth = 0;
		int indexWind = 0;
		int indexFlower = 0;
		int indexStar = 0;
		int indexCat = 0;

		if(numHanzi != 0) {
			lastEndingIndex += numHanzi;
		}
		if(numDots != 0 ) {
			indexDots = lastEndingIndex;
			lastEndingIndex += numDots;
		}
		if (numSticks != 0) {
			indexSticks = lastEndingIndex;
			lastEndingIndex += numSticks;
		}
		if (numFire != 0) {
			indexFire = lastEndingIndex;
			lastEndingIndex += numFire;
		}
		if (numWater != 0)  {
			indexWater = lastEndingIndex;
			lastEndingIndex += numWater;
		}
		if (numEarth != 0) {
			indexEarth = lastEndingIndex;
			lastEndingIndex += numEarth;
		}
		if (numWind != 0) {
			indexWind = lastEndingIndex;
			lastEndingIndex += numWind;
		}
		if (numFlower != 0) {
			indexFlower = lastEndingIndex;
			lastEndingIndex += numFlower;
		}
		if (numStar != 0) {
			indexStar = lastEndingIndex;
			lastEndingIndex += numStar;
		}
		if (numCat != 0) {
			indexCat = lastEndingIndex;
        }

		//second loop goes in and reassigns tiles to sortedHand

		for (int i = 0; i < mahjongTiles.length - 1; i++) {
			switch (mahjongTiles[i].getSuit()) {
				case "Hanzi":
					sortedHand[indexHanzi] = mahjongTiles[i];
					indexHanzi++;
					break;
				case "Dots":
					sortedHand[indexDots] = mahjongTiles[i];
					indexDots++;
					break;
				case "Sticks":
					sortedHand[indexSticks] = mahjongTiles[i];
					indexSticks++;
					break;
				case "Fire":
					sortedHand[indexFire] = mahjongTiles[i];
					indexFire++;
					break;
				case "Water":
					sortedHand[indexWater] = mahjongTiles[i];
					indexWater++;
					break;
				case "Earth":
					sortedHand[indexEarth] = mahjongTiles[i];
					indexEarth++;
					break;
				case "Wind":
					sortedHand[indexWind] = mahjongTiles[i];
					indexWind++;
					break;
				case "Flower":
					sortedHand[indexFlower] = mahjongTiles[i];
					indexFlower++;
					break;
				case "Star":
					sortedHand[indexStar] = mahjongTiles[i];
					indexStar++;
					break;
				case "Cat":
					sortedHand[indexCat] = mahjongTiles[i];
					indexCat++;
					break;
			}
		}

		//set sortedHand by suit to mahjongTiles[]

		for (int i = 0; i < mahjongTiles.length - 1; i++) {
			mahjongTiles[i] = sortedHand[i];
		}

		clearArray(sortedHand);

		/*Sort the hanzi, dots, and sticks suits by ascending numerical values
			Reset all starting indexes to utilize range
		 */

		lastEndingIndex = 0;

		if(numHanzi != 0) {
			lastEndingIndex += numHanzi;
			ascendingSort(mahjongTiles, 1, lastEndingIndex, numHanzi);
		}
		if(numDots != 0 ) {
			indexDots = lastEndingIndex;
			lastEndingIndex += numDots;
			ascendingSort(mahjongTiles,indexDots + 1,lastEndingIndex, numDots);
		}
		if (numSticks != 0) {
			indexSticks = lastEndingIndex;
			lastEndingIndex += numSticks;
			ascendingSort(mahjongTiles, indexSticks + 1, lastEndingIndex, numSticks);
		}

	}

	/**
	 * Helper method for sorting by ascending numerical value
	 * TODO: edit code to sort tiles in ascending order AND skip duplicates (EX: H1,H2,H3,H4,H2,H2,D2)
	 */
	public void ascendingSort(MahjongTile[] hand, int begIndex, int lastEndingIndex, int numTiles) {
		MahjongTile tempTile;

		for (int k = 0; k < numTiles; k++) {
			for (int i = begIndex; i < lastEndingIndex; i++) {
				if (hand[i].getValue() < hand[i - 1].getValue()) {
					tempTile = hand[i - 1];
					hand[i - 1] = hand[i];
					hand[i] = tempTile;
				}
			}
		}

	}

	/**
	 * Method that counts how many sets are in a player's hand
	 * A set is three tiles of the same suit in numerical order (Ex: Hanzi 1, Hanzi 2, Hanzi 3)
	 * OR three identical tiles (Ex: Hanzi 1, Hanzi 1, Hanzi 1 OR Flower, Flower Flower)
	 *
	 * TODO: discuss numSets variable and if we just want a return value and move this
	 * 	IV to human/computer player classes
	 *
	 * @param playerHand array of tiles that represents a player's hand
	 * @return number of sets in a given hand
	 */
	public int numSets (MahjongTile[] playerHand) {
		//reset numSets
		this.numSets = 0;

		String firstTileSuit;
		String secondTileSuit;
		String thirdTileSuit;
		int firstVal;
		int secondVal;
		int thirdVal;

		for (int i = 0; i < playerHand.length; i++) {
			firstTileSuit = playerHand[i].getSuit();
			secondTileSuit = playerHand[i+1].getSuit();
			thirdTileSuit = playerHand[i+2].getSuit();

			//check if 3 tiles in a row are of the same suit
			if (firstTileSuit.equals(secondTileSuit) && firstTileSuit.equals(thirdTileSuit)) {
				firstVal = playerHand[i].getValue();
				secondVal = playerHand[i+1].getValue();
				thirdVal = playerHand[i+2].getValue();

				//check to see if values are in numerical order
				//deck SHOULD be sorted into tiles of ascending order, so this should check if
				//the three values are sequential
				if (secondVal == (firstVal + 1) && thirdVal == (firstVal+2) ) {
					this.numSets++;

					playerHand[i].setPartOfSet(true);
					playerHand[i+1].setPartOfSet(true);
					playerHand[i+2].setPartOfSet(true);

					//set i to the index of the third tile so when loop increments, it skips the
					//counted set
					i += 2;
				}

				//check if there is a three of a kind
				else if (firstVal == secondVal && firstVal == thirdVal) {
					this.numSets++;

					playerHand[i].setPartOfSet(true);
					playerHand[i+1].setPartOfSet(true);
					playerHand[i+2].setPartOfSet(true);

					i += 2;
				}
			}
		}
		return numSets;
	}

	/**
	 * Method that counts how many pairs are in a player's hand that are NOT already part of
	 * a set.
	 * A pair is two identical tiles
	 *
	 * TODO: discuss numPairs variable and if we just want a return value and move this
	 * 	IV to human/computer player classes
	 *
	 * @param playerHand array of tiles that represents a player's hand
	 * @return number of pairs in a given hand
	 */
	public int numPairs (MahjongTile[] playerHand) {
		//reset numSets
		this.numPairs = 0;

		String firstTileSuit;
		String secondTileSuit;
		int firstVal;
		int secondVal;

		for (int i = 0; i < playerHand.length; i++) {
			firstTileSuit = playerHand[i].getSuit();
			secondTileSuit = playerHand[i+1].getSuit();

			//check if 2 tiles in a row are of the same suit and if the two tiles are NOT part of a
			// set
			if (firstTileSuit.equals(secondTileSuit) && !playerHand[i].isPartOfSet()
					&& !playerHand[i+1].isPartOfSet()) {
				firstVal = playerHand[i].getValue();
				secondVal = playerHand[i+1].getValue();

				//check to see if values are equal
				if (secondVal == firstVal) {
					this.numPairs++;

					//set i to the index of the second tile so when loop increments, it skips the
					//counted pair
					i += 1;
				}
			}
		}
		return numPairs;
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
	public String deckToString(MahjongTile[] deck, int index) {
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
	public String handToString(MahjongTile[] hand, int index) {
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

	public MahjongTile[] getDeck() {
		return this.deck;
	}

	public MahjongTile[] getPlayerOneHand() {
		return playerOneHand;
	}

	public MahjongTile[] getPlayerTwoHand() {
		return playerTwoHand;
	}

	public MahjongTile[] getPlayerThreeHand() {
		return playerThreeHand;
	}

	public MahjongTile[] getPlayerFourHand() {
		return playerFourHand;
	}

	public MahjongTile getCurrentDrawnTile() {
		return currentDrawnTile;
	}

	public int getNumSets() {
		return numSets;
	}

	public MahjongTile getLastDiscarded() {
		return lastDiscarded;
	}
	public boolean getIsTurn(){
		return isTurn;
	}

	/**
	 * Setter Methods
	 */
	public void setCurrentDrawnTile(MahjongTile currentDrawnTile) {
		this.currentDrawnTile = currentDrawnTile;
	}

	public void setLastDiscarded(MahjongTile lastDiscarded) {
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

	public void setPlayerOneHand(int index, MahjongTile tile) {
		this.playerOneHand[index] = tile;
	}

	public void setPlayerTwoHand(int index, MahjongTile tile) {
		this.playerTwoHand[index] = tile;
	}

	public void setPlayerThreeHand(int index, MahjongTile tile) {
		this.playerThreeHand[index] = tile;
	}

	public void setPlayerFourHand(int index, MahjongTile tile) {
		this.playerFourHand[index] = tile;
	}

	public void setTurn(boolean turn) {
		isTurn = turn;
	}
}
