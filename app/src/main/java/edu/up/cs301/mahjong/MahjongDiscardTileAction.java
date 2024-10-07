package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * A MahjongDiscardTileAction is an action a player takes when they want to discard
 * a current tile in their hand.
 *
 * @author Megan Ou
 * @version October 2024
 */

public class MahjongDiscardTileAction extends GameAction {

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MahjongDiscardTileAction(GamePlayer player) {
        super(player);
    }
}
