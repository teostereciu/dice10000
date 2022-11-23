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
        while (true) {
            int meldValue = 0;
            throwDice(numDice);
            System.out.println("The possible melds are:");
            HashMap<ArrayList<Integer>, Integer> scoresMap = curThrow.getScoresMap();
            System.out.println(scoresMap);

            System.out.println("Which meld do you choose? Enter dice numbers.");
            Scanner in = new Scanner(System.in);
            String ans = in.nextLine();

            ArrayList<Integer> meld = new ArrayList<>();
            Matcher matcher = Pattern.compile("\\d+").matcher(ans);

            while (matcher.find()) {
                meld.add(Integer.valueOf(matcher.group()));
            }

            for (Map.Entry<ArrayList<Integer>, Integer> entry : scoresMap.entrySet()) {
                ArrayList<Integer> key = entry.getKey();
                if (meld.containsAll(key)) {
                    meldValue += entry.getValue();
                    meld.removeAll(key);
                }
            }

            if (meld.isEmpty()) {
                System.out.println("Meld accepted! Your score increases by " + meldValue);
                meldScore += meldValue;
            } else {
                System.out.println("Meld not accepted! You lose the points from this round!");
                break;
            }

            System.out.println("Do you bank " + score + "points or roll again? (y/n)");
            boolean bank = (in.nextLine() == "y");

            in.close();
            if (bank) {
                bankScore(meldScore);
                break;
            }
        }

    }

    public void throwDice(int numDice) {
        curThrow = new Throw(numDice);
        System.out.println("You rolled " + curThrow.getDiceList());
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
