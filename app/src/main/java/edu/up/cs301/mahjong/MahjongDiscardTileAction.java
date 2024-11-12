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
    private int discardButtonID;
    private int[] allButtonIDs = new int[15];

    /**
     * Constructor for the MahjongDiscardTileAction class.
     *
     * @param player the player making the move
     *               //@param discardedTile value to initialize the discarded tile
     */
    public MahjongDiscardTileAction(GamePlayer player, int[] idArray) {
        super(player);
        discardButtonID = 0;

        for (int i = 0; i < idArray.length; i++) {
            allButtonIDs[i] = idArray[i];
        }
    }

    public void setDiscardButtonID(int discardButtonID) {
        this.discardButtonID = discardButtonID;
    }

    public int getDiscardButtonID() {
        return discardButtonID;
    }

    public int[] getAllButtonIDs() {
        return allButtonIDs;
    }
}

