package com.dice10000.model;

/**
 * Game class.
 *
 */
public class Dice10000 {
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    public static final int TOTAL_DICE = 6;

    public Dice10000() {
        humanPlayer = new HumanPlayer();
        computerPlayer = new ComputerPlayer();
        // humanPlayer.throwDice(TOTAL_DICE);
        // computerPlayer.throwDice(TOTAL_DICE);
        humanPlayer.takeTurn();
    }
}
