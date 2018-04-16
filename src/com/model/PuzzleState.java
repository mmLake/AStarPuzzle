package com.model;

import com.View.RandomInput;
import com.View.StandardInput;
import com.controller.HFunc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mayalake on 4/10/18.
 */
public class PuzzleState {
//    public static final int[] GOAL_PUZZLE_STATE = {0,1,2,3,4,5,6,7,8};

    public static final int PUZZLE_SIZE = 9;
    private String tiles;
    private int depth;
    private int expansionVal;
    private int emptySpaceIdx;

    private ArrayList<PuzzleState> childStates = new ArrayList<>();

    public PuzzleState(RandomInput ra){
        tiles = ra.populatePuzzle();
    }

    public PuzzleState(StandardInput sa){
        tiles = sa.populatePuzzle();
    }

    public PuzzleState(String testing){
        tiles = testing;
    }


    public String getTiles() {
        return tiles;
    }

    public boolean isSolvable() {
        int inversion = 0;
        for (int i = 0; i < PUZZLE_SIZE; i++){
            for (int j = i+1; j < PUZZLE_SIZE; j++) {
                if (Character.getNumericValue(tiles.charAt(i)) > Character.getNumericValue(tiles.charAt(j))) {
                    inversion++;
                }
            }
        }
        return (inversion % 2 == 0);
    }

    public ArrayList<PuzzleState> getChildStates(){
        return childStates;
    }

    public int getDepth(){return depth;}

    //use when checking testfiles
    public void setDepth(int depth){
        this.depth = depth;
    }

    public void addChildState(PuzzleState childState){
        childStates.add(childState);
    }

    public void incrementDepth(){depth++;}

//    public int getExpansionVal(){
//
//    }

}
