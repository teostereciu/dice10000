package com.dice10000.model;

import java.util.Scanner;

/**
 * Game class.
 *
 */
public class Dice10000 {
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    public static final int TOTAL_DICE = 6;
    private Player currentPlayer;

    public Dice10000() {
        humanPlayer = new HumanPlayer();
        computerPlayer = new ComputerPlayer();
        // humanPlayer.throwDice(TOTAL_DICE);
        // computerPlayer.throwDice(TOTAL_DICE);
        // currentPlayer = humanPlayer;
        // humanPlayer.startScanner();
        play(humanPlayer);

        // humanPlayer.takeTurn();
        // computerPlayer.takeTurn();
    }

    public void play(Player player) {
        while (winner() == null) {
            player.takeTurn();
            player = next(player);
            // break;
        }
        // humanPlayer.closeScanner();
    }

    public Player winner() {
        if (humanPlayer.isWinner()) {
            return humanPlayer;
        } else if (computerPlayer.isWinner()) {
            return computerPlayer;
        } else {
            return null;
        }
    }

    public Player next(Player player) { // change this for multiplayer
        if (player.isHuman()) {
            return computerPlayer;
        } else {
            return humanPlayer;
        }
    }
}
