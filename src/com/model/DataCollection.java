package com.model;

/**
 * Created by mayalake on 4/22/18.
 */
public class DataCollection {
    private int totalPuzzles = 0;
    private int totalSolutionCost = 0;
    private long totalTimeCost = 0;

    public int getTotalSolutionCost() {
        return totalSolutionCost;
    }

    public long getTotalTimeCost() {
        return totalTimeCost;
    }

    public int getTotalPuzzles(){
        return totalPuzzles;
    }

    public void addToDataCollection(int solutionCost, long timeCost) {
        totalSolutionCost += solutionCost;
        totalTimeCost += timeCost;
        totalPuzzles++;
    }

}
