package com.View;

import com.model.PuzzleState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by mayalake on 4/15/18.
 */
public class ReadTestFile {
    private static final String DEPTH = "Depth";

    private File file;
    private BufferedReader br;

    public ReadTestFile(String filePath) throws Exception{
        file = new File(filePath);
        br = new BufferedReader(new FileReader(file));
    }

    public String populatePuzzle() throws Exception {
        String puzzleInput;

        //check if text file is empty
        if ((puzzleInput = br.readLine()) != null){
            if (puzzleInput.contains(DEPTH)){
                System.out.println(puzzleInput);

                return br.readLine();
            } else {
                return puzzleInput;
            }
        }

        return null;
    }
}
