package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

/**
 * A GUI of a Mahjong-player. The GUI displays the current state of game,
 * and allows the human player to send moves to the game.
 *
 *
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Megan Ou
 * @author Landon Harrison
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @version October 2024
 */
public class MahjongHumanPlayer extends GameHumanPlayer implements OnClickListener {

	/* instance variables */
	
	// The TextView the displays the current counter value
	private TextView testResultsTextView;
	//private Button btRunTest;

	// the most recent game state, as given to us by the CounterLocalGame
	private MahjongGameState state;
	
	// the android activity that we are running
	private GameMainActivity myActivity;


	//references to buttons
	private Button btDiscDrawn = myActivity.findViewById(R.id.btDiscardDrawn);
	private MDButton btDiscardT1 = myActivity.findViewById(R.id.btDiscSlotT1);
	private MDButton btDiscardT2 = myActivity.findViewById(R.id.btDiscSlotT2);
	private MDButton btDiscardT3 = myActivity.findViewById(R.id.btDiscSlotT3);
	private MDButton btDiscardT4 = myActivity.findViewById(R.id.btDiscSlotT4);
	private MDButton btDiscardT5 = myActivity.findViewById(R.id.btDiscSlotT5);
	private MDButton btDiscardT6 = myActivity.findViewById(R.id.btDiscSlotT6);
	private MDButton btDiscardT7 = myActivity.findViewById(R.id.btDiscSlotT7);
	private MDButton btDiscardB1 = myActivity.findViewById(R.id.btDiscSlotB1);
	private MDButton btDiscardB2 = myActivity.findViewById(R.id.btDiscSlotB2);
	private MDButton btDiscardB3 = myActivity.findViewById(R.id.btDiscSlotB3);
	private MDButton btDiscardB4 = myActivity.findViewById(R.id.btDiscSlotB4);
	private MDButton btDiscardB5 = myActivity.findViewById(R.id.btDiscSlotB5);
	private MDButton btDiscardB6 = myActivity.findViewById(R.id.btDiscSlotB6);
	private MDButton btDiscardB7 = myActivity.findViewById(R.id.btDiscSlotB7);

