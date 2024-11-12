package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.util.Log;

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
	
	/**
	 * can this player move
	 * 
	 * @return
	 * 		true if it is player's turn
	 * 		false if it is not player's turn
	 */
	@Override
	protected boolean canMove(int playerIdx) {
		return playerIdx == gameState.getPlayerID();
	}

	/**
	 * This ctor should be called when a new counter game is started
	 */
	public MahjongLocalGame(GameState state) {
		// initialize the game state, with the counter value starting at 0
		if (! (state instanceof MahjongGameState)) {
			state = new MahjongGameState();
		}
		this.gameState = (MahjongGameState)state;
		super.state = state;

		//test.findViewById(R.id.iVCSlotT1); from Lab 6
	}

	/**
	 * There are four different actions a user can take
	 * TODO: Add conditions (ex: only discard IF a card is drawn)
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		Log.i("action", action.getClass().toString());

		if (action instanceof MahjongDrawTileAction) {
			boolean cardDrawn = false;
			MahjongTile tile;

			while (!cardDrawn) {
				tile = gameState.getDeck()[(int) (Math.random() * 135.0)];
				if (tile.getLocationNum() == 0) {
					gameState.setCurrentDrawnTile(tile);
					tile.setLocationNum(gameState.getPlayerID() + 1);
					cardDrawn = true;
				}
			}
//			gameState.makeDrawTileAction((MahjongDrawTileAction) action);
			return true;
		}
		else if (action instanceof MahjongDiscardTileAction) {
			int buttonID = ((MahjongDiscardTileAction) action).getDiscardButtonID();
			int[] allButtonIDs = ((MahjongDiscardTileAction) action).getAllButtonIDs();
			MahjongTile drawnTile = gameState.getCurrentDrawnTile();
			int playerID = gameState.getPlayerID();

			//iterate through all possible discard buttons
			for (int i = 0; i < allButtonIDs.length; i++) {
				//check if drawn tile is being discarded and discard it
				if (buttonID == allButtonIDs[0]) {
					drawnTile.setLocationNum(5);
					gameState.setLastDiscarded(drawnTile);
				}
				//discard action on other tiles
				else {
					discardTileHelper(drawnTile, playerID, i);
				}
			}

			//set last current drawn tile to null
			gameState.setCurrentDrawnTile(null);

			gameState.makeDiscardAction((MahjongDiscardTileAction) action);

			return true;
		}
		else if (action instanceof MahjongChowAction) {
			gameState.makeChowAction((MahjongChowAction) action);
			return true;
		}
		else if (action instanceof MahjongSwitchViewAction) {
			gameState.makeSwitchViewAction((MahjongSwitchViewAction) action);
			return true;
		}
		else {
			return false;
		}
	}//makeMove

	/**
	 * Helper method for discard tile, takes the given drawn tile and swaps it with the
	 * selected tile to discard
	 * @param drawnTile
	 * @param playerID
	 */
	public void discardTileHelper (MahjongTile drawnTile, int playerID, int index) {
		//set location of drawn tile to player hand
		drawnTile.setLocationNum(playerID + 1);
		if (playerID == 0) {
			//set tile location in player hand to discard
			gameState.getPlayerOneHand()[index].setLocationNum(5);
			//set pointer to null
			gameState.getPlayerOneHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerOneHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerOneHand());
		}

		if (playerID == 1) {
			//set tile location in player hand to discard
			gameState.getPlayerTwoHand()[index].setLocationNum(5);
			//set pointer to null
			gameState.getPlayerTwoHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerTwoHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerTwoHand());
		}

		if (playerID == 2) {
			//set tile location in player hand to discard
			gameState.getPlayerThreeHand()[index].setLocationNum(5);
			//set pointer to null
			gameState.getPlayerThreeHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerThreeHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerThreeHand());
		}

		if (playerID == 3) {
			//set tile location in player hand to discard
			gameState.getPlayerFourHand()[index].setLocationNum(5);
			//set pointer to null
			gameState.getPlayerFourHand()[index] = null;
			//set drawn tile to player hand
			gameState.getPlayerFourHand()[index] = drawnTile;
			gameState.sortHand(gameState.getPlayerFourHand());
		}
	}
	
	/**
	 * send the updated state to a given player
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
	 * @return
	 * 		a message that tells who has won the game, or null if the
	 * 		game is not over
	 */
	@Override
	protected String checkIfGameOver() {

		int numSets = gameState.getNumSets();
		int numPairs = gameState.getNumPairs();
		int playerID = gameState.getPlayerID();
		MahjongTile[] handOne = gameState.getPlayerOneHand();
		MahjongTile[] handTwo = gameState.getPlayerTwoHand();
		MahjongTile[] handThree = gameState.getPlayerThreeHand();
		MahjongTile[] handFour = gameState.getPlayerFourHand();

		switch (playerID) {
			case 0:
				numSets = gameState.countNumSets(handOne);
				numPairs = gameState.countNumPairs(handOne);
				break;
			case 1:
				numSets = gameState.countNumSets(handTwo);
				numPairs = gameState.countNumPairs(handTwo);
				break;
			case 2:
				numSets = gameState.countNumSets(handThree);
				numPairs = gameState.countNumPairs(handThree);
				break;
			case 3:
				numSets = gameState.countNumSets(handFour);
				numPairs = gameState.countNumPairs(handFour);
				break;
		}

		if (numSets == 4 && numPairs == 1) {
			return playerNames[playerID] + " has won!!! Yippee!";
		}

		else {
			return null;
		}
	}

}// class CounterLocalGame
