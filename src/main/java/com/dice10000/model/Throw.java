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

    private HashMap<Integer, Integer> diceFrequency = new HashMap<>();

    public void setDiceFrequency() {
        int occurrences;
        for (int i = 1; i < upperBound + 1; i++) {
            occurrences = Collections.frequency(diceList, i);
            diceFrequency.put(i, occurrences);
        }
        System.out.println(diceFrequency); // todo rm
    }

    private HashMap<List<Integer>, Integer> scoresMap = new HashMap<>();

    public void setScoresMap() {
        List<Integer> combo = new ArrayList<>();
        int occurencesOne = diceFrequency.get(1);
        int i = 0;
        while (i < occurencesOne) {
            combo.add(1);
            i++;
        }
        for (i = combo.size(); i > 0; i--) {
            scoresMap.put(combo.subList(0, i), 100 * i);
        }
        System.out.println(scoresMap);
    }

    public List<Integer> getDiceList() {
        return diceList;
    }
}
