package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.GameFramework.players.GamePlayer;

/**
 * Action that allows the user to switch views between default view and table view
 */

public class MahjongSwitchViewAction extends GameAction {
    public MahjongSwitchViewAction(GamePlayer player) {
        super(player);
        //TODO: empty for now because there is no GUI file for this new view
    }
}
