package com.dice10000.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanPlayer implements Player {
    private int score;
    private Throw curThrow;
    // private Scanner in;

    public boolean isHuman() {
        return true;
    }

    /*
     * public void startScanner() {
     * in = new Scanner(System.in);
     * }
     * 
     * public void closeScanner() {
     * in.close();
     * }
     */

    public void takeTurn() {
        int numDice = 6;
        int meldScore = 0;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (numDice == 0) {
                numDice = 6;
            }
            throwDice(numDice);
            int[] meldOutcome = chooseMeld();
            int meldValue = meldOutcome[0];
            int diceInMeld = meldOutcome[1];

            if (meldValue == -1) {
                System.out.println("GHERLAAA.");
                break;
            } else if (meldValue == 0) {
                System.out.println("Meld not accepted! You lose the points from this round!");
                break;
            } else {
                System.out.println("Meld accepted! Your score increases by " + meldValue);
                numDice -= diceInMeld;
                meldScore += meldValue;
            }

            System.out.println("Do you bank " + meldScore + " points or roll again? (bank/cont)");

            // String bankAns = "";
            // while (in.hasNextLine() && bankAns.equals("")) {
            // bankAns = in.nextLine();
            // break;
            // }

            String ans = "";
            try {
                ans = input.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("Problem in human decide to bank");
                e.printStackTrace();
            }

            if (ans.equals("bank")) {
                bankScore(meldScore);
                System.out.println("You banked " + meldScore + " points. You now have " + score + ".");
                break;
            }
        }
        // in.close();
    }

    public void throwDice(int numDice) {
        curThrow = new Throw(numDice);
        System.out.println("You rolled " + curThrow.getDiceList());
    }

    private int[] chooseMeld() {
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
        // Scanner in = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String ans = "";
        try {
            ans = input.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Problem with human choose meld");
            e.printStackTrace();
        }
        // while (in.hasNextLine() && ans.equals("")) {
        // ans = in.nextLine();
        // break;
        // }
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
            // System.out.println("meld is " + meld);
            // System.out.println("entry is " + entry);
            if (meld.containsAll(key) && meld.size() >= key.size()) {
                meldValue += entry.getValue();
                // System.out.println("meldvalue is " + meldValue);
                meld.removeAll(key);
                // System.out.println("meld after removal is " + meld);
            }
            if (meld.isEmpty()) {
                break;
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

    public boolean isWinner() {
        return (score >= 10000);
    }
}
