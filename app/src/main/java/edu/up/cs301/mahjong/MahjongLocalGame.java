package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.DotsTile;
import edu.up.cs301.mahjong.tiles.HanziTile;
import edu.up.cs301.mahjong.tiles.MahjongTile;
import edu.up.cs301.mahjong.tiles.SymbolsTile;

import android.util.Log;
import android.widget.TextView;

/**
 * A class that represents the state of a game. In our mahjong game, the relevant information is
 * how many pairs and sets a player has.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author Landon Harrison
 * @author Megan Ou
 * @version October 2024
 */
public class MahjongLocalGame extends LocalGame {

	// the game's state
	private MahjongGameState gameState;
	//tells game you can only draw once and you can only discard if a tile is drawn
	private boolean hasDrawnTile;

	private MahjongTile drawnTile;


	/**
	 * Can this player move
	 * 
	 * @return
	 * 		- true if it is player's turn
	 * 		- false if it is not player's turn
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		return playerIdx == gameState.getPlayerID();
	}

	/**
	 * This ctor should be called when a new counter game is started
	 *
	 * @param state - the state of the game
	 */
	public MahjongLocalGame(GameState state) {
		// initialize the game state
		if (! (state instanceof MahjongGameState)) {
			state = new MahjongGameState();
		}
		this.gameState = (MahjongGameState)state;
		super.state = state;

		hasDrawnTile = false;
		drawnTile = null;
	}

