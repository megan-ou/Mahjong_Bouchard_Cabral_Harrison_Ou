package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Mahjong human player class holds all of the information the human player needs about the
 * current state of the game. The GUI displays the current state of game,
 * and allows the human player to send moves to the game.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Megan Ou
 * @author Landon Harrison
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @version October 2024
 */
public class MahjongHumanPlayer extends GameHumanPlayer implements OnClickListener, Serializable {

	/* instance variables */

	//The most recent game state, as given to us by the CounterLocalGame
	private MahjongGameState state;
	
	//The android activity that we are running
	private GameMainActivity myActivity = null;

	//Array of discard button ids
	private int[] discButtonIDArray = new int[15];

	//References to buttons
	private Button btDiscDrawn;
	private Button btDisc1;
	private Button btDisc2;
	private Button btDisc3;
	private Button btDisc4;
	private Button btDisc5;
	private Button btDisc6;
	private Button btDisc7;
	private Button btDisc8;
	private Button btDisc9;
	private Button btDisc10;
	private Button btDisc11;
	private Button btDisc12;
	private Button btDisc13;
	private Button btDisc14;
	private Button btDraw;
	private Button btRestart;
	private Button btChow;
	private Button btTableView;
	private Button btHome;
	private Button btTutorial;
	private Button btPause;
    //private Button btReturn; TODO: Old SwitchView Code

	//References to imageViews
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

	//References to non-hand imageviews
	private ImageView IVDrawnTile;
	private ImageView IVlastDiscarded;

	//Reference to discard pile text view
	private TextView discardPile;
	private TextView playerTurn;

	//Checker for mahjong tile to prevent double printing
	MahjongTile lastDiscardCheck = new MahjongTile("null", 0);

	/**
	 * Constructor: Populates discButtonIDArray with each unique discard button ID
	 *
	 * @param name
	 * 		the player's name
	 */
	public MahjongHumanPlayer(String name) {
		super(name);
		state = new MahjongGameState();

		discButtonIDArray[0] = R.id.btDiscardDrawn;
		discButtonIDArray[1] = R.id.btDiscSlot1;
		discButtonIDArray[2] = R.id.btDiscSlot2;
		discButtonIDArray[3] = R.id.btDiscSlot3;
		discButtonIDArray[4] = R.id.btDiscSlot4;
		discButtonIDArray[5] = R.id.btDiscSlot5;
		discButtonIDArray[6] = R.id.btDiscSlot6;
		discButtonIDArray[7] = R.id.btDiscSlot7;
		discButtonIDArray[8] = R.id.btDiscSlot8;
		discButtonIDArray[9] = R.id.btDiscSlot9;
		discButtonIDArray[10] = R.id.btDiscSlot10;
		discButtonIDArray[11] = R.id.btDiscSlot11;
		discButtonIDArray[12] = R.id.btDiscSlot12;
		discButtonIDArray[13] = R.id.btDiscSlot13;
		discButtonIDArray[14] = R.id.btDiscSlot14;
    } //ctor

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view hierarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.top_gui_layout);
	}

	/**
	 * This method gets called when the user clicks a button. It will determine what
	 * action needs to be made based on the button type. Finally, it'll send that action
	 * to the game state.
	 * 
	 * @param button
	 * 		the button that was clicked
	 */
	public void onClick(View button) {
		//Create instances of actions
		MahjongDrawTileAction drawTileAction = new MahjongDrawTileAction(this);
		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,discButtonIDArray);
		MahjongChowAction chowAction = new MahjongChowAction(this);
		MahjongSwitchViewAction switchViewAction = new MahjongSwitchViewAction(this);

		//Draw button
		if (button.getId() == R.id.btDraw && state.getPlayerID() == playerNum) {
			game.sendAction(drawTileAction);
			setHandGUI(IVDrawnTile,state.getCurrentDrawnTile());
		}

		//Discard button
		else if (isDiscardButton(button.getId()) && state.getPlayerID() == playerNum) {
			discardTileAction.setDiscardButtonID(button.getId());
			game.sendAction(discardTileAction);
			emptyImageView(IVDrawnTile,R.drawable.blank_tile);
			setHandGUI(IVlastDiscarded,state.getLastDiscarded());
			setHandGUI(null,null);

        }

		//Restart button
		else if (button.getId() == R.id.btRestart && state.getPlayerID() == playerNum){
			myActivity.findViewById(R.id.btRestart).setVisibility(View.GONE);
			state.restartGame();
			discardPile.setText("");
			setHandGUI(null,null);
        }

		//Chow button
		else if (button.getId() == R.id.btChow ) {
			game.sendAction(chowAction);
		}
