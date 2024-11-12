package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import android.util.Log;
import android.widget.ImageView;

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
	 */
	@Override
	protected boolean makeMove(GameAction action) {
		Log.i("action", action.getClass().toString());

		if (action instanceof MahjongDrawTileAction) {
			gameState.makeDrawTileAction((MahjongDrawTileAction) action);
			return true;
		}
		else if (action instanceof MahjongDiscardTileAction) {
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
		
		// get the value of the counter
		//int counterVal = this.gameState.getCounter();
		
		//if (counterVal >= TARGET_MAGNITUDE) {
			// counter has reached target magnitude, so return message that
			// player 0 has won.
			//return playerNames[0]+" has won.";
		//}
		//else if (counterVal <= -TARGET_MAGNITUDE) {
			// counter has reached negative of target magnitude; if there
			// is a second player, return message that this player has won,
			// otherwise that the first player has lost
			//if (playerNames.length >= 2) {
			//	return playerNames[1]+" has won.";
		//	}
			//else {
			//	return playerNames[0]+" has lost.";
		//	}
		//}//else {
			// game is still between the two limit: return null, as the game
			// is not yet over
			//return null;
		//}
		return null;
	}

}// class CounterLocalGame
