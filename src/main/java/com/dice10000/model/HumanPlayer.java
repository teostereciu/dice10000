package com.dice10000.model;

public class HumanPlayer implements Player {
    private int score;
    private Throw curThrow;

    public void throwDice(int numDice) {
        curThrow = new Throw(numDice);
        System.out.println("Threw " + curThrow.getDiceList());
        // curThrow.setDiceFrequency();
    }

    public void bankScore(int newPoints) {
        score += newPoints;
    }

    public int getScore() {
        return score;
    }

    public Throw getCurThrow() {
        return curThrow;
    }
}
