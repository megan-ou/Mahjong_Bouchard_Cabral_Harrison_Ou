package edu.up.cs301.mahjong;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.gameConfiguration.*;

/**
 * This is the primary activity for mahjong game
 *
 * Beta Release Missing Features:
 * 	- SwitchViewAction was not implemented because we could not figure out how to switch
 * 		GUI layouts without crashing or breaking the game. Might get rid of this feature because
 * 		of complications with Chow and time constraints. GUI for Table View is designed and in the
 * 		project file, but when trying to implement, it fell apart. We honestly ran out of time on
 * 		this function, opting to prioritize permutation and chow.
 * 	- Tutorial button was not implemented for similar reasons as the SwitchViewAction. We do not
 * 	  	know how to use different GUI layouts without crashes occurring.
 * 	- Home and Pause buttons were also not implemented due to time and knowledge constraints as
 * 		explained above for SwitchViewAction.
 *
 * Beta Release Bugs (Mostly due to running out of time to prioritize chow and permutation):
 *	- Win game message pops up when you draw a winning tile, before you can click "Win!" button.
 *		This is due to the prePerm() code that sends out a winning hand message after the draw tile
 *		action is sent in. To fix this, we will look at adding a boolean in the game state
 *		for winClicked that is true when the win button has been clicked (the only way we want the
 *		human player to be able to say they've won). Then in checkIfGameOver, before we call prePerm
 *		for the human player, we will check if winClicked is true. If not, then their hand won't be
 *		checked.
 *  - "Win" Button can be clicked at any time during the game and functions as a working tile slot
 *		when it should only be clickable to end the game. We ran out of time to code in the
 *		restriction. We plan on implementing this by making sure the specific button ID can only
 *		be clicked in human player when adding the most recently drawn tile can produce a winning
 *		hand.(Using our permutation sort method)
 *	- Similarly, computer players currently cannot win because they do not have access to the last
 *		tile slot in their code at the moment. We plan on adding in conditions that tell the computer
 *		players if the most recently drawn tile can produce a winning hand, populate the last slot.
 *	- Sometimes user can run out of tiles to draw. We are unsure on why this works as the reshuffle
 *		method has been tested multiple times to work; in our tests we have been able to send 1000
 *		draw/discard actions. Will debug more later.
 *	- TextView that says "Human Player's turn" in top left does not change yet to indicate turn. We
 *		ran out of time to update it according to player turn. This is easily fixed by updating textView
 *		in receiveInfo() of the HumanPlayer class according what the current player ID is.
 * 
 * @author Andrew M. Nuxoll
 * @author Steven R. Vegdahl
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author Landon Harrison
 * @author Megan Ou
 * @version October 2024
 */
public class MahjongMainActivity extends GameMainActivity {
	
	//The port number that this game will use when playing over the network
	private static final int PORT_NUMBER = 2234;

	/**
	 * Create the default configuration for this game:
	 * - one human player vs. three computer players
	 * - must have 4 players
	 * - two kinds of computer player and one kind of human player available
	 * 
	 * @return
	 * 		the new configuration object, representing the default configuration
	 */
	@Override
	public GameConfig createDefaultConfig() {
		
		// Define the allowed player types
		ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
		
		// a human player player type (player type 0)
		playerTypes.add(new GamePlayerType("Local Human Player") {
			public GamePlayer createPlayer(String name) {
				return new MahjongHumanPlayer(name);
			}});
		
		// a computer player type (player type 1)
		playerTypes.add(new GamePlayerType("Dumb Computer Player") {
			public GamePlayer createPlayer(String name) {
				return new MahjongComputerPlayer1(name);
			}});
		
		// a computer player type (player type 2)
		playerTypes.add(new GamePlayerType("Smart Computer Player") {
			public GamePlayer createPlayer(String name) {
				return new MahjongComputerPlayer2(name);
			}});

		// Create a game configuration class for Counter:
		// - player types as given above
		// - must have 4 players
		// - name of game is "Mahjong Game"
		// - port number as defined above
		GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Mahjong Game",
				PORT_NUMBER);

		// Add the default players to the configuration
		defaultConfig.addPlayer("Human", 0); // player 1: a human player
		defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
		defaultConfig.addPlayer("Computer 2",1); //player 3: a computer player
		defaultConfig.addPlayer("Computer 3",1); //player 4: a computer player (temporary until we add code for smart player
		
		// Set the default remote-player setup:
		// - player name: "Remote Player"
		// - IP code: (empty string)
		// - default player type: human player
		defaultConfig.setRemoteData("Remote Player", "", 0);
		
		// return the configuration
		return defaultConfig;
	}//createDefaultConfig

	/**
	 * create a local game
	 * 
	 * @return
	 * 		the local game, a mahjong game
	 */
	@Override
	public LocalGame createLocalGame(GameState state) {
		if (state == null) state = new MahjongGameState();
		return new MahjongLocalGame(state);
	}

}
