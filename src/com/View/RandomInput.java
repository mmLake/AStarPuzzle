package com.View;

import com.controller.PuzzleStateController;
import com.model.PuzzleState;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * Created by mayalake on 4/10/18.
 */
public class RandomInput {
    public static final int NUMBER_OF_RANDOM_PUZZLES = 10;

    public String populatePuzzle(){
        List<Integer> puzzleVals= Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        String finalPuzzle;

        do {
            Collections.shuffle(puzzleVals);

            finalPuzzle = puzzleVals.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining(""));
        }while(!PuzzleStateController.isValid(finalPuzzle));

        return finalPuzzle;
    }
}
