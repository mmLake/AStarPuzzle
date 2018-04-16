package com.controller;

import com.model.PuzzleState;

/**
 * Created by mayalake on 4/10/18.
 */
public class HFunc2 extends HFunc{

    public int heuristicVal(String state){
        int heuristicVal = 0;
        //i represents the correct integer at an index
        for (int i = 0; i < PuzzleState.PUZZLE_SIZE; i++){
            int puzzleIdx = Character.getNumericValue(state.charAt(i));
            //if an integer is not in the correct location on the puzzle
            if (puzzleIdx != i){
                //represent the index values as a 2D array without using an actual 2D array for speed
                int correctX = i % 3;
                int correctY = i / 3;

                int puzzleX = puzzleIdx % 3;
                int puzzleY = puzzleIdx / 3;

                heuristicVal += Math.abs(correctX - puzzleX) + Math.abs(correctY - puzzleY);
            }
        }
        return heuristicVal;
    }


}
