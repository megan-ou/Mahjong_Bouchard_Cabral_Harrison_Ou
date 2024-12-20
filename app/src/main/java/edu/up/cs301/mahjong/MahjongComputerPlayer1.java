package edu.up.cs301.mahjong;

import android.util.Log;

import java.io.Serializable;

import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;

/**
 * A dumb computer version of a Mahjong player. This player cannot use the "Chow"
 * action/button. It simply draws a tile and randomly discards another one.
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
        //invoke superclass constructor
        super(name);

		//Initialize discButtonIDArray
		for (int i = 0; i < discButtonIDArray.length; i++) {
			discButtonIDArray[i] = i;
		}
    }


	/**
	 * Callback method--game's state has changed
	 *
	 * External Citation
	 * Date: 11/26/2024
	 * Problem: Our Computer Player was making more draw tiles actions than allowed.
	 * Resource: Dr. Nuxoll
	 * Solution: Add an 'if' statement to check if computer player has not drawn a tile yet, and let
	 * them draw a tile.
	 *
	 * External Citation
	 * Date: 11/26/2024
	 * Problem: Our Computer Player was making more draw tiles actions than allowed, and we did not know
	 * how to exit if it is not the computer players turn.
	 * Resource: Dr. Libby
	 * Solution: Used the 'playerNum' variable from GamePlayer
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

		Log.e("Player Turn", "Dumb AI player's turn." + playerNum);

		try {
			//brief pause
			Thread.sleep(300);
		} catch (InterruptedException e) {
			/* don't care */
		}

		//Draw a tile if a tile has not yet been drawn
		if (!hasDrawnTile) {
			game.sendAction(new MahjongDrawTileAction(this));
			Log.e("Computer Player", "Tile is drawn");

			hasDrawnTile = true;
		}
		//Send a draw action to exit chow mode
		else if (mgs.isChowMode()) {
			game.sendAction(new MahjongDrawTileAction(this));
			hasDrawnTile = false; //just to be safe
		}
		//Discard a tile if a tile is drawn
		else {
			discardHelper();
			hasDrawnTile = false;
		}
	}

	/**
	 * External Citation
	 * Date: 11/26/2024
	 * Problem: Our now 'discardHelper()' method was originally 'timerTicked()' and was calling our
	 * computer's makeDrawTileAction. Which was contributing to our computer player making multiple
	 * drawTiles actions.
	 * Resource: Dr. Nuxoll
	 * Solution: Moved that code to the above method and renamed this function to 'discardHelper' instead.
	 *
	 * Helper method which sends a random discard action to the game.
	 */
	protected void discardHelper() {
		Log.e("Computer Player", "Computer player discards a tile.");

		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,
				discButtonIDArray);

		if (Math.random() >= 0.15) { //85% of the time discard from hand
			discardTileAction.setDiscardButtonID((int) (Math.random() * 13.0) + 1);
			game.sendAction(discardTileAction);
		}
		else {
			//Discard drawn tile
			discardTileAction.setDiscardButtonID(0);
			game.sendAction(discardTileAction);

		}
	}
}
