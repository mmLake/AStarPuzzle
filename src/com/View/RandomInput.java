package com.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List; 

/**
 * Created by mayalake on 4/10/18.
 */
public class RandomInput {

    public String populatePuzzle(){
        List<Integer> puzzleVals= Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);

        do {
            Collections.shuffle(puzzleVals);
        }while(!isValid(puzzleVals));

        return puzzleVals.toString();
    }

    private boolean isValid(List<Integer> puzzle){
        //algorithm to check if # of inversions % 2 == 0
        return true;
    }
}
