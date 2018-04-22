package com.View;

import com.controller.HFunc;
import com.controller.HFunc1;
import com.model.Frontier;
import com.model.PuzzleState;

import java.io.*;

/**
 * Created by mayalake on 4/10/18.
 */
public class WriteSampleOutput {
    public static final int NUMBER_OF_SAMPLE_PUZZLES = 3;

    private static final String OUTPUT_FILE_PATH = "src/com/sampleOutput.txt";
    private static final String DEPTH = "Depth";

    private File file;
    private BufferedWriter bw;

    public WriteSampleOutput() throws Exception{
        file = new File(OUTPUT_FILE_PATH);
        bw = new BufferedWriter(new FileWriter(file, true));
    }

    public Boolean write(String puzzle, HFunc hFunc){
        PuzzleState puzzleState;
        Frontier frontier;
        try {
            puzzleState = new PuzzleState(puzzle, hFunc);
            frontier = new Frontier(puzzleState);
            bw.write("Puzzle: " + puzzle + "\n" + frontier.toString() + "\n");

            bw.flush();

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
