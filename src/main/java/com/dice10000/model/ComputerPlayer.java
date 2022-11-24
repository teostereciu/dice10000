package com.dice10000.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Computer player that plays randomly.
 * 
 */
public class ComputerPlayer implements Player {
    private int score;
    private Throw curThrow;

    public boolean isHuman() {
        return false;
    }

    public void takeTurn() {
        int numDice = 6;
        int meldScore = 0;
        while (true) {
            if (numDice == 0) {
                numDice = 6;
            }
            throwDice(numDice);
            int[] meld = chooseMeldRandom();
            int meldValue = meld[0];

            if (meldValue == -1) {
                System.out.println("GHERLAAA.");
                break;
            } else if (meldValue == 0) {
                System.out.println("Meld not accepted!");
                break;
            } else {
                System.out.println("Meld accepted! Computer's score increases by " + meldValue + ".");
                numDice -= meld[1];
                meldScore += meldValue;
            }

            Random bank = new Random();
            if (bank.nextBoolean()) {
                bankScore(meldScore);
                System.out.println("Computer banked " + meldScore + " points. Computer now has " + score + ".");
                break;
            } else {
                System.out.println("Computer rolls again.");
            }

        }

    }

    private int[] chooseMeldRandom() {
        int meldValue = 0;
        int diceInMeld = 0;
        System.out.println("The possible melds are:");
        HashMap<ArrayList<Integer>, Integer> scoresMap = curThrow.getScoresMap();
        System.out.println(scoresMap);

        if (scoresMap.isEmpty()) {
            int[] outcome = new int[] { -1, diceInMeld };
            return outcome;
        }

        Random rand = new Random();
        System.out.println("Opponent chooses:");
        for (Map.Entry<ArrayList<Integer>, Integer> entry : scoresMap.entrySet()) {
            if (rand.nextBoolean()) {
                ArrayList<Integer> key = entry.getKey();
                System.out.print(key + " ");
                meldValue += entry.getValue();
                diceInMeld += key.size();
                break;
            }
        }
        int[] outcome = new int[] { meldValue, diceInMeld };
        return outcome;
    }

    public void throwDice(int numDice) {
        curThrow = new Throw(numDice);
        System.out.println("Opponent rolled " + curThrow.getDiceList());
    }

    public void bankScore(int newPoints) {
        score += newPoints;
    }

    public boolean isWinner() {
        return (score >= 10000);
    }
}