//		//TableView button
//		else if (button.getId() == R.id.btTableView || button.getId() == R.id.btReturn) {
//			game.sendAction(switchViewAction);
//		} TODO: Old SwitchView code
	}//onClick()

	/**
	 * Helper method that takes the id of a clicked button from onClick() and checks if it is
	 * a discard button
	 *
	 * @param id - the id of the button pressed
	 * @return true if the given id is a discard button
	 */
	public boolean isDiscardButton (int id) {
		for (int i = 0; i < this.discButtonIDArray.length; i++) {
			if (id == discButtonIDArray[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the text in our GUI discard pile
	 *
	 * @param addedText - the text we want to add to the discard pile multiline text view
	 */
	public void updateDiscardPile (String addedText) {
		String existingText = discardPile.getText().toString();
		discardPile.setText(addedText + "\n" + existingText);
	}

	/**
	 * Callback method when we get a message (e.g., from the game)
	 *
	 * External Citation
	 *  Date: 11/13/2024
	 *  Problem: Could not get ImageViews to update after draw and discard
	 *  Resource: Dr. Nuxoll's office hours
	 *  Solution: Call setHandGUI() to update the display in receiveInfo every time the game state
	 *  is updated
	 * 
	 * @param info - the message
	 */
	@Override
	public void receiveInfo(GameInfo info) {
		//Ignore the message if it's not a CounterState message
		if (!(info instanceof MahjongGameState)) return;
		
		//Update our state; then update the display
		this.state = (MahjongGameState)info;

		setHandGUI(null, null);

		//Update the display to reflect this new state IF it is human player's turn
		if (state.getPlayerID() == playerNum) {
			Log.e("Player Turn", "Human player's turn." + playerNum);
			MahjongTile drawnTile = state.getCurrentDrawnTile();
			if (drawnTile != null) {
				setHandGUI(IVDrawnTile, drawnTile);
			}
			else {
				emptyImageView(IVDrawnTile,R.drawable.blank_tile);
			}
		}

		//Update the discarded tile image
		MahjongTile lastDiscardedTile = state.getLastDiscarded();
		if (lastDiscardedTile != null) {
			setHandGUI(IVlastDiscarded, lastDiscardedTile);
			if (lastDiscardCheck != state.getLastDiscarded() ) {
				updateDiscardPile(state.getLastDiscarded().toString());
				lastDiscardCheck = state.getLastDiscarded();
			}
		}

		//Change text of draw tile button if Chow Mode entered
		if (state.getPlayerID() == playerNum && state.isChowMode()) {
			btDraw.setText("Continue");
		}
		//Change text of draw tile button from continue back to draw new tile
		else {
			btDraw.setText("Draw New Tile");
		}

		//Change TextView to display whose turn it is
		String currName = allPlayerNames[state.getPlayerID()];
		playerTurn.setText(currName + "'s Turn");

	} //receiveInfo()

	/**
	 * Set drawn card IV
	 *
	 * @param view - the imageView we want to set an image for
	 * @param imageResource - the image we want to set the drawn card to
	 */
	public void emptyImageView (ImageView view, int imageResource) {
		IVDrawnTile = myActivity.findViewById(R.id.iVDrawnCard);
		view.setImageResource(imageResource);
	}

	/**
	 * Sets the tiles to the ImageViews on the GUI, our version of updateDisplay()
	 *
	 * - sets either the GUI for the hand or a single tile
	 * - for a single tile send all three parameters (including your target imageview and tile)
	 * - for setting the hand GUI send a null object for the second and third parameter
	 *
	 * @param iVsingle - the target image view (leave null if setting the whole hand)
	 * @param tile - the tile you want to send to the image view (leave null if setting whole hand)
	 */
	public void setHandGUI(ImageView iVsingle, MahjongTile tile){

		MahjongTile[] hand = new MahjongTile[14];

		switch (playerNum){
			case 0:
				hand = state.getPlayerOneHand();
				break;
			case 1:
				hand = state.getPlayerTwoHand();
				break;
			case 2:
				hand = state.getPlayerThreeHand();
				break;
			case 3:
				hand = state.getPlayerFourHand();
				break;
		}

		//Load GUI
		MahjongTile mt ;

		//References to imageview objects stored in XML
		IVnum0 = myActivity.findViewById(R.id.iVCSlot1);
		IVnum1 = myActivity.findViewById(R.id.iVCSlot2);
		IVnum2 = myActivity.findViewById(R.id.iVCSlot3);
		IVnum3 = myActivity.findViewById(R.id.iVCSlot4);
		IVnum4 = myActivity.findViewById(R.id.iVCSlot5);
		IVnum5 = myActivity.findViewById(R.id.iVCSlot6);
		IVnum6 = myActivity.findViewById(R.id.iVCSlot7);
		IVnum7 = myActivity.findViewById(R.id.iVCSlot8);
		IVnum8 = myActivity.findViewById(R.id.iVCSlot9);
		IVnum9 = myActivity.findViewById(R.id.iVCSlot10);
		IVnum10 = myActivity.findViewById(R.id.iVCSlot11);
		IVnum11 = myActivity.findViewById(R.id.iVCSlot12);
		IVnum12 = myActivity.findViewById(R.id.iVCSlot13);
		IVnum13 = myActivity.findViewById(R.id.iVCSlotB14);
		ArrayList<ImageView> IVCurr = new ArrayList<>();

		if(tile == null) {
			IVCurr.add(IVnum0);	IVCurr.add(IVnum1);	IVCurr.add(IVnum2);
			IVCurr.add(IVnum3); IVCurr.add(IVnum4); IVCurr.add(IVnum5);
			IVCurr.add(IVnum6); IVCurr.add(IVnum7); IVCurr.add(IVnum8);
			IVCurr.add(IVnum9); IVCurr.add(IVnum10); IVCurr.add(IVnum11);
			IVCurr.add(IVnum12); IVCurr.add(IVnum13);

			}else {
			IVCurr.add(iVsingle);
		}
		int q = 0;
		//Iterates through hand
		for(ImageView iv: IVCurr){

			if(tile == null) {
				//reassigns the holder mahjong tile and current image view
				if (hand == null) {
					break;
				}
				mt = hand[q];
				q++;
			}else{ mt = tile; }

			switch(mt.getSuit()) {
				//Switch statement to set Hanzi
				case "Hanzi":
					switch (mt.getValue()) {
						case 1:
							iv.setImageResource(R.drawable.c_num_1);
							break;
						case 2:
							iv.setImageResource(R.drawable.c_num_2);
							break;
						case 3:
							iv.setImageResource(R.drawable.c_num_3);
							break;
						case 4:
							iv.setImageResource(R.drawable.c_num_4);
							break;
						case 5:
							iv.setImageResource(R.drawable.c_num_5);
							break;
						case 6:
							iv.setImageResource(R.drawable.c_num_6);
							break;
						case 7:
							iv.setImageResource(R.drawable.c_num_7);
							break;
						case 8:
							iv.setImageResource(R.drawable.c_num_8);
							break;
						case 9:
							iv.setImageResource(R.drawable.c_num_9);
							break;
					}
					break;
					//Switch statement to set Dots
				case "Dots":
					switch (mt.getValue()) {
						case 1:
							iv.setImageResource(R.drawable.dots_1);
							break;
						case 2:
							iv.setImageResource(R.drawable.dots_2);
							break;
						case 3:
							iv.setImageResource(R.drawable.dots_3);
							break;
						case 4:
							iv.setImageResource(R.drawable.dots_4);
							break;
						case 5:
							iv.setImageResource(R.drawable.dots_5);
							break;
						case 6:
							iv.setImageResource(R.drawable.dots_6);
							break;
						case 7:
							iv.setImageResource(R.drawable.dots_7);
							break;
						case 8:
							iv.setImageResource(R.drawable.dots_8);
							break;
						case 9:
							iv.setImageResource(R.drawable.dots_9);
							break;
					}
					break;
					//Switch statement to set Sticks
				case "Sticks":
					switch (mt.getValue()) {
						case 1:
							iv.setImageResource(R.drawable.sticks_1);
							break;
						case 2:
							iv.setImageResource(R.drawable.sticks_2);
							break;
						case 3:
							iv.setImageResource(R.drawable.sticks_3);
							break;
						case 4:
							iv.setImageResource(R.drawable.sticks_4);
							break;
						case 5:
							iv.setImageResource(R.drawable.sticks_5);
							break;
						case 6:
							iv.setImageResource(R.drawable.sticks_6);
							break;
						case 7:
							iv.setImageResource(R.drawable.sticks_7);
							break;
						case 8:
							iv.setImageResource(R.drawable.sticks_8);
							break;
						case 9:
							iv.setImageResource(R.drawable.sticks_9);
							break;
					}
					break;

					//Switch case to set imageviews for symbols
				case "Cat":
					iv.setImageResource(R.drawable.cat);
					break;
				case "Fire":
					iv.setImageResource(R.drawable.fire);
					break;
				case "Earth":
					iv.setImageResource(R.drawable.earth);
					break;
				case "Flower":
					iv.setImageResource(R.drawable.flower);
					break;
				case "Star":
					iv.setImageResource(R.drawable.star);
					break;
				case "Water":
					iv.setImageResource(R.drawable.water);
					break;
				case "Wind":
					iv.setImageResource(R.drawable.wind);
					break;
				case "String":
					iv.setImageResource(R.drawable.blank_tile);
					break;
				case "empty":
					iv.setImageResource(R.drawable.blank_tile);
			}
		}
	}//setHandGUI()

	/**
	 * Helper method that initializes all of the object references to the XML
	 * makes setAsGUI shorter
	 */
	public void initializeObjects(){
		//Initialize all View references
		//   image views
		IVDrawnTile = myActivity.findViewById(R.id.iVDrawnCard);
		IVlastDiscarded = myActivity.findViewById(R.id.iVLastDiscarded);

		//   buttons
		btDiscDrawn = myActivity.findViewById(R.id.btDiscardDrawn);
		btDisc1 = myActivity.findViewById(R.id.btDiscSlot1);
		btDisc2 = myActivity.findViewById(R.id.btDiscSlot2);
		btDisc3 = myActivity.findViewById(R.id.btDiscSlot3);
		btDisc4 = myActivity.findViewById(R.id.btDiscSlot4);
		btDisc5 = myActivity.findViewById(R.id.btDiscSlot5);
		btDisc6 = myActivity.findViewById(R.id.btDiscSlot6);
		btDisc7 = myActivity.findViewById(R.id.btDiscSlot7);
		btDisc8 = myActivity.findViewById(R.id.btDiscSlot8);
		btDisc9 = myActivity.findViewById(R.id.btDiscSlot9);
		btDisc10 = myActivity.findViewById(R.id.btDiscSlot10);
		btDisc11 = myActivity.findViewById(R.id.btDiscSlot11);
		btDisc12 = myActivity.findViewById(R.id.btDiscSlot12);
		btDisc13 = myActivity.findViewById(R.id.btDiscSlot13);
		btDisc14 = myActivity.findViewById(R.id.btDiscSlot14);
		btDraw = myActivity.findViewById(R.id.btDraw);
		btRestart = myActivity.findViewById(R.id.btRestart);
		btChow = myActivity.findViewById(R.id.btChow);
		btTableView = myActivity.findViewById(R.id.btTableView);
		btHome = myActivity.findViewById(R.id.btHome);
		btHome.setVisibility(View.GONE);
		btTutorial = myActivity.findViewById(R.id.btTutorial);
		btTutorial.setVisibility(View.GONE);
		btPause = myActivity.findViewById(R.id.btPause);
		btPause.setVisibility(View.GONE);
		//btReturn = myActivity.findViewById(R.id.btReturn); TODO: old SwitchView code

		//Set listeners
		btDiscDrawn.setOnClickListener(this);
		btDisc1.setOnClickListener(this);
		btDisc2.setOnClickListener(this);
		btDisc3.setOnClickListener(this);
		btDisc4.setOnClickListener(this);
		btDisc5.setOnClickListener(this);
		btDisc6.setOnClickListener(this);
		btDisc7.setOnClickListener(this);
		btDisc8.setOnClickListener(this);
		btDisc9.setOnClickListener(this);
		btDisc10.setOnClickListener(this);
		btDisc11.setOnClickListener(this);
		btDisc12.setOnClickListener(this);
		btDisc13.setOnClickListener(this);
		btDisc14.setOnClickListener(this);
		btDraw.setOnClickListener(this);
		btRestart.setOnClickListener(this);
		btChow.setOnClickListener(this);
		btTableView.setOnClickListener(this);
		//btReturn.setOnClickListener(this); TODO: old SwitchView code

		//    textviews
		discardPile = myActivity.findViewById(R.id.mlDiscardPile);
		playerTurn = myActivity.findViewById(R.id.playerName);
	}//initializeObjects()

	/**
	 * Callback method--our game has been chosen/rechosen to be the GUI,
	 * called from the GUI thread
	 *
	 * External Citation
	 * Date: 11/12/2024
	 * Problem: Null button error was being thrown even after initialization
	 * Resource: Dr. Nuxoll's office hours
	 * Solution: Firstly, instantiate GUI objects after intitializing myActivity
	 * 
	 * @param activity - the activity under which we are running
	 */
	public void setAsGui(GameMainActivity activity) {

		//Remember the activity
		this.myActivity = activity;
		
	    //Load the layout resource for our GUI
		activity.setContentView(R.layout.gameplay_view);

		initializeObjects();
		setHandGUI(null,null);

	}
}// class CounterHumanPlayer

