package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.players.GameHumanPlayer;
import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameInfo;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.google.android.material.drawable.DrawableUtils;

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
//	private Button btDiscDrawn = myActivity.findViewById(R.id.btDiscardDrawn);
//	private MDButton btDiscardT1 = myActivity.findViewById(R.id.btDiscSlotT1);
//	private MDButton btDiscardT2 = myActivity.findViewById(R.id.btDiscSlotT2);
//	private MDButton btDiscardT3 = myActivity.findViewById(R.id.btDiscSlotT3);
//	private MDButton btDiscardT4 = myActivity.findViewById(R.id.btDiscSlotT4);
//	private MDButton btDiscardT5 = myActivity.findViewById(R.id.btDiscSlotT5);
//	private MDButton btDiscardT6 = myActivity.findViewById(R.id.btDiscSlotT6);
//	private MDButton btDiscardT7 = myActivity.findViewById(R.id.btDiscSlotT7);
//	private MDButton btDiscardB1 = myActivity.findViewById(R.id.btDiscSlotB1);
//	private MDButton btDiscardB2 = myActivity.findViewById(R.id.btDiscSlotB2);
//	private MDButton btDiscardB3 = myActivity.findViewById(R.id.btDiscSlotB3);
//	private MDButton btDiscardB4 = myActivity.findViewById(R.id.btDiscSlotB4);
//	private MDButton btDiscardB5 = myActivity.findViewById(R.id.btDiscSlotB5);
//	private MDButton btDiscardB6 = myActivity.findViewById(R.id.btDiscSlotB6);
//	private MDButton btDiscardB7 = myActivity.findViewById(R.id.btDiscSlotB7);

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
		return myActivity.findViewById(R.id.top_gui_layout);
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

		//load GUI
		MahjongTile mt ;

		IVnum0 = myActivity.findViewById(R.id.iVCSlotT1);
		IVnum1 = myActivity.findViewById(R.id.iVCSlotT2);
		IVnum2 = myActivity.findViewById(R.id.iVCSlotT3);
		IVnum3 = myActivity.findViewById(R.id.iVCSlotT4);
		IVnum4 = myActivity.findViewById(R.id.iVCSlotT5);
		IVnum5 = myActivity.findViewById(R.id.iVCSlotT6);
		IVnum6 = myActivity.findViewById(R.id.iVCSlotT7);
		IVnum7 = myActivity.findViewById(R.id.iVCSlotB1);
		IVnum8 = myActivity.findViewById(R.id.iVCSlotB2);
		IVnum9 = myActivity.findViewById(R.id.iVCSlotB3);
		IVnum10 = myActivity.findViewById(R.id.iVCSlotB4);
		IVnum11 = myActivity.findViewById(R.id.iVCSlotB5);
		IVnum12 = myActivity.findViewById(R.id.iVCSlotB6);
		IVnum13 = myActivity.findViewById(R.id.iVCSlotB7);

		ImageView[] IVCurr = {IVnum0, IVnum1, IVnum2, IVnum3, IVnum4, IVnum5, IVnum6, IVnum7
				, IVnum8, IVnum9, IVnum10, IVnum11, IVnum12, IVnum13};
		int q = 0;

		//iterates through hand
		for(ImageView iv: IVCurr){
			//reassigns the holder mahjong tile and current image view
			if(state.getPlayerOneHand()[q] == null){ break;}
			mt = state.getPlayerOneHand()[q];
			q++;

			switch(mt.getSuit()) {
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

