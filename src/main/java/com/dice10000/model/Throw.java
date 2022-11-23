package com.dice10000.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * A throw of 1-6 dices with 6 faces.
 * 
 */
public class Throw {
    protected List<Integer> diceList = new ArrayList<>();
    private final int upperBound = 6;
    private int numDice = 0;

    public Throw(int num) {
        numDice = num;
        Random rand = new Random();
        int randInt;
        while (num != 0) { // generate random values for num (0<num<7) dice in the throw
            do {
                randInt = rand.nextInt(upperBound + 1);
            } while (randInt == 0);
            diceList.add(randInt);
            num--;
        }
        setDiceFrequency();
        setScoresMap();
    }

    private List<Integer> diceFrequency = new ArrayList<>();

    public void setDiceFrequency() {
        int occurrences;
        diceFrequency.add(0, null);
        for (int i = 1; i < upperBound + 1; i++) {
            occurrences = Collections.frequency(diceList, i);
            diceFrequency.add(i, occurrences);
        }
        // System.out.println(diceFrequency); // todo rm
    }

    private HashMap<ArrayList<Integer>, Integer> scoresMap = new HashMap<>();

    public HashMap<ArrayList<Integer>, Integer> getScoresMap() {
        return scoresMap;
    }

    public void setScoresMap() {
        ArrayList<Integer> combo = new ArrayList<>();
        int scoreCombo = 0;
        boolean straightOneSix = true;
        for (int die = 1; die < upperBound + 1; die++) {
            int occurencesDie = diceFrequency.get(die);
            switch (occurencesDie) {
                case 6:
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    if (die == 1) {
                        scoreCombo = 10 * 100 * 8;
                    } else {
                        scoreCombo = die * 100 * 8;
                    }
                    scoresMap.put(combo, scoreCombo);
                    combo = new ArrayList<>();
                    occurencesDie--;
                case 5:
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    if (die == 1) {
                        scoreCombo = 10 * 100 * 4;
                    } else {
                        scoreCombo = die * 100 * 4;
                    }
                    scoresMap.put(combo, scoreCombo);
                    combo = new ArrayList<>();
                    occurencesDie--;
                case 4:
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    if (die == 1) {
                        scoreCombo = 10 * 100 * 2;
                    } else {
                        scoreCombo = die * 100 * 2;
                    }
                    scoresMap.put(combo, scoreCombo);
                    combo = new ArrayList<>();
                    occurencesDie--;
                case 3:
                    combo.add(die);
                    combo.add(die);
                    combo.add(die);
                    if (die == 1) {
                        scoreCombo = 10 * 100;
                    } else {
                        scoreCombo = die * 100;
                    }
                    scoresMap.put(combo, scoreCombo);
                    combo = new ArrayList<>();
                    occurencesDie--;
                case 2:
                    if (die == 1 || die == 5) {
                        combo.add(die);
                        combo.add(die);
                        scoreCombo = 2 * (50 + 50 * (die % 5)); // two 1 die -> 200p and two 5 die -> 100p
                        scoresMap.put(combo, scoreCombo);
                        combo = new ArrayList<>();
                        ;
                        occurencesDie--;
                    }
                case 1:
                    if (die == 1 || die == 5) {
                        combo.add(die);
                        scoreCombo = 50 + 50 * (die % 5); // one 1 die -> 100p and one 5 die -> 50p
                        scoresMap.put(combo, scoreCombo);
                        combo = new ArrayList<>();
                    }
                    break;
                case 0:
                    straightOneSix = false;
            }
        }
        if (numDice == 6 && straightOneSix) {
            for (int die = 1; die < upperBound + 1; die++)
                combo.add(die);
            scoreCombo = 1500;
            scoresMap.put(combo, scoreCombo);
            combo = new ArrayList<>();
        }
        // System.out.println(scoresMap);
    }

    public List<Integer> getDiceList() {
        return diceList;
    }
}
