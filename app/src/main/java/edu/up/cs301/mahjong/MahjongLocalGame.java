package edu.up.cs301.mahjong;

import edu.up.cs301.GameFramework.infoMessage.GameState;
import edu.up.cs301.GameFramework.players.GamePlayer;
import edu.up.cs301.GameFramework.LocalGame;
import edu.up.cs301.GameFramework.actionMessage.GameAction;
import edu.up.cs301.mahjong.tiles.MahjongTile;

import android.util.Log;

import java.io.Serializable;

/**
 * A class that represents the state of a game. In our mahjong game, the relevant information is
 * how many pairs and sets a player has.
 *
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @author Jacqui Bouchard
 * @author Jazmine Cabral
 * @author Landon Harrison
 * @author Megan Ou
 * @version October 2024
 */
public class MahjongLocalGame extends LocalGame implements Serializable {

    //The game's state
    private MahjongGameState gameState;
    //Tells game you can only draw once and you can only discard if a tile is drawn
    private boolean hasDrawnTile;

    private MahjongTile drawnTile;

    private MahjongTile chowTile;

    /**
     * Can this player move
     *
     * @param playerIdx - the id of the player we are checking
     * @return - true if it is player's turn
     * - false if it is not player's turn
     */
    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == gameState.getPlayerID();
    }

    /**
     * This ctor should be called when a new counter game is started
     *
     * @param state - the state of the game
     */
    public MahjongLocalGame(GameState state) {
        //Initialize the game state
        if (!(state instanceof MahjongGameState)) {
            state = new MahjongGameState();
        }
        this.gameState = (MahjongGameState) state;
        super.state = state;

        hasDrawnTile = false;
        drawnTile = null;
        chowTile = null;
    }

    /**
     * There are four different actions a user can take: draw tile, discard tile, chow a discarded
     * tile, and switch view (between table view and gameplay)
     *
     * External Citation:
     * Date: 11/27/2024
     * 	 Problem: Could not find source of a crash, how to use debugger.
     * 	 Resource: Dr. Nuxoll's office hours
     * 	 Solution: Run debugger, then place breakpoints. Re-ordered some of the code
     * 	    in makeMove() method to help with when to turn on and off chow mode in discardAction.
     * 	    Set chowTile variable in setChowMode() method IF chowMode was turned on.
     *
     *
     * @param action - the action received
     */
    @Override
    protected boolean makeMove(GameAction action) {
        int playerID = gameState.getPlayerID();
        drawnTile = gameState.getCurrentDrawnTile();
        MahjongTile[] currHand = new MahjongTile[14]; //copy of current player's hand

        //If drawable tiles run out, reshuffle the discard pile and make all discarded tiles
        //drawable
        if (!tileDrawable()) {
            gameState.reshuffleDiscard();
        }

        Log.i("action", action.getClass().toString());


        if (canMove(playerID)) {
            //set current hand
            switch (playerID){
                case 0:
                    currHand = gameState.getPlayerOneHand();
                    break;
                case 1:
                    currHand = gameState.getPlayerTwoHand();
                    break;
                case 2:
                    currHand = gameState.getPlayerThreeHand();
                    break;
                case 3:
                    currHand = gameState.getPlayerFourHand();
                    break;
            }

            if (action instanceof MahjongDrawTileAction && !hasDrawnTile) {
                //If it is chow mode and draw tile (renamed to continue) is clicked,
                //exit chow mode
                if (gameState.isChowMode()) {
                    //Turn off chow mode
                    gameState.setChowMode(-1);

                    //discard the chow tile
                    chowTile.setTileStatus(0);
                    chowTile.setLocationNum(5);
                    gameState.setCurrentDrawnTile(null);
                    return true;
                }

                //Draw tiles normally
                else {
                    //Keep randomly selecting a tile until an unused tile is drawn
                    while (!hasDrawnTile) {
                        drawnTile = gameState.getDeck()[(int) (Math.random() * 135.0)];
                        if (drawnTile.getLocationNum() == 0) {
                            gameState.setCurrentDrawnTile(drawnTile);
                            drawnTile.setLocationNum(gameState.getPlayerID() + 1);
                            hasDrawnTile = true;
                        }
                    }
                    return true;
                }
            }

            else if (action instanceof MahjongDiscardTileAction && hasDrawnTile) {
                int buttonID = ((MahjongDiscardTileAction) action).getDiscardButtonID();
                int[] allButtonIDs = ((MahjongDiscardTileAction) action).getAllButtonIDs();

                drawnTile = gameState.getCurrentDrawnTile();

                if (buttonID == allButtonIDs[14] && gameState.prePerm(currHand) != 41) {
                    return false;
                }

                //Iterate through all possible discard buttons
                for (int i = 0; i < allButtonIDs.length; i++) {
                    if (buttonID == allButtonIDs[i]) {
                        //Check if drawn tile is being discarded and discard it
                        if (i == 0) {
                            drawnTile.setLocationNum(5);
                            gameState.setLastDiscarded(drawnTile);
                        }

                        //Discard action on other tiles
                        else {
                            discardTileHelper(drawnTile, playerID, i - 1);
                            //Check if the 14th tile is being discarded and sets winClicked to true
                            //So checkIfGameOver can send a win message
                            if (i == allButtonIDs.length - 1) {
                                gameState.setWinClicked(true);
                            }
                        }
                    }
                }

                //Set last current drawn tile to null
                gameState.setCurrentDrawnTile(null);

                //Change turns of players
                gameState.makeDiscardAction((MahjongDiscardTileAction) action);

                //Turn off chow mode if this is a Chow discard
                if (gameState.isChowMode()) {
                    gameState.setChowMode(-1);
                }
                //If already not in chow mode, check to see if last discarded is chowable
                else {
                    //set chow mode if last discarded tile is chowable
                    setChowMode();
                }

                //Reset the variable
                hasDrawnTile = false;

                return true;
            }

            else if (action instanceof MahjongChowAction && gameState.isChowMode()) {
                //Set chow tile as a last discarded and change its status
                chowTile.setTileStatus(3);

                //Set drawn tile to chow tile
                gameState.setCurrentDrawnTile(chowTile);
                hasDrawnTile = true;
                return true;
            }

        }
        return false;
    }//makeMove()

    /**
     * Helper method for checking to see if there are drawable tiles, if not, then reshuffle
     * the discard pile
     */
    public boolean tileDrawable() {
        int numDrawable = 0;
        //count num drawable tiles in deck
        for (int i = 0; i < gameState.getDeck().length; i++) {
            if (gameState.getDeck()[i].getLocationNum() == 0) {
                numDrawable++;
            }
        }
        //if no tiles are drawable
        if (numDrawable == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Helper method for discard tile, takes the given drawn tile and swaps it with the
     * selected tile to discard
     *
     * @param drawnTile - the tile to be swapped
     * @param playerID  - the player that's discarding a tile
     * @param index     - the index of the drawnTile in the player's hand
     */
    public void discardTileHelper (MahjongTile drawnTile, int playerID, int index) {
        //Set location of drawn tile to player hand
        drawnTile.setLocationNum(playerID + 1);
        if (playerID == 0) {
            //Set tile location in player hand to discard
            gameState.getPlayerOneHand()[index].setLocationNum(5);
            gameState.setLastDiscarded(gameState.getPlayerOneHand()[index]);
            //Set pointer to null
            gameState.getPlayerOneHand()[index] = null;
            //Set drawn tile to player hand
            gameState.getPlayerOneHand()[index] = drawnTile;
            gameState.sortHand(gameState.getPlayerOneHand());
        }

        if (playerID == 1) {
            //Set tile location in player hand to discard
            gameState.getPlayerTwoHand()[index].setLocationNum(5);
            gameState.setLastDiscarded(gameState.getPlayerTwoHand()[index]);
            //Set pointer to null
            gameState.getPlayerTwoHand()[index] = null;
            //Set drawn tile to player hand
            gameState.getPlayerTwoHand()[index] = drawnTile;
            gameState.sortHand(gameState.getPlayerTwoHand());
        }

        if (playerID == 2) {
            //Set tile location in player hand to discard
            gameState.getPlayerThreeHand()[index].setLocationNum(5);
            gameState.setLastDiscarded(gameState.getPlayerThreeHand()[index]);
            //set pointer to null
            gameState.getPlayerThreeHand()[index] = null;
            //set drawn tile to player hand
            gameState.getPlayerThreeHand()[index] = drawnTile;
            gameState.sortHand(gameState.getPlayerThreeHand());
        }

        if (playerID == 3) {
            //Set tile location in player hand to discard
            gameState.getPlayerFourHand()[index].setLocationNum(5);
            gameState.setLastDiscarded(gameState.getPlayerFourHand()[index]);
            //Set pointer to null
            gameState.getPlayerFourHand()[index] = null;
            //Set drawn tile to player hand
            gameState.getPlayerFourHand()[index] = drawnTile;
            gameState.sortHand(gameState.getPlayerFourHand());
        }
    }//discardTileHelper()

    /**
     * Helper method to check if other players can chow the last discarded tile
     *
     * @return true if last discarded will complete a set
     */
    public boolean canChow (MahjongTile[] playerHand, MahjongTile lastDiscarded) {
        int numSetsBefore;
        int numSetsAfter;
        MahjongTile[] copyHand = new MahjongTile[playerHand.length];
        //Make a shallow copy of the player's hand
        for (int i = 0; i < copyHand.length; i++) {
            copyHand[i] = playerHand[i];
        }

        //sort the hand

        gameState.sortHand(copyHand);

        //Count number of sets in hand before chow
        numSetsBefore = gameState.countNumSets(copyHand);

        //Set the null 14th tile slot to last discarded
        copyHand[13] = lastDiscarded;

        //Sort the hand
        gameState.sortHand(copyHand);

        //Sort hand and count number of sets after chow
        numSetsAfter = gameState.countNumSets(copyHand);

        //See if the number of sets increases if you were to chow that tile
        if ((numSetsBefore + 1) == numSetsAfter) {
            return true;
        }

        else {
            return false;
        }
    }//canChow()

    /**
     * Helper method that checks if a player can chow
     */
    public void setChowMode() {
        int playerID = gameState.getPlayerID();
        MahjongTile lastDiscarded = gameState.getLastDiscarded();
        MahjongTile[] handOne = gameState.getPlayerOneHand();
        MahjongTile[] handTwo = gameState.getPlayerTwoHand();
        MahjongTile[] handThree = gameState.getPlayerThreeHand();
        MahjongTile[] handFour = gameState.getPlayerFourHand();

        //Added as a check just in case
        if (lastDiscarded == null) {
            Log.e("ChowLogic", "No tile to chow");
        }

        //Check to see if any other player can chow the last discarded tile
        //and enter chow mode
        switch (playerID) {
            case 0:
                if (canChow(handTwo, lastDiscarded)) {
                    gameState.setChowMode(1);
                } else if (canChow(handThree, lastDiscarded)) {
                    gameState.setChowMode(2);
                } else if (canChow(handFour, lastDiscarded)) {
                    gameState.setChowMode(3);
                }
                break;
            case 1:
                if (canChow(handOne, lastDiscarded)) {
                    gameState.setChowMode(0);
                } else if (canChow(handThree, lastDiscarded)) {
                    gameState.setChowMode(2);
                } else if (canChow(handFour, lastDiscarded)) {
                    gameState.setChowMode(3);
                }
                break;
            case 2:
                if (canChow(handOne, lastDiscarded)) {
                    gameState.setChowMode(0);
                } else if (canChow(handTwo, lastDiscarded)) {
                    gameState.setChowMode(1);
                } else if (canChow(handFour, lastDiscarded)) {
                    gameState.setChowMode(3);
                }
                break;
            case 3:
                if (canChow(handOne, lastDiscarded)) {
                    gameState.setChowMode(0);
                } else if (canChow(handTwo, lastDiscarded)) {
                    gameState.setChowMode(1);
                } else if (canChow(handThree, lastDiscarded)) {
                    gameState.setChowMode(2);
                }
                break;

        } //End switch case

        if (gameState.isChowMode()) {
            chowTile = gameState.getLastDiscarded();
        }
    }//setChowMode()

    /**
     * Send the updated state to a given player
     *
     * @param p - given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // This is a perfect-information game, so we'll make a
        // complete copy of the state to send to the player
        p.sendInfo(new MahjongGameState(this.gameState));
    }

    /**
     * Check if the game is over. It is over, return a string that tells
     * who the winner(s), if any, are. If the game is not over, return null;
     * <p>
     * Win requirement: a player has 4 sets and 1 pair
     *
     * @return a message that tells who has won the game, or null if the
     * game is not over
     */
    @Override
    protected String checkIfGameOver() {

        MahjongTile[] handOne = gameState.getPlayerOneHand();
        MahjongTile[] handTwo = gameState.getPlayerTwoHand();
        MahjongTile[] handThree = gameState.getPlayerThreeHand();
        MahjongTile[] handFour = gameState.getPlayerFourHand();

        if (gameState.isWinClicked()) {
            //if win is clicked, discard occurs which increments a player's turn, so look at
            //previous player's hand to determine a win.
            int playerID = gameState.getPlayerID() - 1;
            if (playerID == 0 && gameState.prePerm(handOne) == 41) {
                return playerNames[0] + " has won!!! Yippee!";
            } else if (playerID == 1 && gameState.prePerm(handTwo) == 41
                    && handTwo[13].getSuit() != "empty suit") {
                return playerNames[1] + " has won!!! Yippee!";
            } else if (playerID == 2 && gameState.prePerm(handThree) == 41
                    && handThree[13].getSuit() != "empty suit") {
                return playerNames[2] + " has won!!! Yippee!";
            } else if (playerID == 3 && gameState.prePerm(handFour) == 41
                    && handFour[13].getSuit() != "empty suit") {
                return playerNames[3] + " has won!!! Yippee!";
            } else {
                return null;
            }
        }
        return null;
    }//checkIfGameOver()

}//class CounterLocalGame
