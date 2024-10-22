package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.mahjong.tiles.MahjongTiles;

/**
 * A MahjongDiscardTileAction is an action a player takes when they want to discard
 * a current tile in their hand.
 * @author Megan Ou
 * @author Jazmine Cabral
 * @version October 2024
 */

public class MahjongDiscardTileAction extends GameAction {
    private MahjongTiles discardTile;

    /**
     * Constructor for the MahjongDiscardTileAction class.
     *
     * @param player the player making the move
     * @param discardedTile value to initialize the discarded tile
     */
    public MahjongDiscardTileAction(GamePlayer player, MahjongTiles discardedTile) {
        super(player);
        this.discardTile = discardedTile;
    }

    /** Getter Method for discarded tile **/
    public MahjongTiles getDiscardTile() {
        return discardTile;
    }
}
