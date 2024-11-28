package edu.up.cs301.mahjong;

import java.util.ArrayList;

import edu.up.cs301.GameFramework.GameMainActivity;
import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.gameConfiguration.*;

/**
 * this is the primary activity for mahjong game
 *
 * Beta Release Missing Features:
 * 	- SwitchViewAction was not implemented because we could not figure out
 * Beta Release Bugs:
 *
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
	
	// the port number that this game will use when playing over the network
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
