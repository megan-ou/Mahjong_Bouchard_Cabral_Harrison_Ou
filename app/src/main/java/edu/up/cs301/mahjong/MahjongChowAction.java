package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.mahjong.tiles.MahjongTile;

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

public class MahjongChowAction extends GameAction {
    private MahjongTile chowTile;

    /**
     * constructor for MahjongChowAction
     *
     * @param player the player who called the action
     */
    public MahjongChowAction(GamePlayer player){
            //, MahjongTile chowTile) {
        super(player);
        //this.chowTile = chowTile;
        //chowTile.setRevealed(true);
    }

    /**
     * Getter Method for the tile that the player calls "chow" on
     */
    public MahjongTile getChowTile() { return chowTile; }
}
