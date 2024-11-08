package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * A MahjongDiscardTileAction is an action a player takes when they want to discard
 * a current tile in their hand.
 * @author Megan Ou
 * @author Jazmine Cabral
 * @version October 2024
 */

public class MahjongDiscardTileAction extends GameAction {
    /**
     * Constructor for the MahjongDiscardTileAction class.
     *
     * @param player the player making the move
     *               //@param discardedTile value to initialize the discarded tile
     */
    public MahjongDiscardTileAction(GamePlayer player) {
        super(player);
    }

}

