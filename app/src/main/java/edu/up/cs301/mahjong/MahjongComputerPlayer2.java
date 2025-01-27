package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;


/**
* A smart computer version of a Mahjong player. This player can use the "chow" action/
 * button which is used if the last discarded tile completes a set of 3. This player
 * will prioritize runs over three of a kind. It will also account for many options/
 * strategies to take when discarding a tile in its hand:
 * 		- Priority will be given to potential sets already in its hand.
 * 		- When a tile is drawn, it will use permutation to determine what tile would be best to
 * 		  discard.
 * 		- If the drawn tile could complete two different sets of 3, it will randomly choose which
 * 		  set of three to complete and discard the other tile.
 * 		- A set of three can be broken up if a tile in the set is required to complete a pair
 * 		  AND the drawn tile can repair the broken set.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author  Landon Harrison
 * @author Megan Ou
 * @version November 2024
 */
public class MahjongComputerPlayer2 extends MahjongComputerPlayer1 implements Serializable {
	/*
	 * instance variables
	 */
	private MahjongGameState mgs = null;
	private boolean hasDrawnTile = false;
	private int[] discButtonIDArray = new int[15]; //Array of discard button ids
	MahjongTile[]  hand; //The computer player's current hand of tiles

	/**
	 * Constructor
	 *
	 * @param name
	 * 		the player's name
	 */
	public MahjongComputerPlayer2(String name) {
		super(name);
		hand = new MahjongTile[14];

		//Initialize discButtonIDArray & player's hand
		for (int i = 0; i < discButtonIDArray.length; i++) {
			discButtonIDArray[i] = i;
		}
	}

    /**
     * Callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {

		if (info instanceof MahjongGameState) {
			mgs = new MahjongGameState((MahjongGameState) info);
		}
		else {
			return;
		}

		//Make sure hand does not have null tiles
		mgs.setEmptyHand(hand);

		//Exit if it is not computer's turn
		if (mgs.getPlayerID() != playerNum) {
			return;
		}

		Log.e("Player Turn", "Smart AI player's turn." + playerNum);

		try {
			//Brief pause
			Thread.sleep(300);
		} catch (InterruptedException e) {
			/* don't care */
		}

		//Send a draw action to exit chow mode
		if (mgs.isChowMode() && !hasDrawnTile) {
			game.sendAction(new MahjongChowAction(this));
			hasDrawnTile = true; //just to be safe
		}

		//Draw a tile if a tile has not yet been drawn and is not chow mode
		else if (!mgs.isChowMode() && !hasDrawnTile) {
			game.sendAction(new MahjongDrawTileAction(this));
			Log.e("Computer Player", "Tile is drawn");
			hasDrawnTile = true;
		}

		//Discard a tile if a tile is drawn
		else {
			switch (this.mgs.getPlayerID()){
				case 0:
					hand = Arrays.copyOf(mgs.getPlayerOneHand(), mgs.getPlayerOneHand().length);
					break;
				case 1:
					hand = Arrays.copyOf(mgs.getPlayerTwoHand(), mgs.getPlayerTwoHand().length);
					break;
				case 2:
					hand = Arrays.copyOf(mgs.getPlayerThreeHand(), mgs.getPlayerThreeHand().length);
					break;
				case 3:
					hand = Arrays.copyOf(mgs.getPlayerFourHand(), mgs.getPlayerFourHand().length);
					break;
			}

			discardHelper();
			hasDrawnTile = false;
		}
	}//receiveInfo

	/**
	 * Helper method for discarding a tile.
	 * 	- The Smart AI will look for the tile that would be best to discard by using permutation to
	 * 	  determine which version of the current hand would give the best result.
	 */
	protected void discardHelper() {
		Log.e("S Computer Player", "S Computer player discards a tile.");

		//Algorithm to decide best tile to discard
		//This will look for increase in number of sets
		int result = 0;
		int bestDiscard = 0; //Index of the tile that would be best to discard
		String holdSuit = "";

		/**
		 * iterate through hand checking for optimal discard tile
		 */

		for(int i = 0; i < hand.length; i++){
			holdSuit = hand[i].getSuit();//holds original suit of "discarded tile"
			hand[i].setSuit("empty");//sets "discarded tile" to empty suit
			/**
			 * reasigning optimal discarded tile and holding result of preperm
			 */
			if (mgs.prePerm(hand) > result){
				result = mgs.prePerm(hand);
				bestDiscard = i;
			}
			hand[i].setSuit(holdSuit);//setting suit of tile back to held suit
		}

		//Sets the fourteenth hand element back to null
		hand[13] = new MahjongTile("empty", -1);

		//Computer player discards the tile at the bestDiscard index
		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,
				discButtonIDArray);
		discardTileAction.setDiscardButtonID(bestDiscard);
		game.sendAction(discardTileAction);
	}
}


