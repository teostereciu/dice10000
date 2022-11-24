package com.dice10000.model;

import java.util.Scanner;

/**
 * Player interface
 * 
 */
interface Player {
    public boolean isHuman();

    public void takeTurn();

    public void throwDice(int numDice);

    public void bankScore(int newPoints);

}