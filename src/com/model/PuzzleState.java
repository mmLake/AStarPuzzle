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
    private PuzzleState parent = null;
    private long runTimeInMS = 0;

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
        String inversionTiles = "";
        String[] tilesWithout0 = tiles.split("0");
        for (String s : tilesWithout0) {
            inversionTiles += s;
        }

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
        puzzleState.parent = this;

        return puzzleState;
    }

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

    public int getDepth(){return depth;}

    public int getHeuristicVal() {
        return heuristicVal;
    }

    public HFunc gethFunc() {
        return hFunc;
    }

    public PuzzleState getParent() {
        return parent;
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

    @Override
    public String toString(){
        //modify to return the path of puzzle to get solved
        return tiles;
    }
}
