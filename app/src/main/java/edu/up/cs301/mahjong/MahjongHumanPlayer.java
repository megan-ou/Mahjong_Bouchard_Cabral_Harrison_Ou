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

import java.util.ArrayList;

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

	// the most recent game state, as given to us by the CounterLocalGame
	private MahjongGameState state;
	
	// the android activity that we are running
	private GameMainActivity myActivity = null;

	//array of discard button ids
	private int[] discButtonIDArray = new int[15];

	//references to buttons
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

	//references to non-hand imageviews
	private ImageView IVDrawnCard;
	private ImageView IVlastDiscarded;

	//reference to discard pile text view
	private TextView discardPile;

	//checker for mahjong tile to prevent double printing
	MahjongTile lastDiscardCheck = new MahjongTile("null", 0);

	/**
	 * constructor
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
		//create instances of actions
		MahjongDrawTileAction drawTileAction = new MahjongDrawTileAction(this);
		MahjongDiscardTileAction discardTileAction = new MahjongDiscardTileAction(this,discButtonIDArray);
		MahjongChowAction chowAction = new MahjongChowAction(this);
		MahjongSwitchViewAction switchViewAction = new MahjongSwitchViewAction(this);

		if (button.getId() == R.id.btDraw) {
			game.sendAction(drawTileAction);
			setHandGUI(IVDrawnCard,state.getCurrentDrawnTile());
		}

		else if (isDiscardButton(button.getId())) {
			discardTileAction.setDiscardButtonID(button.getId());
			game.sendAction(discardTileAction);
			emptyDrawnCard(R.drawable.blank_tile);
			setHandGUI(IVlastDiscarded,state.getLastDiscarded());
			setHandGUI(null,null);
            myActivity.findViewById(R.id.playerName).setVisibility(View.GONE);

        }

		else if (button.getId() == R.id.btRestart){
			myActivity.findViewById(R.id.btRestart).setVisibility(View.GONE);
			state.restartGame();
			setHandGUI(null,null);
        }

		//TODO: Implement chow & switch view after alpha release
//		else if (button instanceof MChowButton) {
//			game.sendAction(chowAction);
//
//		else if (button instanceof MSwitchViewButton) {
//			game.sendAction(switchViewAction);
//		}
	}// onClick

	/**
	 * Helper method that takes the id of a clicked button from onClick() and checks if it is
	 * a discard button
	 * @param id
	 * @return
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
	 * Helper method for getting existing text in testResultsTextView
	 */
	public void updateDiscardPile (String addedText) {
		String existingText = discardPile.getText().toString();
		discardPile.setText(existingText + "\n" + addedText);
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

		setHandGUI(null, null);

		//Update the display to reflect this new state IF it is human player's turn
		if (state.getPlayerID() == 0) {
			MahjongTile drawnTile = state.getCurrentDrawnTile();
			if (drawnTile != null) {
				setHandGUI(IVDrawnCard, drawnTile);
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
	}

	/**
	 * set drawn card IV
	 */
	public void emptyDrawnCard(int imageResource) {
		IVDrawnCard = myActivity.findViewById(R.id.iVDrawnCard);

		IVDrawnCard.setImageResource(imageResource);
	}

	/**
	 * sets the tiles to the ImageViews on the GUI
	 *
	 * sets either the GUI for the hand or a single tile
	 * for a single tile send all three parameters (including your target imageview and tile)
	 * for setting the hand GUI send a null object for the second and third parameter
	 *
	 * @param iVsingle - the target image view (leave null if setting the whole hand)
	 * @param tile - the tile you want to send to the image view (leave null if setting whole hand)
	 */
	public void setHandGUI(ImageView iVsingle, MahjongTile tile){

		//load GUI
		MahjongTile mt ;

		//references to imageview objects stored in XML
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
		//iterates through hand
		for(ImageView iv: IVCurr){

			if(tile == null) {
				//reassigns the holder mahjong tile and current image view
				if (state.getPlayerOneHand()[q] == null) {
					break;
				}
				mt = state.getPlayerOneHand()[q];
				q++;
			}else{ mt = tile; }

			switch(mt.getSuit()) {
				//switch statement to set Hanzi
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
					//switch statement to set Dots
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
					//switch statement to set Sticks
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

					//switch case to set imageviews for symbols
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
	 * helper method that initializes all of the object references to the XML
	 * makes setAsGUI shorter
	 *
	 */
	public void initializeObjects(){
		//Initialize all View references
		//   image views
		IVDrawnCard = myActivity.findViewById(R.id.iVDrawnCard);
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


		//set listeners
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

		//    textviews
		discardPile = myActivity.findViewById(R.id.tvDiscardPile);
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
		activity.setContentView((R.layout.gameplay_view));

		activity.setContentView((R.layout.gameplay_view));
		initializeObjects();
		setHandGUI(null,null);

		//human player indicator set to visible
		activity.findViewById(R.id.playerName).setVisibility(View.VISIBLE);

	}
}



// class CounterHumanPlayer