	//references to imageViews
	private ImageView IVnum0;
	private ImageView IVnum1;
	private ImageView IVnum2;
	private ImageView IVnum3;
	private ImageView IVnum4;
	private ImageView IVnum5;
	private ImageView IVnum6;
	private ImageView IVnum7;
	private ImageView IVnum8;
	private ImageView IVnum9;
	private ImageView IVnum10;
	private ImageView IVnum11;
	private ImageView IVnum12;
	private ImageView IVnum13;
	/**
	 * constructor
	 * @param name
	 * 		the player's name
	 */
	public MahjongHumanPlayer(String name) {
		super(name);
		state = new MahjongGameState();
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.multiLineRunTest);
	}
	
	/**
	 * sets the counter value in the text view
	 */
	protected void updateDisplay() {
		// set the text in the appropriate widget
		//counterValueTextView.setText("" + state.getCounter());
	}

	/**
	 * This method gets called when the user clicks the run test button. It then repeatedly
	 * calls methods in the GameState class to test if it will run
	 * 
	 * @param button
	 * 		the button that was clicked
	 *
	 * 	External Citation
	 * 	 Date:     27 October 2024
	 * 	 Problem:  Didn't know how to append text to a multi-line EditText.
	 * 	 Resource: Google AI from question "appending to multi-line edit text java and xml"
	 * 	 Solution: We used the example code that Google AI showed us and implemented something
	 * 	 		   similar in our code
	 */

	public void onClick(View button) {
		//create instances of actions
		MahjongDrawTileAction drawTileAction = new MahjongDrawTileAction(this);
		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this);
		MahjongChowAction chowAction = new MahjongChowAction(this);
		MahjongSwitchViewAction switchViewAction = new MahjongSwitchViewAction(this);

		String existingText;
		String newText;

		//Any text from previous run cleared
		testResultsTextView.setText("");

		// if we are not yet connected to a game, ignore
		if (game == null) return;

		MahjongGameState firstInstance = new MahjongGameState();

		MahjongGameState firstCopy = new MahjongGameState(firstInstance);

		//Any text from previous run cleared
		testResultsTextView.setText("");

		firstInstance.startGame();
		existingText = getExistingText();
		newText = "Game has begun and card are dealt.";

		testResultsTextView.setText(existingText + "\n" + newText);

		//Call draw tile method for human player
		if (firstInstance.makeDrawTileAction(drawTileAction)) {
			existingText = getExistingText();
			newText = "Player 1 draws the " + firstInstance.getLastDrawnTile() + ".";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		//TODO: write code to specify what tile was discarded, we spent a few hours
		// on this code and needed to move on. Make clearTile method which sets the image
		// in the GUI to a null slot.
		if (firstInstance.makeDiscardAction(discardTileAction)) {
			/*existingText = getExistingText();
			newText = "Player 1 discards a tile.";

			testResultsTextView.setText(existingText + "\n" + newText);*/

			if (button instanceof MDButton) {
				//change hand--> change pointer of discardedTile to a null object
				//get the last drawn tile
				//change pointer of previously discarded tile to the last drawn tile
				//call the sort method
				//call the setImageView method
				if (button == btDiscardT1) {

				}
				else if (button == btDiscardT2) {

				}
			}
			else if (button instanceof Button) {
				//clear discDrawn image view
				//set the discDrawn image view to null
				if (button == btDiscDrawn) {

				}
			}
		}

		//TODO: write code to specify what tile was ponged
		if (firstInstance.makeChowAction(chowAction)) {
			existingText = getExistingText();
			newText = "Player 3 called pong to take player 2's discarded tile. " +
					"Player 3's completed set of 3 is revealed.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeSwitchViewAction(switchViewAction)) {
			existingText = getExistingText();
			newText = "Player 1 switches to Table View to see which tiles were revealed.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeSwitchViewAction(switchViewAction)) {
			existingText = getExistingText();
			newText = "Player 1 switches back to Game View to continue playing.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeDrawTileAction(drawTileAction)) {
			existingText = getExistingText();
			newText = "Player 2 draws the " + firstInstance.getLastDrawnTile() + ".";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeDiscardAction(discardTileAction)) {
			existingText = getExistingText();
			newText = "Player 2 discards a tile.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		//TODO: write code to specify what tile was chowed
		if (firstInstance.makeChowAction(chowAction)) {
			existingText = getExistingText();
			newText = "Player 4 called chow to take player 3's discarded tile. " +
					"Player 4's completed set of 3 is revealed.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeSwitchViewAction(switchViewAction)) {
			existingText = getExistingText();
			newText = "Player 1 switches to Table View to see which tiles were revealed.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeSwitchViewAction(switchViewAction)) {
			existingText = getExistingText();
			newText = "Player 1 switches back to Game View to continue playing.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeDrawTileAction(drawTileAction)) {
			existingText = getExistingText();
			newText = "Player 3 draws the " + firstInstance.getLastDrawnTile() + ".";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeDiscardAction(discardTileAction)) {
			existingText = getExistingText();
			newText = "Player 3 discards a tile.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeDrawTileAction(drawTileAction)) {
			existingText = getExistingText();
			newText = "Player 4 draws the " + firstInstance.getLastDrawnTile() + ".";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		if (firstInstance.makeDiscardAction(discardTileAction)) {
			existingText = getExistingText();
			newText = "Player 4 discards a tile.";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		//TODO: write code to specify what tile was chowed
		if (firstInstance.makeChowAction(chowAction)) {
			existingText = getExistingText();
			newText = "Player 1 called chow to take player 4's discarded pile";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		existingText = getExistingText();
		testResultsTextView.setText(existingText + "\n" + "For brevity, assume draw, discard, pong, and switch view " +
				"continues between all 4 players until game is over");

		gameIsOver("Player 1 won the game!");

		MahjongGameState secondInstance = new MahjongGameState();

		MahjongGameState secondCopy = new MahjongGameState(secondInstance);

		String firstCopyString;
		String secondCopyString;

		firstCopyString = firstCopy.toString();
		secondCopyString = secondCopy.toString();

		//check if first copy and second copy are equal

		if (firstCopyString.equals(secondCopyString)) {
			existingText = getExistingText();
			newText = "First copy and second copy are identical";

			testResultsTextView.setText(existingText + "\n" + newText);
		}

		//print out first copy and second copy string
		existingText = getExistingText();
		newText = firstCopyString + "\n" + secondCopyString;

		testResultsTextView.setText(existingText + "\n" + newText);




	}// onClick

	/**
	 * Helper method for getting existing text in testResultsTextView
	 */
	public String getExistingText () {
		return testResultsTextView.getText().toString();
	}


	/**
	 * callback method when we get a message (e.g., from the game)
	 * 
	 * @param info
	 * 		the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		// ignore the message if it's not a CounterState message
		if (!(info instanceof MahjongGameState)) return;
		
		// update our state; then update the display
		this.state = (MahjongGameState)info;
		updateDisplay();
	}


	/**
	 * sets the tiles to the ImageViews on the GUI
	 *
	 */
	public void setTiles(){

		//load GUI
		MahjongTile mt ;
		ImageView IVCurr = null;

		//iterates through hand
		for(int q = 0; q < 12; q++){
			//reassigns the holder mahjong tile and current image view
			mt = state.getPlayerOneHand()[q];
			switch(q){
				case 0:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT1);
					break;
				case 1:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT2);
					break;
				case 2:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT3);
					break;
				case 3:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT4);
					break;
				case 4:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT5);
					break;
				case 5:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT6);
					break;
				case 6:
					IVCurr = myActivity.findViewById(R.id.iVCSlotT7);
					break;
				case 7:
					IVCurr = myActivity.findViewById(R.id.iVCSlotB1);
					break;
				case 8:
					IVCurr  = myActivity.findViewById(R.id.iVCSlotB2);
					break;
				case 9:
					IVCurr  = myActivity.findViewById(R.id.iVCSlotB3);
					break;
				case 10:
					IVCurr  = myActivity.findViewById(R.id.iVCSlotB4);
					break;
				case 11:
					IVCurr = myActivity.findViewById(R.id.iVCSlotB5);
					break;
				case 12:
					IVCurr = myActivity.findViewById(R.id.iVCSlotB6);
					break;
				case 13:
					IVCurr = myActivity.findViewById(R.id.iVCSlotB7);
					break;

			}
			switch(mt.getSuit()) {
				case "hanzi":
					switch (mt.getValue()) {
						case 1:
							IVCurr.setImageResource(R.drawable.c_num_1);
							break;
						case 2:
							IVCurr.setImageResource(R.drawable.c_num_2);
							break;
						case 3:
							IVCurr.setImageResource(R.drawable.c_num_3);
							break;
						case 4:
							IVCurr.setImageResource(R.drawable.c_num_4);
							break;
						case 5:
							IVCurr.setImageResource(R.drawable.c_num_5);
							break;
						case 6:
							IVCurr.setImageResource(R.drawable.c_num_6);
							break;
						case 7:
							IVCurr.setImageResource(R.drawable.c_num_7);
							break;
						case 8:
							IVCurr.setImageResource(R.drawable.c_num_8);
							break;
						case 9:
							IVCurr.setImageResource(R.drawable.c_num_9);
							break;
					}
					break;
				case "dots":
					switch (mt.getValue()) {
						case 1:
							IVCurr.setImageResource(R.drawable.dots_1);
							break;
						case 2:
							IVCurr.setImageResource(R.drawable.dots_2);
							break;
						case 3:
							IVCurr.setImageResource(R.drawable.dots_3);
							break;
						case 4:
							IVCurr.setImageResource(R.drawable.dots_4);
							break;
						case 5:
							IVCurr.setImageResource(R.drawable.dots_5);
							break;
						case 6:
							IVCurr.setImageResource(R.drawable.dots_6);
							break;
						case 7:
							IVCurr.setImageResource(R.drawable.dots_7);
							break;
						case 8:
							IVCurr.setImageResource(R.drawable.dots_8);
							break;
						case 9:
							IVCurr.setImageResource(R.drawable.dots_9);
							break;
					}
				case "sticks":
					switch (mt.getValue()) {
						case 1:
							IVCurr.setImageResource(R.drawable.sticks_1);
							break;
						case 2:
							IVCurr.setImageResource(R.drawable.sticks_2);
							break;
						case 3:
							IVCurr.setImageResource(R.drawable.sticks_3);
							break;
						case 4:
							IVCurr.setImageResource(R.drawable.sticks_4);
							break;
						case 5:
							IVCurr.setImageResource(R.drawable.sticks_5);
							break;
						case 6:
							IVCurr.setImageResource(R.drawable.sticks_6);
							break;
						case 7:
							IVCurr.setImageResource(R.drawable.sticks_7);
							break;
						case 8:
							IVCurr.setImageResource(R.drawable.sticks_8);
							break;
						case 9:
							IVCurr.setImageResource(R.drawable.sticks_9);
							break;
					}
				case "cat":
					IVCurr.setImageResource(R.drawable.cat);
					break;
				case "fire":
					IVCurr.setImageResource(R.drawable.fire);
					break;
				case "earth":
					IVCurr.setImageResource(R.drawable.earth);
					break;
				case "flower":
					IVCurr.setImageResource(R.drawable.flower);
					break;
				case "star":
					IVCurr.setImageResource(R.drawable.star);
					break;
				case "water":
					IVCurr.setImageResource(R.drawable.water);
					break;
				case "wind":
					IVCurr.setImageResource(R.drawable.wind);
					break;
			}
		}


	}

	/**
	 * callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 * 
	 * @param activity
	 * 		the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {
		
		// remember the activity
		this.myActivity = activity;
		
	    // Load the layout resource for our GUI
		activity.setContentView((R.layout.activity_main_09_22));

		activity.setContentView((R.layout.activity_main_09_22));
		state.dealTiles();
		state.sortDeck();

		//testResultsTextView = activity.findViewById(R.id.multiLineRunTest);

		//btRunTest = activity.findViewById(R.id.btRunTest);

		//btRunTest.setOnClickListener(this);
		}
	}



// class CounterHumanPlayer

