package com.dice10000.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer implements Player {
    private int score;
    private Throw curThrow;

    public void takeTurn() {
        int numDice = 6;
        int meldScore = 0;
        Scanner in = new Scanner(System.in);
        while (true) {
            throwDice(numDice);
            int[] meldOutcome = chooseMeld();
            int meldValue = meldOutcome[0];
            int diceInMeld = meldOutcome[1];

            if (meldValue != 0) {
                System.out.println("Meld accepted! Your score increases by " + meldValue);
                numDice -= diceInMeld;
                meldScore += meldValue;
            } else if (meldValue == 0) {
                System.out.println("Meld not accepted! You lose the points from this round!");
                break;
            } else {
                System.out.println("GHERLAAA.");
            }

            System.out.println("Do you bank " + meldScore + " points or roll again? (bank/cont)");

            boolean bank = (in.nextLine().equals("bank"));
            // in.close();

            if (bank) {
                bankScore(meldScore);
                System.out.println("You banked " + meldScore + " points. You now have " + score + ".");
                break;
            }
        }
        in.close();
    }

    public void throwDice(int numDice) {
        curThrow = new Throw(numDice);
        System.out.println("You rolled " + curThrow.getDiceList());
    }

    public int[] chooseMeld() {
        int meldValue = 0;
        int diceInMeld = 0;
        System.out.println("The possible melds are:");
        HashMap<ArrayList<Integer>, Integer> scoresMap = curThrow.getScoresMap();
        System.out.println(scoresMap);
        if (scoresMap.isEmpty()) {
            int[] outcome = new int[] { -1, diceInMeld };
            return outcome;
        }
        System.out.println("Which meld do you choose? Enter dice numbers.");
        Scanner in = new Scanner(System.in);
        String ans = in.nextLine();
        // in.close();

        ArrayList<Integer> meld = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\d+").matcher(ans);

        while (matcher.find()) {
            meld.add(Integer.valueOf(matcher.group()));
        }

        diceInMeld = meld.size();
        // System.out.println("size is " + diceInMeld);

        for (Map.Entry<ArrayList<Integer>, Integer> entry : scoresMap.entrySet()) {
            ArrayList<Integer> key = entry.getKey();
            if (meld.containsAll(key)) {
                meldValue += entry.getValue();
                meld.removeAll(key);
            }
        }
        int[] outcome = new int[] { meldValue, diceInMeld };
        return outcome;
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
