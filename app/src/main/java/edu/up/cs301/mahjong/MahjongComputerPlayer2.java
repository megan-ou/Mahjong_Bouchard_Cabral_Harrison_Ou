package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;


/**
* A smart computer version of a Mahjong player. This player can use the "chow" action/
 * button which is used if the last discarded tile completes a set of 3. This player
 * will prioritize runs over three of a kind. It will also account for many options/
 * strategies to take when discarding a tile in its hand:
 * 		- Priority will be given to potential sets already in its hand.
 * 		- When a tile is drawn, it will see if it first completes a run, then a three
 * 		  of a kind, then a pair, and it will discard a tile not currently part of a pair/set.
 * 		- If the tile could complete two different sets of 3, it will randomly choose which
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
	
	// the most recent game state, as given to us by the CounterLocalGame
	private MahjongGameState mgs = null;

	private boolean hasDrawnTile = false;

	//array of discard button ids
	private int[] discButtonIDArray = new int[15];

	MahjongTile[]  hand = new MahjongTile[14];


	/**
	 * constructor
	 *
	 * @param name
	 * 		the player's name
	 */
	public MahjongComputerPlayer2(String name) {
		super(name);

		//initialize discButtonIDArray
		for (int i = 0; i < discButtonIDArray.length; i++) {
			discButtonIDArray[i] = i;
		}

		hand = new MahjongTile[14];


	}

    /**
     * callback method--game's state has changed
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

		mgs.setEmptyHand(hand);

		//exit if it is not computer's turn
		if (mgs.getPlayerID() != playerNum) {
			return;
		}

		Log.e("Player Turn", "Smart AI player's turn." + playerNum);

		try {
			//brief pause
			Thread.sleep(300);
		} catch (InterruptedException e) {
			/* don't care */
		}

		//Draw a tile if a tile has not yet been drawn
		if (!hasDrawnTile) {
			//First draw tile
			game.sendAction(new MahjongDrawTileAction(this));
			Log.e("Computer Player", "Tile is drawn");

			hasDrawnTile = true;
		}
		//Send a draw action to exit chow mode
//		else if (mgs.isChowMode()) {
//			game.sendAction(new MahjongDrawTileAction(this));
//			hasDrawnTile = false; //just to be safe
//		}

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
            //Send chow action if in chow mode
            //Just to build in a little bit of error into Smart AI, 10% chance Smart AI skips chow entirely
            if (mgs.isChowMode() && mgs.getPlayerID() == playerNum && !hasDrawnTile) {
                double randNum = Math.random();
                //chow 90% of time
                if (randNum < 0.9) {
                    game.sendAction(new MahjongChowAction(this));
                    hasDrawnTile = true;}
                }
            else
            {
			discardHelper();
			hasDrawnTile = false;}

		}



	}

	/**
	 * code to send a discard
	 */
	protected void discardHelper() {
		Log.e("Computer Player", "Computer player discards a tile.");


		// algorithm to decide best tile to discard
		//this will look for increase in number of sets
		int result = 0;
		int bestDiscard = 0;
		String holdSuit = "";

		hand[13] = mgs.getCurrentDrawnTile();
		for(int i = 0; i < hand.length; i++){
			holdSuit = hand[i].getSuit();
			hand[i].setSuit("empty");
			if (mgs.prePerm(hand) > result){
				result = mgs.prePerm(hand);
				bestDiscard = i;
			}
			hand[i].setSuit(holdSuit);
		}

		//sets the thirteenth hand element back to null
		hand[13] = null;

		//computer player discards
		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,
				discButtonIDArray);

		discardTileAction.setDiscardButtonID(bestDiscard );
		game.sendAction(discardTileAction);

	}
}


