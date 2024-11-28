package edu.up.cs301.mahjong;

import java.io.Serializable;

import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.MahjongTile;

/**
 * A MahjongDrawTileAction occurs on each players' turn where they must draw a tile.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Megan Ou
 * @author Jazmine Cabral
 * @version October 2024
 */
public class MahjongDrawTileAction extends GameAction implements Serializable {

	/**
	 * Constructor for the MahjongDrawTileAction class.
	 *
	 * @param player the player making the move
	 */
	public MahjongDrawTileAction(GamePlayer player) {
		super(player);
	}

}
