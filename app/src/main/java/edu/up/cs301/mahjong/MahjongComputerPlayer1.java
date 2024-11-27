package edu.up.cs301.mahjong;

import android.util.Log;

import java.io.Serializable;

import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;

/**
 * A dumb computer version of a Mahjong player. This player cannot use the "Chow"
 * action/button. It simply draws a tile, determines if it fits in their hand,
 * or else it's discarded.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author Landon Harrison
 * @author Megan Ou
 * @version November 2024
 */
public class MahjongComputerPlayer1 extends GameComputerPlayer implements Tickable, Serializable {

	//array of discard button ids
	private int[] discButtonIDArray = new int[15];

	//have I drawn a tile this turn yet?
	private boolean hasDrawnTile = false;

    /**
     * Constructor for objects of class CounterComputerPlayer1
     * 
     * @param name - the player's name
     */
    public MahjongComputerPlayer1(String name) {
        // invoke superclass constructor
        super(name);
        
        // start the timer, ticking 20 times per second
        getTimer().setInterval(50);
        getTimer().start();

		//initialize discButtonIDArray
		for (int i = 0; i < discButtonIDArray.length; i++) {
			discButtonIDArray[i] = i;
		}
    }


	/**
	 * External Citation
	 * Date: 11/26/2024
	 * Problem: Our Computer Player was making more draw tiles actions than allowed.
	 * Resource: Dr. Nuxoll
	 * Solution: Add an 'if' statement to check if computer player has not drawn a tile yet, and let them draw a tile.
	 *
	 * External Citation
	 * Date: 11/26/2024
	 * Problem: Our Computer Player was making more draw tiles actions than allowed, and we did not know
	 * how to exit if it is not the computer players turn.
	 * Resource: Dr. Libby
	 * Solution: Used the 'playerNum' variable from GamePlayer
	 *
	 *
     * Callback method--game's state has changed
     * 
     * @param info - the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {

		MahjongGameState mgs;

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
			discardHelper();
			hasDrawnTile = false;
		}
	}

	/**
	 *
	 *
	 * External Citation
	 * Date: 11/26/2024
	 * Problem: Our now 'discardHelper()' method was originally 'timedTick()' and was calling our computpers makeDrawTileAction. Which
	 * was contributing to our computer player making multiple drawTiles actions.
	 * Resource: Dr. Nuxoll
	 * Solution: Moved that code to the above method and renamed this function to 'discardHelper' instead.
	 *
	 * code to send a discard
	 */
	protected void discardHelper() {
		Log.e("Computer Player", "Computer player discards a tile.");

		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,
				discButtonIDArray);

		if (Math.random() >= 0.15) { // 85% of the time discard from hand
			discardTileAction.setDiscardButtonID((int) (Math.random() * 12.0) + 1);
			game.sendAction(discardTileAction);
		}
		else {
			// discard drawn tile
			discardTileAction.setDiscardButtonID(0);
			game.sendAction(discardTileAction);

		}
	}
}
