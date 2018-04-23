package com.model;

import com.controller.FrontierController;

/**
 * Created by mayalake on 4/15/18.
 */
public class Frontier {
    private static int numberOfStates = 0;
    private int finalDepth = 0;
    private long timeCost = 0;
    private String puzzlePath = "\n";

    public Frontier(PuzzleState root){
        //start time
        long startTime = System.nanoTime();

        //run aStar
        PuzzleState finalState = FrontierController.aStarAlgorithm(root);

        //end time
        timeCost = System.nanoTime() - startTime;

        if (!(finalState == null)){
            finalDepth = finalState.getDepth();

            PuzzleState temp = finalState;
            while ((temp = temp.getParent()) != null){
                puzzlePath = temp.getTiles() + "\n" + puzzlePath;
            }
        }
    }

    public String toString() {
        return "Depth " + finalDepth + "\n"
                + "Total # of states " + numberOfStates + "\n"
                + puzzlePath;
    }

    public int getFinalDepth(){
        return finalDepth;
    }

    public int getNumberOfStates(){
        return numberOfStates;
    }

    public long getTimeCost() {
        return timeCost;
    }

    public static void incrementNumberOfStates(){
        numberOfStates++;
    }
}
