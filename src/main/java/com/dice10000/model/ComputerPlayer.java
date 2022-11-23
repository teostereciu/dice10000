package com.dice10000.model;

public class ComputerPlayer implements Player {
    private int score;
    private Throw curThrow;

    public void takeTurn() {

    }

    public void throwDice(int numDice) {
        curThrow = new Throw(numDice);
        System.out.println("Opponent rolled " + curThrow.getDiceList());
    }

    public void bankScore(int newPoints) {
        score += newPoints;
    }
}
