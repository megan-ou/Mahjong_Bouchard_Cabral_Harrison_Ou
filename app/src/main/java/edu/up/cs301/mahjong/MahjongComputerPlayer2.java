package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;



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
public class MahjongComputerPlayer2 extends MahjongComputerPlayer1 {
	
	/*
	 * instance variables
	 */
	
	// the most recent game state, as given to us by the CounterLocalGame
	private MahjongGameState currentGameState = null;
	
	// If this player is running the GUI, the activity (null if the player is
	// not running a GUI).
	private Activity activityForGui = null;
	
	// If this player is running the GUI, the widget containing the counter's
	// value (otherwise, null);
	private TextView counterValueTextView = null;
	
	// If this player is running the GUI, the handler for the GUI thread (otherwise
	// null)
	private Handler guiHandler = null;
	
	/**
	 * constructor
	 * 
	 * @param name
	 * 		the player's name
	 */
	public MahjongComputerPlayer2(String name) {
		super(name);
	}
	
    /**
     * callback method--game's state has changed
     * 
     * @param info
     * 		the information (presumably containing the game's state)
     */
	@Override
	protected void receiveInfo(GameInfo info) {
		// perform superclass behavior
		super.receiveInfo(info);
		
		Log.i("computer player", "receiving");
		
		// if there is no game, ignore
		if (game == null) {
			return;
		}
		else if (info instanceof MahjongGameState) {
			// if we indeed have a counter-state, update the GUI
			currentGameState = (MahjongGameState)info;
			updateDisplay();
		}
	}
	
	
	/** 
	 * sets the counter value in the text view
	 *  */
	private void updateDisplay() {
		// if the guiHandler is available, set the new counter value
		// in the counter-display widget, doing it in the Activity's
		// thread.
		if (guiHandler != null) {
			guiHandler.post(
					new Runnable() {
						public void run() {
						if (counterValueTextView != null && currentGameState != null) {
							//counterValueTextView.setText("" + currentGameState.getCounter());
						}
					}});
		}
	}
	
	/**
	 * Tells whether we support a GUI
	 * 
	 * @return
	 * 		true because we support a GUI
	 */
	public boolean supportsGui() {
		return true;
	}
	
	/**
	 * callback method--our player has been chosen/rechosen to be the GUI,
	 * called from the GUI thread.
	 * 
	 * @param a
	 * 		the activity under which we are running
	 */
	@Override
	public void setAsGui(GameMainActivity a) {
		
		// remember who our activity is
		this.activityForGui = a;
		
		// remember the handler for the GUI thread
		this.guiHandler = new Handler();
		
		// Load the layout resource for the our GUI's configuration
		activityForGui.setContentView(R.layout.counter_human_player);

		// remember who our text view is, for updating the counter value
		this.counterValueTextView =
				(TextView) activityForGui.findViewById(R.id.testResultsTextView);
		
		// disable the buttons, since they will have no effect anyway
		Button plusButton = (Button)activityForGui.findViewById(R.id.plusButton);
		plusButton.setEnabled(false);
		Button minusButton = (Button)activityForGui.findViewById(R.id.minusButton);
		minusButton.setEnabled(false);
		
		// if the state is non=null, update the display
		if (currentGameState != null) {
			updateDisplay();
		}
	}

}
