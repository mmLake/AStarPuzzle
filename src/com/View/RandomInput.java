package com.View;

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
        }while(!isValid(finalPuzzle));

        return finalPuzzle;
    }

    public boolean isValid(String puzzle) {
        int inversion = 0;
        String inversionTiles = "";
        String[] tilesWithout0 = puzzle.split("0");
        for (String s : tilesWithout0) {
            inversionTiles += s;
        }

        for (int i = 0; i < puzzle.length()-1; i++){
            for (int j = i+1; j < puzzle.length()-1; j++) {
                if (Character.getNumericValue(inversionTiles.charAt(i)) > Character.getNumericValue(inversionTiles.charAt(j))) {
                    inversion++;
                }
            }
        }
        return (inversion % 2 == 0);
    }

}
