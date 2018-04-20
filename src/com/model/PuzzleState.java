package com.model;

import com.View.RandomInput;
import com.View.StandardInput;
import com.controller.HFunc;

import java.util.ArrayList;

/**
 * Created by mayalake on 4/10/18.
 */
public class PuzzleState {
    public static final int PUZZLE_SIZE = 9;

    private String tiles;
    private int depth = 0;
    private HFunc hFunc;
    private int heuristicVal;

    private ArrayList<PuzzleState> childStates = new ArrayList<>();

    public PuzzleState(RandomInput ra, HFunc hFunc){
        tiles = ra.populatePuzzle();
        this.hFunc = hFunc;
        setHeuristicVal();
    }

    public PuzzleState(StandardInput sa, HFunc hFunc){
        tiles = sa.populatePuzzle();
        this.hFunc = hFunc;
        setHeuristicVal();
    }

    public PuzzleState(String testing, HFunc hFunc){
        tiles = testing;
        this.hFunc = hFunc;
        setHeuristicVal();
    }

    public PuzzleState(String testing){
        tiles = testing;
    }

    public boolean isSolvable() {
        int inversion = 0;
        String inversionTiles = tiles.split("0")[0] + tiles.split("0")[1];
        for (int i = 0; i < PUZZLE_SIZE-1; i++){
            for (int j = i+1; j < PUZZLE_SIZE-1; j++) {
                if (Character.getNumericValue(inversionTiles.charAt(i)) > Character.getNumericValue(inversionTiles.charAt(j))) {
                    inversion++;
                }
            }
        }
        return (inversion % 2 == 0);
    }

    public PuzzleState puzzleWithSwappedTilesOld(int tile1Idx, int tile2Idx){
        int lowerIdx = ((tile1Idx < tile2Idx) ? tile1Idx : tile2Idx);
        int higherIdx = ((lowerIdx == tile1Idx) ? tile2Idx : tile1Idx);

        String newTiles = tiles.substring(0, lowerIdx)
                + tiles.charAt(higherIdx)
                + tiles.substring(lowerIdx+1, higherIdx)
                + tiles.charAt(lowerIdx)
                + tiles.substring(higherIdx+1);

        PuzzleState puzzleState = new PuzzleState(newTiles, hFunc);

        puzzleState.depth = this.depth+1;

        System.out.println("PARENT DEPTH " + this.depth + " CHILD DEPTH " + puzzleState.depth);
        System.out.println("CHILD TILES " + puzzleState.getTiles() + " CHILD H " + puzzleState.heuristicVal);

        return puzzleState;
    }

    public void addChildState(PuzzleState childState){
        childStates.add(childState);
    }

    public void incrementDepth(){depth++;}

    //use when checking testfiles
    public void setDepth(int depth){
        this.depth = depth;
    }

    private void setHeuristicVal(){
        heuristicVal = hFunc.heuristicVal(tiles);
    }

    public String getTiles() {
        return tiles;
    }

    public ArrayList<PuzzleState> getChildStates(){
        return childStates;
    }

    public int getDepth(){return depth;}

    public int getHeuristicVal() {
        return heuristicVal;
    }

    public HFunc gethFunc() {
        return hFunc;
    }

    @Override
    public boolean equals(Object puzzleState) {
        if (puzzleState instanceof PuzzleState){
            return this.tiles.equals(((PuzzleState) puzzleState).tiles);
        }

        if (puzzleState instanceof String){
            return this.tiles.equals(puzzleState);
        }

        return false;
    }
}
