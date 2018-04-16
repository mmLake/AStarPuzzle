package com.View;

import com.model.PuzzleState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by mayalake on 4/15/18.
 */
public class ReadTestFile {
    private static final String inputFilePath = "com/input1.txt";

    private File file;
    private BufferedReader br;
    private int depth = 0;
    private String tiles = "";

    public ReadTestFile() throws Exception{
        file = new File(inputFilePath);
        br = new BufferedReader(new FileReader(file));
    }

    public PuzzleState populatePuzzle() throws Exception {
        String puzzleInput;

        //check if text file is empty
        if ((puzzleInput = br.readLine()) == null){
            return null;
        }

        //if file is not empty, get puzzle and puzzle depth
        puzzleInput = br.readLine();

        if (puzzleInput.contains("Depth")){
            depth = Integer.parseInt(puzzleInput.substring(6));
            tiles = br.readLine();
        } else {
            tiles = puzzleInput;
        }


        PuzzleState testPuzzle = new PuzzleState(tiles);
        testPuzzle.setDepth(depth);
        return testPuzzle;
    }
}
