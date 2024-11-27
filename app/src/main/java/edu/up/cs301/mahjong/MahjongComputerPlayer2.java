package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;


/**
* A smart computer version of a Mahjong player. This player can use the "chow" action/
 * button which is used if the last discarded tile completes a set of 3. This player
 * will prioritize runs over three of a kind. It will also account for many options/
 * strategies to take when discarding a tile in its hand:
 * 		- Priority will be given to potential sets already in its hand.
 * 		- When a tile is drawn, it will see if it first completes a run, then a three
 * 		  of a kind, then a pair, and it will discard a tile not currently part of a pair/set.
 * 		- If the tile could complete two different sets of 3, it will randomly choose which
 * 		  set of three to complete and discard the other tile.
 * 		- A set of three can be broken up if a tile in the set is required to complete a pair
 * 		  AND the drawn tile can repair the broken set.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author  Landon Harrison
 * @author Megan Ou
 * @version November 2024
 */
public class MahjongComputerPlayer2 extends MahjongComputerPlayer1 implements Serializable {
	
	/*
	 * instance variables
	 */
	
	// the most recent game state, as given to us by the CounterLocalGame
	private MahjongGameState mgs = null;

	// If this player is running the GUI, the activity (null if the player is
	// not running a GUI).
	private Activity activityForGui = null;

	// If this player is running the GUI, the widget containing the counter's
	// value (otherwise, null);
	private TextView counterValueTextView = null;

	// If this player is running the GUI, the handler for the GUI thread (otherwise
	// null)
	private Handler guiHandler = null;

	private boolean hasDrawnTile = false;

	//array of discard button ids
	private int[] discButtonIDArray = new int[15];

	MahjongTile[]  hand;


	/**
	 * constructor
	 *
	 * @param name
	 * 		the player's name
	 */
	public MahjongComputerPlayer2(String name) {
		super(name);


		//initialize discButtonIDArray
		for (int i = 0; i < discButtonIDArray.length; i++) {
			discButtonIDArray[i] = i;
		}
	}

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {

		if (info instanceof MahjongGameState) {
			mgs = new MahjongGameState((MahjongGameState) info);
		}

		else {
			return;
		}



		//exit if it is not computer's turn
		if (mgs.getPlayerID() != playerNum) {
			return;
		}

		Log.e("Player Turn", "Smart AI player's turn." + playerNum);

		try {
			//brief pause
			Thread.sleep(300);
		} catch (InterruptedException e) {
			/* don't care */
		}

		//Draw a tile if a tile has not yet been drawn
		if (!hasDrawnTile) {
			//First draw tile
			game.sendAction(new MahjongDrawTileAction(this));
			Log.e("Computer Player", "Tile is drawn");

			hasDrawnTile = true;
		}
		//Send a draw action to exit chow mode
//		else if (mgs.isChowMode()) {
//			game.sendAction(new MahjongDrawTileAction(this));
//			hasDrawnTile = false; //just to be safe
//		}

		//Discard a tile if a tile is drawn
		else {
			switch (this.mgs.getPlayerID()){
				case 1:
					hand = this.mgs.getPlayerOneHand();
					break;
				case 2:
					hand = this.mgs.getPlayerTwoHand();
					break;
				case 3:
					hand = this.mgs.getPlayerThreeHand();
					break;
				case 4:
					hand = this.mgs.getPlayerFourHand();
					break;
			}
			discardHelper();
			hasDrawnTile = false;

		}



	}


	/**
	 * sets the counter value in the text view
	 *  */
	private void updateDisplay() {
//		// if the guiHandler is available, set the new counter value
//		// in the counter-display widget, doing it in the Activity's
//		// thread.
//		if (guiHandler != null) {
//			guiHandler.post(
//					new Runnable() {
//						public void run() {
//						if (counterValueTextView != null && currentGameState != null) {
//							//counterValueTextView.setText("" + currentGameState.getCounter());
//						}
//					}});
//		}
	}

	/**
	 * Tells whether we support a GUI
	 *
	 * @return
	 * 		true because we support a GUI
	 */
	public boolean supportsGui() {
		return true;
	}


		/**
	 * code to send a discard
	 */
	protected void discardHelper() {
		Log.e("Computer Player", "Computer player discards a tile.");


		int result = 0;
		int bestDiscard = 0;
		String holdSuit = "";

		hand[13] = mgs.getCurrentDrawnTile();
		for(int i = 0; i < 14; i++){
			holdSuit = hand[i].getSuit();
			hand[i].setSuit("empty");
			if (mgs.prePerm(hand) > result){
				result = mgs.prePerm(hand);
				bestDiscard = i;
			}
			hand[i].setSuit(holdSuit);
		}

		hand[13] = null;

		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,
				discButtonIDArray);

		discardTileAction.setDiscardButtonID(bestDiscard );
		game.sendAction(discardTileAction);

	}
}


