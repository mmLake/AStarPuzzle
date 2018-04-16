package com.model;

/**
 * Created by mayalake on 4/10/18.
 */
public class TestData {
    int costs; //number of nodes that are generated to solve puzzle
    int depth; //number of steps it takes to solve puzzle

    public int getCosts() {
        return costs;
    }

    public void setCosts(int costs) {
        this.costs = costs;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public double getAverageCost() {
        return costs / depth;
    }


}
