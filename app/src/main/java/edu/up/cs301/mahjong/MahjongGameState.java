package edu.up.cs301.mahjong;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.up.cs301.GameFramework.infoMessage.GameState;


/**
 * This contains the state for the Counter game. The state consist of simply
 * the value of the counter.
 * 
 * @author Steven R. Vegdahl
 * @version July 2013
 */
public class MahjongGameState extends GameState {
	
	// to satisfy Serializable interface
	private static final long serialVersionUID = 7737393762469851826L;

	int playerID;
	boolean isTurn;
	boolean isPair;
	boolean isSet;
	int numSets;
	int numPairs;
	MahjongTiles[] currentHand;
	MahjongTiles currentDrawnTile;
	MahjongTiles lastDiscarded;
	ArrayList<MahjongTiles> deck;

	/**
	 * default ctor
	 */
	MahjongGameState(){
		this.playerID = 0;
		this.isTurn = false;
		this.isPair = false;
		this.isSet = false;
		this.numSets = 0;
		this.numPairs = 0;
		this.currentHand = null;
		this.currentDrawnTile = null;
		this.lastDiscarded = null;
		this.deck = null;

	}

	/**
	 * Copy ctor
	 */
	public MahjongGameState(MahjongGameState mgs){
		this.playerID = mgs.playerID;
		this.isTurn = mgs.isTurn;
		this.isPair = mgs.isPair;
		this.isSet = mgs.isSet;
		this.numSets = mgs.numSets;
		this.numPairs = mgs.numPairs;
		this.currentHand = mgs.currentHand;
		this.currentDrawnTile = mgs.currentDrawnTile;
		this.lastDiscarded = mgs.lastDiscarded;
		this.deck = mgs.deck;

	}
}