	/**
	 * There are four different actions a user can take
	 *
	 * @param action - the action received
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		int playerID = gameState.getPlayerID();
		drawnTile = gameState.getCurrentDrawnTile();

		if (!tileDrawable()) {
			gameState.reshuffleDiscard();
		}

		Log.i("action", action.getClass().toString());
		if (canMove(playerID)) {
			if (action instanceof MahjongDrawTileAction && !hasDrawnTile) {
				//keep randomly selecting a tile until an unused tile is drawn
				while (!hasDrawnTile) {
					drawnTile = gameState.getDeck()[(int) (Math.random() * 135.0)];
					if (drawnTile.getLocationNum() == 0) {
						gameState.setCurrentDrawnTile(drawnTile);
						drawnTile.setLocationNum(gameState.getPlayerID() + 1);
						hasDrawnTile = true;
					}
				}
				return true;

			} else if (action instanceof MahjongDiscardTileAction && hasDrawnTile) {
				int buttonID = ((MahjongDiscardTileAction) action).getDiscardButtonID();
				int[] allButtonIDs = ((MahjongDiscardTileAction) action).getAllButtonIDs();

				//iterate through all possible discard buttons
				for (int i = 0; i < allButtonIDs.length; i++) {
					if (buttonID == allButtonIDs [i]) {
						//check if drawn tile is being discarded and discard it
						if (i == 0) {
							drawnTile.setLocationNum(5);
							gameState.setLastDiscarded(drawnTile);
						}
						//discard action on other tiles
						else {
							discardTileHelper(drawnTile, playerID, i-1);
						}
					}
				}

				//set last current drawn tile to null
				gameState.setCurrentDrawnTile(null);

				gameState.makeDiscardAction((MahjongDiscardTileAction) action);

				//reset the variable
				hasDrawnTile = false;

				return true;
			} else if (action instanceof MahjongChowAction) {
				gameState.makeChowAction((MahjongChowAction) action);
				return true;
			} else if (action instanceof MahjongSwitchViewAction) {
				gameState.makeSwitchViewAction((MahjongSwitchViewAction) action);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}//makeMove

	/**
	 * Helper method for checking to see if there are drawable tiles, if not, then reshuffle
	 * the discard pile
	 */
	public boolean tileDrawable() {
		int numDrawable = 0;
		//count num drawable tiles in deck
		for (int i = 0; i < gameState.getDeck().length; i++) {
			if(gameState.getDeck()[i].getLocationNum() == 0) {
				numDrawable++;
			}
		}
		//if no tiles are drawable
		if (numDrawable == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
	 * Helper method for discard tile, takes the given drawn tile and swaps it with the
	 * selected tile to discard
	 *
	 * @param drawnTile - the tile to be swapped
	 * @param playerID - the player that's discarding a tile
	 * @param index - the index of the drawnTile in the player's hand
	 */
	public void discardTileHelper (MahjongTile drawnTile, int playerID, int index) {
		//set location of drawn tile to player hand
		drawnTile.setLocationNum(playerID + 1);
		if (playerID == 0) {
			//set tile location in player hand to discard
			gameState.getPlayerOneHand()[index].setLocationNum(5);
			gameState.setLastDiscarded(gameState.getPlayerOneHand()[index]);
			//set pointer to null
			gameState.getPlayerOneHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerOneHand()[index] = drawnTile;
		}

		if (playerID == 1) {
			//set tile location in player hand to discard
			gameState.getPlayerTwoHand()[index].setLocationNum(5);
			gameState.setLastDiscarded(gameState.getPlayerTwoHand()[index]);
			//set pointer to null
			gameState.getPlayerTwoHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerTwoHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerTwoHand());
		}

		if (playerID == 2) {
			//set tile location in player hand to discard
			gameState.getPlayerThreeHand()[index].setLocationNum(5);
			gameState.setLastDiscarded(gameState.getPlayerThreeHand()[index]);
			//set pointer to null
			gameState.getPlayerThreeHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerThreeHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerThreeHand());
		}

		if (playerID == 3) {
			//set tile location in player hand to discard
			gameState.getPlayerFourHand()[index].setLocationNum(5);
			gameState.setLastDiscarded(gameState.getPlayerFourHand()[index]);
			//set pointer to null
			gameState.getPlayerFourHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerFourHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerFourHand());
		}
	}
	
	/**
	 * Send the updated state to a given player
	 *
	 * @param p - given player
	 */
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		// this is a perfect-information game, so we'll make a
		// complete copy of the state to send to the player
		p.sendInfo(new MahjongGameState(this.gameState));

		
	}//sendUpdatedSate
	
	/**
	 * Check if the game is over. It is over, return a string that tells
	 * who the winner(s), if any, are. If the game is not over, return null;
	 *
	 * Win requirement: a player has 4 sets and 1 pair
	 * 
	 * @return a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver() {

		int numSets1;
		int numPairs1;
		int numSets2;
		int numPairs2;
		int numSets3;
		int numPairs3;
		int numSets4;
		int numPairs4;

		MahjongTile[] handOne = gameState.getPlayerOneHand();
		MahjongTile[] handTwo = gameState.getPlayerTwoHand();
		MahjongTile[] handThree = gameState.getPlayerThreeHand();
		MahjongTile[] handFour = gameState.getPlayerFourHand();

		numSets1 = gameState.countNumSets(handOne);
		numPairs1 = gameState.countNumPairs(handOne);

		numSets2 = gameState.countNumSets(handTwo);
		numPairs2 = gameState.countNumPairs(handTwo);

		numSets3 = gameState.countNumSets(handThree);
		numPairs3 = gameState.countNumPairs(handThree);

		numSets4 = gameState.countNumSets(handFour);
		numPairs4 = gameState.countNumPairs(handFour);

		if (numSets1 == 4 && numPairs1 == 1) {
			return "Player 1 has won!!! Yippee!";
		}

		if (numSets2 == 4 && numPairs2 == 1) {
			return "Player 2 has won!!! Yippee!";
		}

		if (numSets3 == 4 && numPairs3 == 1) {
			return "Player 3 has won!!! Yippee!";
		}

		if (numSets4 == 4 && numPairs4 == 1) {
			return "Player 4 has won!!! Yippee!";
		}

		else {
			return null;
		}
	}

}// class CounterLocalGame
