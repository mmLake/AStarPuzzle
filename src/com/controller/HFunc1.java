package com.controller;

import com.model.PuzzleState;
import com.model.TestData;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by mayalake on 4/10/18.
 */
public class HFunc1 extends HFunc{
    PuzzleState puzzleState;
    TestData testData;


//    public void solvePuzzle(PuzzleState puzzleState, int cost, int depth){
//       if (puzzleState.isSolvable()){
//
//       }
//    }

    public int heuristicVal(String state){
        int heuristicVal = 0;
        for (int i = 0; i < PuzzleState.PUZZLE_SIZE; i++){
            if (Character.getNumericValue(state.charAt(i)) != i){
                heuristicVal++;
            }
        }
        return heuristicVal;
    }
}