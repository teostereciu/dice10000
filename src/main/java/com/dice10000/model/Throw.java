package com.dice10000.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A throw of 1-6 dices with 6 faces.
 * 
 */
public class Throw {
    protected List<Integer> diceList = new ArrayList<>();
    private final int upperBound = 6;

    public Throw(int num) {
        Random rand = new Random();
        int randInt;
        while (num != 0) { // generate random values for num (0<num<7) dice in the throw
            do {
                randInt = rand.nextInt(upperBound + 1);
            } while (randInt == 0);
            diceList.add(randInt);
            num--;
        }
    }
}
