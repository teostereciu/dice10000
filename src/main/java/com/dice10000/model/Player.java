package com.dice10000.model;

/**
 * Player interface
 * 
 */
interface Player {
    public void takeTurn();

    public void throwDice(int numDice);

    public void bankScore(int newPoints);

}