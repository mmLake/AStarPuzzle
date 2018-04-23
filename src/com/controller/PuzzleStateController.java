package com.controller;

import com.model.PuzzleState;

/**
 * Created by mayalake on 4/22/18.
 */
public class PuzzleStateController {
    public static boolean isValid(String puzzle) {
        int inversion = 0;
        String puzzleNew = puzzle.replace("0","");

        for (int i = 0; i < puzzle.length()-1; i++){
            for (int j = i+1; j < puzzle.length()-1; j++) {
                if (Character.getNumericValue(puzzleNew.charAt(i)) > Character.getNumericValue(puzzleNew.charAt(j))) {
                    inversion++;
                }
            }
        }
        return (inversion % 2 == 0);
    }

    public static PuzzleState getChildPuzzle(PuzzleState parent, String tiles){
        PuzzleState puzzleState = new PuzzleState(tiles, parent.gethFunc());
        puzzleState.setDepth(parent.getDepth()+1);
        puzzleState.setParent(parent);

        return puzzleState;
    }
}
