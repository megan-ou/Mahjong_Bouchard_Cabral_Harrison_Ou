package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.MahjongTile;

/**
 * A MahjongDrawTileAction is an action that for Each players turn must draw a tile.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Megan Ou
 * @author Jazmine Cabral
 * @version October 2024
 */
public class MahjongDrawTileAction extends GameAction {

	/**
	 * Constructor for the MahjongDrawTileAction class.
	 *
	 * @param player the player making the move
	 */
	public MahjongDrawTileAction(GamePlayer player) {
		super(player);
	}

}
