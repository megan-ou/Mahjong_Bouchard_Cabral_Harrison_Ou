package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.MahjongTiles;

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
	
	// to satisfy the serializable interface
	private static final long serialVersionUID = 28062013L;

	private MahjongTiles drawnTile;

	/**
	 * Constructor for the MahjongDrawTileAction class.
	 * 
	 * @param player the player making the move
	 * @param tile value to initialize this.isPlus
	 */
	public MahjongDrawTileAction(GamePlayer player, MahjongTiles tile) {
		super(player);
		this.drawnTile = tile;
	}

	/**
	 * constructor for GameAction
	 *
	 * @param player the player who created the action
	 */
	public MahjongDrawTileAction(GamePlayer player) {
		super(player);
	}

	public MahjongTiles getDrawnTile() {
		return drawnTile;
	}


}
