package com.model;

import com.View.RandomInput;
import com.View.StandardInput;
import com.controller.HFunc;

import java.util.ArrayList;

/**
 * Created by mayalake on 4/10/18.
 */
public class PuzzleState implements Comparable<PuzzleState>{
    private String tiles;
    private int depth;
    private HFunc hFunc;
    private int heuristicVal;
    private int f;
    private PuzzleState parent = null;
    public int indexOf0;

    public PuzzleState(String testing, HFunc hFunc){
        tiles = testing;
        this.hFunc = hFunc;
        setHeuristicVal();
        indexOf0 = tiles.indexOf('0');
    }

    private void setHeuristicVal(){
        heuristicVal = hFunc.heuristicVal(tiles);
    }

    public void setDepth(int depth){
        this.depth = depth;
        f = depth + heuristicVal;
    }

    public void setParent(PuzzleState parent){
        this.parent = parent;
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

    public int getF() {
        return f;
    }

    @Override
    public boolean equals(Object puzzleState) {
        if (puzzleState instanceof String){
            return this.tiles.equals(puzzleState);
        }

        return false;
    }

    @Override
    public int compareTo(PuzzleState puzzleState) {
        int val = Integer.compare(this.f, puzzleState.f);
        return ((val == 0)? Integer.compare(this.depth, puzzleState.depth) :val);
    }
}
