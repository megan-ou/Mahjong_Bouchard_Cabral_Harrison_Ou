package edu.up.cs301.mahjong;

import java.io.Serializable;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * A MahjongChowAction is an action the current player takes when they want to call "chow" (or press
 * the chow button) on a tile that the last player just discarded. This tile will be added
 * to the current player's hand.
 *
 * TODO: add a method that turns the other two cards in set to revealed as well, but do we want to
 *  this in the Game State class?
 *
 * @author Jacqui Bouchard
 * @version October 2024
 */

public class MahjongChowAction extends GameAction implements Serializable {
    /**
     * constructor for MahjongChowAction
     *
     * @param player the player who called the action
     */
    public MahjongChowAction(GamePlayer player){
        super(player);

    }

}
