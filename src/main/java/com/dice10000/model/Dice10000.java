package com.dice10000.model;

/**
 * Game class.
 *
 */
public class Dice10000 {
    private HumanPlayer humanPlayer;

    public Dice10000() {
        humanPlayer = new HumanPlayer();
        humanPlayer.throwDice(6);

    }
}
