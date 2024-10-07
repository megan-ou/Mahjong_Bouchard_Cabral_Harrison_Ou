package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;

/**
 * A CounterMoveAction is an action that is a "move" the game: either increasing
 * or decreasing the counter value.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Megan Ou
 * @version October 2024
 */
public class MahjongDrawTileAction extends GameAction {
	
	// to satisfy the serializable interface
	private static final long serialVersionUID = 28062013L;

	//whether this move is a plus (true) or minus (false)
	private boolean isPlus;
	
	/**
	 * Constructor for the CounterMoveAction class.
	 * 
	 * @param player
	 *            the player making the move
	 * @param isPlus
	 *            value to initialize this.isPlus
	 */
	public MahjongDrawTileAction(GamePlayer player, boolean isPlus) {
		super(player);
		this.isPlus = isPlus;
	}

	/**
	 * constructor for GameAction
	 *
	 * @param player the player who created the action
	 */
	public MahjongDrawTileAction(GamePlayer player) {
		super(player);
	}

	/**
	 * getter method, to tell whether the move is a "plus"
	 * 
	 * @return
	 * 		a boolean that tells whether this move is a "plus"
	 */
	public boolean isPlus() {
		return isPlus;
		
	}
}//class CounterMoveAction
