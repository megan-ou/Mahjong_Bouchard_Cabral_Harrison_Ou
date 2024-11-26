package edu.up.cs301.mahjong;

import java.io.Serializable;

import edu.up.cs301.GameFramework.players.GameComputerPlayer;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.GameFramework.utilities.Tickable;
import edu.up.cs301.mahjong.tiles.MahjongTile;

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

		//exit if it is human player
		if (mgs.getPlayerID() == 0) {
			return;
		}

		try {
			//First draw tile
			game.sendAction(new MahjongDrawTileAction(this));

			//then discard
			timerTicked();

			//brief pause
			Thread.sleep(300);
		} catch (InterruptedException e) {
		}

		sendInfo(mgs);

	}
	
	/**
	 * Callback method: the timer ticked
	 */
	protected void timerTicked() {
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
