package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.view.View;
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

//TODO: instantiate the buttons
	//references to buttons
	private MDiscButton btDiscDrawn = new MDiscButton(myActivity.getApplicationContext(),null,0);
	private MDiscButton btDiscardT1 = myActivity.findViewById(R.id.btDiscSlot1);
	private MDiscButton btDiscardT2 = myActivity.findViewById(R.id.btDiscSlot2);
	private MDiscButton btDiscardT3 = myActivity.findViewById(R.id.btDiscSlot3);
	private MDiscButton btDiscardT4 = myActivity.findViewById(R.id.btDiscSlot4);
	private MDiscButton btDiscardT5 = myActivity.findViewById(R.id.btDiscSlot5);
	private MDiscButton btDiscardT6 = myActivity.findViewById(R.id.btDiscSlot6);
	private MDiscButton btDiscardT7 = myActivity.findViewById(R.id.btDiscSlot7);
	private MDiscButton btDiscardB1 = myActivity.findViewById(R.id.btDiscSlot8);
	private MDiscButton btDiscardB2 = myActivity.findViewById(R.id.btDiscSlot9);
	private MDiscButton btDiscardB3 = myActivity.findViewById(R.id.btDiscSlot10);
	private MDiscButton btDiscardB4 = myActivity.findViewById(R.id.btDiscSlot11);
	private MDiscButton btDiscardB5 = myActivity.findViewById(R.id.btDiscSlot12);
	private MDiscButton btDiscardB6 = myActivity.findViewById(R.id.btDiscSlot13);
	private MDiscButton btDiscardB7 = myActivity.findViewById(R.id.btDiscSlot14);

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
		btDiscDrawn = (MDiscButton) myActivity.findViewById(R.id.btDiscardDrawn);
	}

	/**
	 * Returns the GUI's top view object
	 * 
	 * @return
	 * 		the top object in the GUI's view heirarchy
	 */
	public View getTopView() {
		return myActivity.findViewById(R.id.top_gui_layout);
	}
	
	/**
	 * sets the discard pile in the text view
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
	 */

	public void onClick(View button) {
		//create instances of actions
		MahjongDrawTileAction drawTileAction = new MahjongDrawTileAction(this);
		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this);
		MahjongChowAction chowAction = new MahjongChowAction(this);
		MahjongSwitchViewAction switchViewAction = new MahjongSwitchViewAction(this);





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
	public void setTileGUI(GameMainActivity activity){

		ImageView IVCurr = activity.findViewById(R.id.iVCSlot1);
		IVCurr.setImageResource(R.drawable.star);


		//load GUI
		MahjongTile mt ;
		IVCurr = null;

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

		//iterates through hand
		for(int q = 0; q < 12; q++){
			//reassigns the holder mahjong tile and current image view
			mt = state.getPlayerOneHand()[q];
			switch(q){
				case 0:
					IVCurr = this.IVnum0;
					break;
				case 1:
					IVCurr = this.IVnum1;
					break;
				case 2:
					IVCurr = this.IVnum2;
					break;
				case 3:
					IVCurr = this.IVnum3;
					break;
				case 4:
					IVCurr = IVnum4;
					break;
				case 5:
					IVCurr = IVnum5;
					break;
				case 6:
					IVCurr = IVnum6;
					break;
				case 7:
					IVCurr = IVnum7;
					break;
				case 8:
					IVCurr  = IVnum8;
					break;
				case 9:
					IVCurr  = IVnum9;
					break;
				case 10:
					IVCurr  = IVnum10;
					break;
				case 11:
					IVCurr = IVnum11;
					break;
				case 12:
					IVCurr = IVnum12;
					break;
				case 13:
					IVCurr = IVnum13;
					break;

			}
			switch(mt.getSuit()) {
				case "Hanzi":
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
				case "Dots":
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
				case "Sticks":
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
				case "Cat":
					IVCurr.setImageResource(R.drawable.cat);
					break;
				case "Fire":
					IVCurr.setImageResource(R.drawable.fire);
					break;
				case "Earth":
					IVCurr.setImageResource(R.drawable.earth);
					break;
				case "Flower":
					IVCurr.setImageResource(R.drawable.flower);
					break;
				case "Star":
					IVCurr.setImageResource(R.drawable.star);
					break;
				case "Water":
					IVCurr.setImageResource(R.drawable.water);
					break;
				case "Wind":
					IVCurr.setImageResource(R.drawable.wind);
					break;
				case "String":
					IVCurr.setImageResource(R.drawable.blank_tile);
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
		state.sortHand(state.getPlayerOneHand());
		setTileGUI(this.myActivity);




		//testResultsTextView = activity.findViewById(R.id.multiLineRunTest);

		//btRunTest = activity.findViewById(R.id.btRunTest);

		//btRunTest.setOnClickListener(this);
		}
	}



// class CounterHumanPlayer

